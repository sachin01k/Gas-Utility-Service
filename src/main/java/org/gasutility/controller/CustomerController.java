package org.gasutility.controller;

import org.gasutility.model.Customer;
import org.gasutility.model.GasConnectionRequest;
import org.gasutility.model.ServiceRequest;
import org.gasutility.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("")
    public String hello() {
        return "Hello World!";
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody Customer customer){

        String response = customerService.registerCustomer(customer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
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
    public ResponseEntity<ServiceRequest> trackRequest(@PathVariable("requestId") Integer requestId){
        return customerService.trackRequest(requestId);
    }
}
