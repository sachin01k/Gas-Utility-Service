package org.gasutility.rest_services;



import java.util.List;

import org.gasutility.dto.Request;
import org.gasutility.models.ServiceRequest;

public interface RequestService {
	
	public String newRequest(Request request);
	public ServiceRequest getCustomerRequestStatus(Integer requestId);
	public List<ServiceRequest> getCustomerRequestStatus();
}
