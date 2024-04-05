package org.gas_utility.controller;

import org.gas_utility.dto.incoming.Customer;
import org.gas_utility.dto.incoming.ServiceRequest;
import org.gas_utility.dto.incoming.UpdateRequestDetails;
import org.gas_utility.dto.outgoing.CustomerDetails;
import org.gas_utility.dto.outgoing.GasConnectionRequestDetails;
import org.gas_utility.dto.outgoing.ServiceRequestDetails;

import org.gas_utility.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CustomerController is a Rest Controller class which handle's Requests calls from user.
 * @author Sachin Kamble
 * @since 17.0
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
     * @return ResponseEntity with response and appropriate Http Status Code.
     */
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody Customer customer, BindingResult result){

        String response = customerService.registerCustomer(customer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * GetCustomerDetails takes customer ID as input, gives in return customer details.
     * @param customerId
     * @return ResponseEntity with response and appropriate Http Status Code.
     */
    @GetMapping("customer-details/{customerId}")
    public ResponseEntity<CustomerDetails> getCustomerDetails(@PathVariable("customerId") Integer customerId){
        return customerService.getCustomerDetails(customerId);
    }

    /**
     * UpdateCustomerDetails method updates the customer details as per customer requirement.
     * @param customerDetails
     * @return ResponseEntity with response and appropriate Http Status Code.
     */
    @PutMapping("update-customer-details")
    public ResponseEntity<String> updateCustomerDetails(@RequestBody CustomerDetails customerDetails){
        return customerService.updateCustomerDetails(customerDetails);
    }

    /**
     * NewConnection method implements new Gas Connection process if customer
     * doesn't have gas connection.
     * @param customerId
     * @return ResponseEntity with response and appropriate Http Status Code.
     */
    @GetMapping("new-gas-connection/{customerId}")
    public ResponseEntity<String> newConnection(@PathVariable("customerId") Integer customerId){

        String response = customerService.newConnection(customerId);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    /**
     * Tracks request call, verify request status.
     * @param connectionRequestID
     * @return ResponseEntity with response and appropriate Http Status Code.
     */
    @GetMapping("view-gas-connection-request/{requestId}")
    public ResponseEntity<GasConnectionRequestDetails> viewGasConnectionDetails(@PathVariable("requestId") Integer connectionRequestID){
        return  customerService.viewGasConnectionRequestDetails(connectionRequestID);
    }

    /**
     * Customer can request services via serviceRequest() method.
     * @param request
     * @return ResponseEntity with response and appropriate Http Status Code
     */
    @PostMapping("service-request")
    public ResponseEntity<String> serviceRequest(@RequestBody ServiceRequest request){

        String response = customerService.newServiceRequest(request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    /**
     * Tracks customer's service request and status, informs customer.
     * @param requestId
     * @return ResponseEntity with response and appropriate Http Status Code.
     */
    @GetMapping("track-request/{requestId}")
    public ResponseEntity<ServiceRequestDetails> trackRequest(@PathVariable("requestId") Integer requestId){
        return customerService.trackRequest(requestId);
    }

    /**
     * Customer can view previous service requests, there status and processing.
     * @param customerId
     * @return ResponseEntity with response and appropriate Http Status Code.
     */
    @GetMapping("track-all-requests/{customerId}")
    public ResponseEntity<List<ServiceRequestDetails>> viewAllRequest(@PathVariable("customerId") Integer customerId){
        return customerService.viewAllRequest(customerId);
    }

    /**
     * Customer can update request details and edit fields data.
     * @param requestDetails
     * @return ResponseEntity with response and appropriate Http Status Code.
     */
    @PutMapping("update-request-Details")
    public ResponseEntity<String> updateRequestDetails(@RequestBody UpdateRequestDetails requestDetails){
        return customerService.updateRequestDetails(requestDetails);
    }

    /**
     * Deleting unwanted requests made by customer through deleteServiceRequest().
     * @param requestId
     * @return ResponseEntity with response and appropriate Http Status Code.
     */
    @DeleteMapping("delete-request/{requestId}")
    public ResponseEntity<String> deleteServiceRequest(@PathVariable("requestId") Integer requestId){
        return customerService.deleteServiceRequest(requestId);
    }
}
