package org.gasutility.util;

import org.gasutility.model.CustomerEntity;
import org.gasutility.model.ServiceRequestEntity;
import org.springframework.mail.SimpleMailMessage;

public class MailComposer {

    public static SimpleMailMessage customerRegistrationSuccessMessage(CustomerEntity customer){
        String text = "Dear "+customer.getName()+", \n"
                +"\n"
                +"Welcome to Gas Utility Service, we are happy you choose us as your Gas Service Provider.\n"
                +"Your Registration has been done Successfully, below is your permanent Customer ID.\n"
                +"Unique Customer Identification : "+customer.getCustomerId()+ "\n"
                +"We promise you to fulfill all services required by you.\n"
                +"Service At your Door!!\n"
                +"\n"
                +"Note : Do not Share your Unique Customer Identification Number to anyone.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(text);
        message.setSubject("Registration Successful!");
        message.setFrom("systempayroll73@gmail.com");
        message.setTo(customer.getEmail());

        return message;
    }

    public static SimpleMailMessage newServiceRequestGenerationMessage(ServiceRequestEntity service){
        String text = "Dear "+service.getCustomer().getName()+",\n"
                +"\n"
                +"We have recieved your service request. Your request ID is " + service.getRequestId() + ".\n"
                +"Our Customer Support Representative will review your request and will provide you Solution.\n"
                +"You can track your service request status using your Request Id.\n"
                +"We are thankful for your co-operation.\n"
                +"Service At your Door!!\n"
                +"\n"
                +"Note : Do not Share your Unique Customer Identification Number to anyone.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(text);
        message.setSubject("Registration Successful!");
        message.setFrom("systempayroll73@gmail.com");
        message.setTo(service.getCustomer().getEmail());

        return message;
    }
}
