package org.gasutility.controller;

import org.gasutility.dto.incoming.Customer;
import org.gasutility.dto.incoming.ServiceRequest;
import org.gasutility.dto.incoming.UpdateRequestDetails;
import org.gasutility.dto.outgoing.CustomerDetails;
import org.gasutility.dto.outgoing.GasConnectionRequestDetails;
import org.gasutility.dto.outgoing.ServiceRequestDetails;

import org.gasutility.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CustomerController in a Controller class which handle's Requests from user.
 * @author Sachin Kamble
 */
@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    /**
     * Hello Method greet the user.
     * @return
     */
    @GetMapping("")
    public String hello() {
        return "Hey, Welcome to Gas Utility Service\nHow can we help you??";
    }

    /**
     * Register method is mapped to New Costumer Registration request.
     * @param customer
     * @return
     */
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody Customer customer, BindingResult result){

        String response = customerService.registerCustomer(customer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("customer-details/{customerId}")
    public ResponseEntity<CustomerDetails> getCustomerDetails(@PathVariable("customerId") Integer customerId){

        return customerService.getCustomerDetails(customerId);
    }

    @PutMapping("update-customer-details")
    public ResponseEntity<String> updateCustomerDetails(@RequestBody CustomerDetails customerDetails){

        return customerService.updateCustomerDetails(customerDetails);
    }

    @GetMapping("new-gas-connection/{customerId}")
    public ResponseEntity<String> newConnection(@PathVariable("customerId") Integer customerId){

        String response = customerService.newConnection(customerId);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("view-gas-connection-request/{requestId}")
    public ResponseEntity<GasConnectionRequestDetails> viewGasConnectionDetails(@PathVariable("requestId") Integer connectionRequestID){
        return  customerService.viewGasConnectionRequestDetails(connectionRequestID);
    }

    @PostMapping("service-request")
    public ResponseEntity<String> serviceRequest(@RequestBody ServiceRequest request){

        String response = customerService.newServiceRequest(request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("track-request/{requestId}")
    public ResponseEntity<ServiceRequestDetails> trackRequest(@PathVariable("requestId") Integer requestId){
        return customerService.trackRequest(requestId);
    }

    @GetMapping("track-all-requests/{customerId}")
    public ResponseEntity<List<ServiceRequestDetails>> viewAllRequest(@PathVariable("customerId") Integer customerId){

        return customerService.viewAllRequest(customerId);
    }

    @PutMapping("update-request-Details")
    public ResponseEntity<String> updateRequestDetails(@RequestBody UpdateRequestDetails requestDetails){

        return customerService.updateRequestDetails(requestDetails);
    }

    @DeleteMapping("delete-request/{requestId}")
    public ResponseEntity<String> deleteServiceRequest(@PathVariable("requestId") Integer requestId){

        return customerService.deleteServiceRequest(requestId);
    }

}
