package org.gas_utility.service.implementation;

import org.gas_utility.dto.incoming.ServiceRequest;
import org.gas_utility.dto.incoming.UpdateRequestDetails;
import org.gas_utility.dto.outgoing.CustomerDetails;
import org.gas_utility.dto.outgoing.GasConnectionRequestDetails;
import org.gas_utility.exceptions.*;
import org.gas_utility.model.CustomerEntity;
import org.gas_utility.model.NewGasConnection;
import org.gas_utility.model.ServiceRequestEntity;
import org.gas_utility.enums.RequestType;
import org.gas_utility.dto.incoming.Customer;
import org.gas_utility.dto.outgoing.ServiceRequestDetails;
import org.gas_utility.repository.CustomerRepo;
import org.gas_utility.repository.GasConnectionRepo;
import org.gas_utility.repository.ServiceRequestRepo;
import org.gas_utility.service.ICustomerService;
import org.gas_utility.util.MailComposer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * CustomerServiceImpl is implementation class for CustomerService Interface.
 * It is Service layer of the api with business logic.
 * @author Sachin Kamble
 * @since 17.0
 */
@Service
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepo customerRepo;
    private final GasConnectionRepo gasRepo;
    private final ServiceRequestRepo serviceRepo;
    private final JavaMailSender javaMailSender;

    /**
     * Parameterised Constructor instantiates fields;
     * @param customerRepo
     * @param gasRepo
     * @param serviceRepo
     * @param javaMailSender
     */
    @Autowired
    public CustomerServiceImpl(CustomerRepo customerRepo, GasConnectionRepo gasRepo,
                               ServiceRequestRepo serviceRepo, JavaMailSender javaMailSender){

        this.customerRepo = customerRepo;
        this.gasRepo = gasRepo;
        this.serviceRepo = serviceRepo;
        this.javaMailSender = javaMailSender;
    }

    /**
     * registerCustomer() method provides registration process for customer,
     * to connect with Gas Utility Service Organization. A detailed mail is
     * sent to registered customer.
     *
     * @param customerDetails
     * @return returns String with unique customerId.
     * @exception DuplicatePhoneNumber,DuplicateAadharNumber,DuplicateEmail
     */
    @Override
    public String registerCustomer(Customer customerDetails) {

        String phone = customerRepo.findByPhone(customerDetails.getPhone());
        String email = customerRepo.findByEmail(customerDetails.getEmail());
        String aadhar = customerRepo.findByAadhar(customerDetails.getAadhar());

        if(phone != null)
            throw new DuplicatePhoneNumber("Phone Number Already Exists!");

        if(email != null)
            throw new DuplicateEmail("Email Id Already Exists!");

        if(aadhar != null)
            throw new DuplicateAadharNumber("Aadhar Number Already Exists");

        CustomerEntity customer = new CustomerEntity();
        BeanUtils.copyProperties(customerDetails,customer);
        customer = customerRepo.save(customer);

        SimpleMailMessage message = MailComposer.customerRegistrationSuccessMessage(customer);
        javaMailSender.send(message);

        return "Successfully Registered! Customer ID : " + customer.getCustomerId();
    }

    /**
     * Registered Customer's can request new gas connection.
     * If customer already have a gas connection will not be provided another as per
     * Indian Law.
     * @param customerId
     * @return returns String with request ID, else throws exception if already has a connection.
     * @exception InvalidCustomerId,GasConnectionAlreadyExists
     */
    @Override
    public String newConnection(Integer customerId) {

        Optional<CustomerEntity> optional = customerRepo.findById(customerId);

        if(optional.isEmpty()){
            throw new InvalidCustomerId("Invalid Customer ID.. Try again!!");
        }

        CustomerEntity customer = optional.get();

        if(customer.getNewGasConnection() != null)
            throw new GasConnectionAlreadyExists("Gas Connection Already Exists! One Connection per person.");

        NewGasConnection gasConnection = new NewGasConnection();
        gasConnection.setAddress(customer.getAddress());
        gasConnection.setCustomer(customer);

        gasConnection = gasRepo.save(gasConnection);

        SimpleMailMessage message = MailComposer.newGasConnectionRequestMessage(gasConnection);
        javaMailSender.send(message);

        return "New Gas connection request Successful! request Id : " + gasConnection.getConnectionId();
    }

    /**
     * viewGasConnectionRequestDetails() method provides customer's feature to
     * track status of new Gas connection request.
     * @param connectionRequestID
     * @return returns ResponseEntity of GasConnectionRequestDetails type object with HttpStatus code.
     * @exception InvalidGasConnectionRequestId
     */
    @Override
    public ResponseEntity<GasConnectionRequestDetails> viewGasConnectionRequestDetails(Integer connectionRequestID) {

        Optional<NewGasConnection> optional = gasRepo.findById(connectionRequestID);

        if(optional.isEmpty())
            throw new InvalidGasConnectionRequestId("Gas Connection request ID invalid!");

        NewGasConnection connection = optional.get();

        return new ResponseEntity<>(GasConnectionRequestDetails.builder()
                .connectionId(connection.getConnectionId())
                .address(connection.getAddress())
                .status(String.valueOf(connection.getStatus()))
                .requestDate(connection.getRequestDate())
                .updateDate(connection.getUpdateDate())
                .build(), HttpStatus.OK);
    }

    /**
     * newServiceRequest() method provides customer's a support to request various services.
     * Customer can process 3 types of requests COMPLAINT, MAINTENANCE, EMERGENCY.
     * @param request
     * @return String with message with newly generated request ID.
     * @exception InvalidCustomerId,InvalidRequestType
     */
    @Override
    public String newServiceRequest(ServiceRequest request) {

        Optional<CustomerEntity> optional = customerRepo.findById(request.getCustomerId());
        CustomerEntity customer = null;

        if(optional.isEmpty()){
            throw new InvalidCustomerId("Invalid Customer ID.. Try again!!");
        }

        customer = optional.get();
        List<String> type = Arrays.stream(RequestType.values()).map(RequestType::toString).collect(toList());

        if(!type.contains(request.getRequestType()))
            throw new InvalidRequestType("Select Valid Request type!");

        ServiceRequestEntity service = new ServiceRequestEntity();
        service.setRequestType(Enum.valueOf(RequestType.class,request.getRequestType()));
        service.setDescription(request.getDescription());
        service.setCustomer(customer);

        service = serviceRepo.save(service);

        SimpleMailMessage message = MailComposer.newServiceRequestGenerationMessage(service);
        javaMailSender.send(message);

        return "Your Request has been Successfully submitted! Request ID : " + service.getRequestId();
    }

    /**
     * Customer can track service request status using trackRequest() method.
     * @param requestId
     * @return ResponseEntity object of type ServiceRequestDetails.
     * @exception InvalidRequestId
     */
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

    /**
     * viewAllRequest() method retrieves all service requests created by particular customer.
     * @param customerId
     * @return ResponseEntity object of type List<ServiceRequestDetails>.
     * @exception InvalidCustomerId
     */
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

    /**
     * getCustomerDetails() method provides customer's support to view Customer
     * details.
     * @param customerId
     * @return ResponseEntity object of type CustomerDetails.
     * @exception InvalidCustomerId
     */
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

    /**
     * Customer's details update process in implemented by
     * updateCustomerDetails() method.
     * @param customerDetails
     * @return ResponseEntity object of type String.
     * @exception InvalidCustomerId
     */
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

    /**
     * Customer's service requests is updated using updateRequestDetails() method.
     * @param requestDetails
     * @return ResponseEntity object of type String.
     * @exception InvalidRequestId
     */
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

    /**
     * Customer can delete service request made earlier.
     * @param requestId
     * @return ResponseEntity object of type String.
     * @exception InvalidRequestId
     */
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
