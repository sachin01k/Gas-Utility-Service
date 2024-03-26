package org.gasutility.controller;

import org.gasutility.model.Customer;
import org.gasutility.service.CustomerServiceImpl;
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
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
