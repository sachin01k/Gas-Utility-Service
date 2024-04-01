package org.gasutility.service;


import org.gasutility.dto.incoming.ServiceRequest;
import org.gasutility.dto.incoming.UpdateRequestDetails;
import org.gasutility.dto.outgoing.CustomerDetails;
import org.gasutility.exceptions.GasConnectionAlreadyExists;
import org.gasutility.model.CustomerEntity;
import org.gasutility.model.NewGasConnection;
import org.gasutility.model.ServiceRequestEntity;
import org.gasutility.enums.RequestType;
import org.gasutility.exceptions.InvalidCustomerId;
import org.gasutility.exceptions.InvalidRequestId;
import org.gasutility.dto.incoming.Customer;
import org.gasutility.dto.incoming.GasConnectionRequest;
import org.gasutility.dto.outgoing.ServiceRequestDetails;
import org.gasutility.repository.CustomerRepo;
import org.gasutility.repository.GasConnectionRepo;
import org.gasutility.repository.ServiceRequestRepo;
import org.gasutility.util.MailComposer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepo customerRepo;
    private final GasConnectionRepo gasRepo;
    private final ServiceRequestRepo serviceRepo;
    private final JavaMailSender javaMailSender;

    @Autowired
    public CustomerServiceImpl(CustomerRepo customerRepo, GasConnectionRepo gasRepo,
                               ServiceRequestRepo serviceRepo, JavaMailSender javaMailSender){

        this.customerRepo = customerRepo;
        this.gasRepo = gasRepo;
        this.serviceRepo = serviceRepo;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public String registerCustomer(Customer customerDetails) {

        CustomerEntity customer = new CustomerEntity();
        BeanUtils.copyProperties(customerDetails,customer);
        customer = customerRepo.save(customer);

        SimpleMailMessage message = MailComposer.customerRegistrationSuccessMessage(customer);
        javaMailSender.send(message);

        return "Successfully Registered! Customer ID : " + customer.getCustomerId();
    }

    @Override
    public String newConnection(GasConnectionRequest connection) {

        Optional<CustomerEntity> optional = customerRepo.findById(connection.getCustomerId());
        CustomerEntity customer = null;

        if(optional.isEmpty()){
            throw new InvalidCustomerId("Invalid Customer ID.. Try again!!");
        }

        customer = optional.get();

        if(customer.getNewGasConnection() != null)
            throw new GasConnectionAlreadyExists("Gas Connection Already Exists! One Connection per person.");

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

        if(optional.isEmpty()){
            throw new InvalidCustomerId("Invalid Customer ID.. Try again!!");
        }

        customer = optional.get();

        ServiceRequestEntity service = new ServiceRequestEntity();
        service.setRequestType(Enum.valueOf(RequestType.class,request.getRequestType()));
        service.setDescription(request.getDescription());
        service.setCustomer(customer);

        service = serviceRepo.save(service);

        SimpleMailMessage message = MailComposer.newServiceRequestGenerationMessage(service);
        javaMailSender.send(message);

        return "Your Request has been Successfully submitted! Request ID : " + service.getRequestId();
    }

    @Override
    public ResponseEntity<ServiceRequestDetails> trackRequest(Integer requestId) {

       Optional<ServiceRequestEntity> optional = serviceRepo.findById(requestId);
        ServiceRequestEntity request = null;

       if(optional.isEmpty()){
           throw new InvalidRequestId("Invalid Request Id.. enter valid Id number!");
       }

       request = optional.get();
       ServiceRequestDetails service = ServiceRequestDetails.builder()
               .requestId(request.getRequestId())
               .requestType(String.valueOf(request.getRequestType()))
               .status(String.valueOf(request.getStatus()))
               .description(request.getDescription())
               .requestDate(request.getRequestDate())
               .updateDate(request.getUpdateDate())
               .build();

       return new ResponseEntity<>(service, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ServiceRequestDetails>> viewAllRequest(Integer customerId){

        CustomerEntity customer = null;
        List<ServiceRequestDetails> list = new ArrayList<>();
        Optional<CustomerEntity> optional = customerRepo.findById(customerId);

        if(optional.isEmpty()){
            throw new InvalidCustomerId("Invalid Customer ID.. Try again!!");
        }

        customer = optional.get();
        List<ServiceRequestEntity> requests = customer.getServiceRequest();

        for(ServiceRequestEntity request : requests){

            list.add(ServiceRequestDetails.builder()
                    .requestId(request.getRequestId())
                    .requestType(String.valueOf(request.getRequestType()))
                    .status(String.valueOf(request.getStatus()))
                    .description(request.getDescription())
                    .requestDate(request.getRequestDate())
                    .updateDate(request.getUpdateDate())
                    .build());
        }

        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerDetails> getCustomerDetails(Integer customerId) {

        CustomerEntity customer = null;
        Optional<CustomerEntity> optional = customerRepo.findById(customerId);

        if(optional.isEmpty()){
            throw new InvalidCustomerId("Invalid Customer ID.. Try again!!");
        }

        customer = optional.get();
        CustomerDetails customerDetails = new CustomerDetails();
        BeanUtils.copyProperties(customer,customerDetails);

        return new ResponseEntity<>(customerDetails, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateCustomerDetails(CustomerDetails customerDetails) {

        CustomerEntity customer = null;
        Optional<CustomerEntity> optional = customerRepo.findById(customerDetails.getCustomerId());

        if(optional.isEmpty()){
            throw new InvalidCustomerId("Invalid Customer ID.. Try again!!");
        }

        customer = optional.get();
        BeanUtils.copyProperties(customerDetails,customer);

        customerRepo.save(customer);

        return new ResponseEntity<>("Customer Details Updated!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateRequestDetails(UpdateRequestDetails requestDetails) {

        Optional<ServiceRequestEntity> optional = serviceRepo.findById(requestDetails.getRequestId());
        ServiceRequestEntity request = null;

        if(optional.isEmpty()){
            throw new InvalidRequestId("Invalid Request Id.. enter valid Id number!");
        }

        request = optional.get();
        request.setRequestType(Enum.valueOf(RequestType.class,requestDetails.getRequestType()));
        request.setDescription(requestDetails.getDescription());

        serviceRepo.save(request);
        return new ResponseEntity<>("Request Details Updated!",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteServiceRequest(Integer requestId) {

        Optional<ServiceRequestEntity> optional = serviceRepo.findById(requestId);
        ServiceRequestEntity request = null;

        if(optional.isEmpty()){
            throw new InvalidRequestId("Invalid Request Id.. enter valid Id number!");
        }

        request = optional.get();
        serviceRepo.deleteById(request.getRequestId());
        return new ResponseEntity<>("Service Request Deleted Successfully!!",HttpStatus.OK);
    }
}
