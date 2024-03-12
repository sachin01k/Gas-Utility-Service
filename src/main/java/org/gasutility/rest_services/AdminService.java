package org.gasutility.rest_services;

import java.util.List;

import org.gasutility.dto.AdminLogin;
import org.gasutility.models.ServiceRequest;

public interface AdminService {
 
	public boolean verify(AdminLogin adminLogin);
	public List<ServiceRequest> viewRequest();
}
