package org.gasutility.rest;



import org.gasutility.dto.CustomerDetails;
import org.gasutility.dto.Request;
import org.gasutility.models.Customer;
import org.gasutility.models.ServiceRequest;
import org.gasutility.rest_services.CustomerService;
import org.gasutility.rest_services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer-api")
public class CustomerRestApi {

	
	private CustomerService service;
	private RequestService requestService;
	
	@Autowired
	public CustomerRestApi(CustomerService service,RequestService requestService) {
		this.service = service;
		this.requestService = requestService;
	}
	
	
    @GetMapping("/")
    public String hello(){
        return "hello world";
    }
    
    @PostMapping(value="/create-account", consumes="application/json")
    public String addCustomer(@RequestBody CustomerDetails customerDetails) {
    	
    	String msg = service.addCustomer(customerDetails);    	
    	return msg;
    }
    
    @GetMapping("/new-request/type/{type}/description/{description}/customerid/{customerId}")
    public String newRequest(@PathVariable("type") String type,
    		@PathVariable("description") String description,
    		@PathVariable("customerId") Integer customerId) {
    	
    	String msg = requestService.newRequest(new Request(customerId,type,description));
    	
    	return msg;
    }
    
    @GetMapping("/account-info")
    public CustomerDetails getCustomerAccountInfo(
    		@RequestParam("customerId") Integer customerId){
    	
    	CustomerDetails customerDetails = service.getCustomerAccountInfo(customerId);
    	
    	return customerDetails;
    }
    
    
    @GetMapping("/request-status")
    public ServiceRequest viewRequestStatus(
    		@RequestParam("requestId") Integer requestId){
    	
    	ServiceRequest request = requestService.getCustomerRequestStatus(requestId);    	
    	
    	return request;
    }
    
    
    
    
    
    
    
    
    
    
    
    

}