package org.gasutility.rest_services;

import org.gasutility.dto.CustomerDetails;
import org.gasutility.models.Customer;

public interface CustomerService {
	
	public String addCustomer(CustomerDetails customerDetails);
	public CustomerDetails getCustomerAccountInfo(Integer customerId);
	
}
