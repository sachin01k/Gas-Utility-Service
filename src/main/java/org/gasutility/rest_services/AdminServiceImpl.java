package org.gasutility.rest_services;

import java.util.Optional;

import org.gasutility.dto.AdminLogin;
import org.gasutility.models.Admin;
import org.gasutility.models.ServiceRequest;
import org.gasutility.repository.AdminRepository;
import org.gasutility.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository repo;
	
	@Autowired
	private RequestRepository rRepo;
	
	@Override
	public boolean verify(AdminLogin adminLogin) {
		
		Admin admin = repo.check(adminLogin.getUsername(), adminLogin.getPassword());
		
		if(admin != null)
			return true;
		
		return false;
	}
	
	@Override
	public boolean acceptRequest(Integer requestId) {
		
		Optional<ServiceRequest> optional = rRepo.findById(requestId);
		
		if(optional.isPresent())
		{
			ServiceRequest request = optional.get();
			
			request.setRequestId(requestId);
			request.setStatus("accepted");
			request = rRepo.save(request);
			
			if(request.getStatus() == "accepted")
				return true;
		}		
		
		return false;
	}
	
}
