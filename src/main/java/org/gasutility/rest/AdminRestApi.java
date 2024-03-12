package org.gasutility.rest;

import java.util.List;

import org.gasutility.dto.AdminLogin;
import org.gasutility.models.ServiceRequest;
import org.gasutility.rest_services.AdminService;
import org.gasutility.rest_services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminRestApi {
	
	@Autowired
	private AdminService service;
	
	@Autowired
	private RequestService reqService;
	
	@PostMapping("/login")
	public Boolean login(@RequestBody AdminLogin adminLogin) {
		
		boolean status = service.verify(adminLogin);
		
		if(status)
			return status;
		
		return false;
	}
	
	@GetMapping("/load")
	public List<ServiceRequest> getNewPendingRequest(){
		return reqService.getCustomerRequestStatus();
	}
}
