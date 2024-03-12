package org.gasutility.service;

import java.util.List;

import org.gasutility.dto.AdminLogin;
import org.gasutility.models.ServiceRequest;


public interface IAdminService {
	
	public Boolean verify(AdminLogin adminLogin);
	public Boolean updateStatus(Integer requestId);
	public List<ServiceRequest> getNewPendingRequest();
}
