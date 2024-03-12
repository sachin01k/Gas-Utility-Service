package org.gasutility.rest_services;



import org.gasutility.dto.AdminLogin;


public interface AdminService {
 
	public boolean verify(AdminLogin adminLogin);
	public boolean acceptRequest(Integer requestId);
}
