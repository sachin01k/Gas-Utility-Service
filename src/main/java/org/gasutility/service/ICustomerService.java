package org.gasutility.service;

import org.gasutility.dto.incoming.Customer;
import org.gasutility.dto.incoming.GasConnectionRequest;
import org.gasutility.dto.incoming.ServiceRequest;
import org.gasutility.dto.incoming.UpdateRequestDetails;
import org.gasutility.dto.outgoing.CustomerDetails;
import org.gasutility.dto.outgoing.ServiceRequestDetails;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICustomerService {

    public String registerCustomer(Customer customer);
    public String newConnection(GasConnectionRequest connection);
    public String newServiceRequest(ServiceRequest request);

    public ResponseEntity<ServiceRequestDetails> trackRequest(Integer requestId);

    ResponseEntity<List<ServiceRequestDetails>> viewAllRequest(Integer customerId);

    ResponseEntity<CustomerDetails> getCustomerDetails(Integer customerId);

    ResponseEntity<String> updateCustomerDetails(CustomerDetails customerDetails);

    ResponseEntity<String> updateRequestDetails(UpdateRequestDetails requestDetails);

    ResponseEntity<String> deleteServiceRequest(Integer requestId);
}
