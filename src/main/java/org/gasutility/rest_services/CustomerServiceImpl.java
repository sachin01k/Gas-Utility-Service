package org.gasutility.rest_services;

import java.util.Optional;

import org.gasutility.dto.Address;
import org.gasutility.dto.CustomerDetails;
import org.gasutility.models.Customer;
import org.gasutility.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository repo;
	
	@Override
	public String addCustomer(CustomerDetails customerDetails) {
		
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDetails, customer);
		
		Address address = customerDetails.getAddress();		
		String addres = address.getStreet() + " " + address.getDistrict()
											+ " " +address.getState();
		
		customer.setAddress(addres);		
		customer = repo.save(customer);
		
		if(customer.getCustomerId()!=null) {
			return "Account Created Successfully Customer ID : " + customer.getCustomerId();
		}
		
		return "Account Creation Failed";
	}
	
	@Override
	public CustomerDetails getCustomerAccountInfo(Integer customerId) {
		
		Optional<Customer> optional = repo.findById(customerId);
		if(optional.isPresent()) {
			Customer customer = optional.get();
			
			CustomerDetails customerDetails = new CustomerDetails();
			
			BeanUtils.copyProperties(customer, customerDetails);
			String []address = customer.getAddress().split(" ");
			Address addres = new Address();
			addres.setStreet(address[0]);
			addres.setDistrict(address[1]);
			addres.setState(address[2]);
			
			customerDetails.setAddress(addres);
			return customerDetails;
		}
		return null;
	}
}
