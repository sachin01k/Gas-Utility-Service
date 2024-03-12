package org.gasutility.service;

import java.util.List;

import org.gasutility.dto.AdminLogin;
import org.gasutility.models.ServiceRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AdminService implements IAdminService {
	
	public Boolean verify(AdminLogin adminLogin) {
		WebClient webClient = WebClient.create();
    	Boolean status = webClient.post()
    			.uri("http://localhost:8080/admin/login")
    			.bodyValue(adminLogin)
    			.retrieve()
    			.bodyToMono(Boolean.class)
    			.block();
    	
    	return status;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceRequest> getNewPendingRequest() {
		WebClient webClient = WebClient.create();		
		
		return  webClient.get()
    			.uri("http://localhost:8080/admin/load")
    			.retrieve()
    			.bodyToMono(List.class)
    			.block();
    	
	}
	
	public Boolean updateStatus(Integer requestId) {
		WebClient webClient = WebClient.create();		
		
		return  webClient.put()
    			.uri("http://localhost:8080/admin/accept-request/" + requestId)
    			.retrieve()
    			.bodyToMono(Boolean.class)
    			.block();
		
	}
}
