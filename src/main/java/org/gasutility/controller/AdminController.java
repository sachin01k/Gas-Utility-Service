package org.gasutility.controller;

import java.util.List;

import org.gasutility.dto.AdminLogin;
import org.gasutility.models.ServiceRequest;
import org.gasutility.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	
	@Autowired
	private IAdminService service;
	
	@GetMapping("/")
    public String login(Model model){
    	
    	model.addAttribute("admin", new AdminLogin());
    			
        return "admin";
    }
    
    @PostMapping("/verify")
    public String verify(@ModelAttribute("admin") AdminLogin adminLogin, Model model)
    {
    	
  	 	boolean status = service.verify(adminLogin);
    	
    	if(status) {
    		
    		List<ServiceRequest> list = service.getNewPendingRequest();
    		model.addAttribute("list", list);
    		
    		return "home";
    	}
    		
    	model.addAttribute("msg", "Invalid Login");
    	return "index";
    }

}
