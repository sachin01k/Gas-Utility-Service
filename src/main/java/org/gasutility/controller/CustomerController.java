package org.gasutility.controller;

import org.gasutility.dto.Request;
import org.gasutility.models.ServiceRequest;
import org.gasutility.service.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class CustomerController {
	
	String msg=null;
	
	@Autowired
	private Customer service;
	
    @GetMapping("/")
    public String hello(Model model){
    	
    	model.addAttribute("msg", msg);
    	model.addAttribute("request", new Request());
    	model.addAttribute("serRequest", new ServiceRequest());
        return "index";
    }
    
    @GetMapping("/request")
    public String newRequest(@ModelAttribute("request")Request request, Model model) {
    	
    	String msg = service.newRequest(request);
    	model.addAttribute("msg", msg);
    	model.addAttribute("request", new Request());
    	model.addAttribute("serRequest", new ServiceRequest());
    	return "index";
    }
    
    @GetMapping("/track")
    public String trackRequest(@RequestParam("requestId") Integer requestId, Model model) {
    	
    	ServiceRequest request = service.trackRequest(requestId);
    	model.addAttribute("msg", msg);
    	model.addAttribute("request", new Request());
    	model.addAttribute("serRequest", request);
    	
    	return "index";
    }
}
