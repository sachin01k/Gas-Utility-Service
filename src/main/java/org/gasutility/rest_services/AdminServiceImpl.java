package org.gasutility.rest_services;

import java.util.List;

import org.gasutility.dto.AdminLogin;
import org.gasutility.models.Admin;
import org.gasutility.models.ServiceRequest;
import org.gasutility.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository repo;
	
	@Override
	public boolean verify(AdminLogin adminLogin) {
		
		Admin admin = repo.check(adminLogin.getUsername(), adminLogin.getPassword());
		
		if(admin != null)
			return true;
		
		return false;
	}
	
	@Override
	public List<ServiceRequest> viewRequest() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
