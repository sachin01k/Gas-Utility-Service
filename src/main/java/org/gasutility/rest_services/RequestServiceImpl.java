package org.gasutility.rest_services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.gasutility.dto.Request;
import org.gasutility.models.Customer;
import org.gasutility.models.ServiceRequest;
import org.gasutility.repository.CustomerRepository;
import org.gasutility.repository.RequestRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {
	
	@Autowired
	private RequestRepository repo;
	
	@Autowired
	private CustomerRepository crepo;
	
	@Override
	public String newRequest(Request request) {
		
		Integer customerId = request.getCustomerId();
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		ServiceRequest sr = new ServiceRequest();
		BeanUtils.copyProperties(request, sr);		
		sr.setCustomer(customer);
		
		sr = repo.save(sr);
		
		if(sr.getRequestId()!=null && sr.getType().equals("booking")) {
			return "New Booking Request Created Booking Id : " + sr.getRequestId();
		}
		else if(sr.getRequestId()!=null && sr.getType().equals("gas-leak")) {
			return "Gas leak complaint registered, Registration Id : " + sr.getRequestId();
		}		
		
		return "Something went Wrong";
	}
	
	public ServiceRequest getCustomerRequestStatus(Integer requestId){

		Optional<ServiceRequest> optional =  repo.findById(requestId);
		if(optional.isPresent()) {
			ServiceRequest request = optional.get();
			return request;
		}
		
		return null;
	}
	
	public List<ServiceRequest> getCustomerRequestStatus(){

		List<ServiceRequest> list =  repo.getCustomerRequests();	
		
		Collections.sort(list, new Comparator<ServiceRequest>() {
			
			public int compare(ServiceRequest obj1, ServiceRequest obj2) {
				
				if(obj1.getDate().equals(obj2))
					return 0;
				
				else if(obj1.getDate().isAfter(obj2.getDate()))
					return -1;				
				
				return 1;
			}
		});
		
		return list;
	}

}
