package org.gasutility.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.gasutility.entities.CustomerEntity;
import org.gasutility.entities.NewGasConnection;
import org.gasutility.entities.ServiceRequestEntity;
import org.gasutility.model.Customer;
import org.gasutility.model.GasConnectionRequest;
import org.gasutility.model.ServiceRequest;
import org.gasutility.repository.CustomerRepo;
import org.gasutility.repository.GasConnectionRepo;
import org.gasutility.repository.ServiceRequestRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private GasConnectionRepo gasRepo;

    @Autowired
    private ServiceRequestRepo serviceRepo;

    @Override
    public String registerCustomer(Customer customer) {

        CustomerEntity entity = new CustomerEntity();
        BeanUtils.copyProperties(customer,entity);
        entity = customerRepo.save(entity);

        return "Successfully Registered! Customer ID : " + entity.getCustomerId();
    }

    @Override
    public String newConnection(GasConnectionRequest connection) {

        Optional<CustomerEntity> optional = customerRepo.findById(connection.getCustomerId());
        CustomerEntity customer = null;

        if(optional.isPresent())
            customer = optional.get();

        NewGasConnection gasConnection = new NewGasConnection();
        gasConnection.setAddress(connection.getAddress());
        gasConnection.setCustomer(customer);

        gasConnection = gasRepo.save(gasConnection);

        return "New Gas connection request Successful! request Id : " + gasConnection.getConnectionId();
    }

    @Override
    public String newServiceRequest(ServiceRequest request) {

        Optional<CustomerEntity> optional = customerRepo.findById(request.getCustomerId());
        CustomerEntity customer = null;

        if(optional.isPresent())
            customer = optional.get();

        ServiceRequestEntity service = new ServiceRequestEntity();
        service.setRequestType(request.getRequestType());
        service.setDescription(request.getDescription());
        service.setCustomer(customer);

        service = serviceRepo.save(service);

        return "Your Request has been Successfully submitted! Request ID : " + service.getRequestId();
    }

    @Override
    public ResponseEntity< ServiceRequest> trackRequest(Integer requestId) {

       Optional<ServiceRequestEntity> optional = serviceRepo.findById(requestId);
        ServiceRequestEntity service = null;

       if(optional.isPresent())
           service = optional.get();

       ServiceRequest request = new ServiceRequest();
       request.setCustomerId(service.getCustomer().getCustomerId());
       request.setStatus(service.getStatus());
       request.setRequestType(service.getRequestType());
       request.setDescription(service.getDescription());
       request.setRequestDate(service.getRequestDate());
       request.setUpdateDate(service.getUpdateDate());

       return new ResponseEntity<>(request, HttpStatus.OK);
    }

}
