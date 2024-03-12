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
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
    public String verify(@ModelAttribute("admin") AdminLogin adminLogin, Model model,HttpServletRequest request)
    {
    	HttpSession session = request.getSession();
    	
    	
  	 	boolean status = service.verify(adminLogin);
    	
    	if(status) {
    		session.setAttribute("username", adminLogin.getUsername());
    		List<ServiceRequest> list = service.getNewPendingRequest();
    		model.addAttribute("list", list);
    		
    		return "home";
    	}
    		
    	model.addAttribute("msg", "Invalid Login");
    	return "admin";
    }
    
    @GetMapping("/accept")
    public String acceptRequest(@RequestParam("requestId") Integer requestId, Model model,HttpServletRequest request) {
    	
    	HttpSession session = request.getSession();
    	String username = (String) session.getAttribute("username");
    	if(username==null || "".equals(username)) {
    		return "redirect:/admin/";
    	}
    	
    	boolean status = service.updateStatus(requestId);
    	
    	if(status)
    	{
    		List<ServiceRequest> list = service.getNewPendingRequest();
    		model.addAttribute("list", list);
    		model.addAttribute("msg", "Request Accepted");
    		return "home";
    	}
    	
    	List<ServiceRequest> list = service.getNewPendingRequest();
		model.addAttribute("list", list);
    	model.addAttribute("msg", "Something went wrong");
    	return "home";
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
    	 HttpSession session = request.getSession();
         session.invalidate();
    	
    	
        return "redirect:/admin/"; 
    }

}
