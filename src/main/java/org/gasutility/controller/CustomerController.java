package org.gasutility.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;



@Controller
public class CustomerController {

    @GetMapping("/")
    public String hello(Model model){
    	
    	WebClient webClient = WebClient.create();
    	
    	String msg = webClient.get()
    			.uri("http://localhost:8080/customer/")
    			.retrieve()
    			.bodyToMono(String.class)
    			.block();
    	model.addAttribute("msg", msg);
    			
        return "index";
    }

}
