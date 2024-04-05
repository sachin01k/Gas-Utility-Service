package org.gas_utility.service;

import org.gas_utility.dto.incoming.Customer;
import org.gas_utility.dto.incoming.ServiceRequest;
import org.gas_utility.dto.incoming.UpdateRequestDetails;
import org.gas_utility.dto.outgoing.CustomerDetails;
import org.gas_utility.dto.outgoing.GasConnectionRequestDetails;
import org.gas_utility.dto.outgoing.ServiceRequestDetails;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * ICustomerService is Interface, it provides blueprint for Implementation classes.
 * @author Sachin Kamble
 * @since 17.0
 */
public interface ICustomerService {

    public String registerCustomer(Customer customer);
    public String newConnection(Integer connection);
    public String newServiceRequest(ServiceRequest request);

    public ResponseEntity<ServiceRequestDetails> trackRequest(Integer requestId);

    ResponseEntity<List<ServiceRequestDetails>> viewAllRequest(Integer customerId);

    ResponseEntity<CustomerDetails> getCustomerDetails(Integer customerId);

    ResponseEntity<String> updateCustomerDetails(CustomerDetails customerDetails);

    ResponseEntity<String> updateRequestDetails(UpdateRequestDetails requestDetails);

    ResponseEntity<String> deleteServiceRequest(Integer requestId);

    ResponseEntity<GasConnectionRequestDetails> viewGasConnectionRequestDetails(Integer connectionRequestID);
}
