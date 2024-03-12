package org.gasutility.service;

import org.gasutility.dto.Request;
import org.gasutility.models.ServiceRequest;

public interface Customer {
	
	public String newRequest(Request request);
	public ServiceRequest trackRequest(Integer requestId);
}
