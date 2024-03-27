package org.gasutility.service;

import org.gasutility.entities.NewGasConnection;
import org.gasutility.entities.ServiceRequestEntity;
import org.gasutility.model.Customer;
import org.gasutility.model.GasConnectionRequest;
import org.gasutility.model.ServiceRequest;
import org.springframework.http.ResponseEntity;

public interface ICustomerService {

    public String registerCustomer(Customer customer);
    public String newConnection(GasConnectionRequest connection);
    public String newServiceRequest(ServiceRequest request);

    public ResponseEntity< ServiceRequest> trackRequest(Integer requestId);
}
