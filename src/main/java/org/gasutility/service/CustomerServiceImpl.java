package org.gasutility.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.gasutility.entities.CustomerEntity;
import org.gasutility.model.Customer;
import org.gasutility.repository.CustomerRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public String registerCustomer(Customer customer) {

        CustomerEntity entity = new CustomerEntity();
        BeanUtils.copyProperties(customer,entity);
        entity = customerRepo.save(entity);

        return "Successfully Registered! Customer ID : " + entity.getId();
    }
}
