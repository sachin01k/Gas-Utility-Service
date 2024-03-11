package org.gasutility.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerRestApi {

    @GetMapping("/")
    public String hello(){
        return "hello world ";
    }

}