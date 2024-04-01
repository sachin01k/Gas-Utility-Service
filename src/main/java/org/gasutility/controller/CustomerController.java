package org.gasutility.controller;


import org.gasutility.dto.incoming.Customer;
import org.gasutility.dto.incoming.GasConnectionRequest;
import org.gasutility.dto.incoming.ServiceRequest;
import org.gasutility.dto.incoming.UpdateRequestDetails;
import org.gasutility.dto.outgoing.CustomerDetails;
import org.gasutility.dto.outgoing.ServiceRequestDetails;

import org.gasutility.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("")
    public String hello() {
        return "Hey, Welcome to Gas Utility Service\nHow can we help you??";
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody Customer customer){

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

    @PostMapping("new-gas-connection")
    public ResponseEntity<String> newConnection(@RequestBody GasConnectionRequest connection){

        String response = customerService.newConnection(connection);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
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
