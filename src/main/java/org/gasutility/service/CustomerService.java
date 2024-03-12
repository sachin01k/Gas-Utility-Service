package org.gasutility.service;


import org.gasutility.dto.Request;
import org.gasutility.models.ServiceRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CustomerService implements Customer {	
	
	public String newRequest(Request request) {
		String new_request_url = "http://localhost:8080/customer-api/new-request"
				+ "/type/"+ request.getType() + "/description"
				+ "/"+ request.getDescription() +"/customerid/" + request.getCustomerId();
		
		WebClient webClient = WebClient.create();

		String msg = webClient.get()
				.uri(new_request_url)
				.retrieve()
				.bodyToMono(String.class)
				.block();
		
		return msg;
	}
	
	@Override
	public ServiceRequest trackRequest(Integer requestId) {
		
		String new_request_url = "http://localhost:8080/customer-api/request-status?requestId="+requestId;
		WebClient webClient = WebClient.create();

		ServiceRequest request= webClient.get()
				.uri(new_request_url)
				.retrieve()
				.bodyToMono(ServiceRequest.class)
				.block();
		
		if(request != null)
			return request;
					
		return null;
	}
}
