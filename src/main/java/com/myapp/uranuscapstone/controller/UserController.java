package com.myapp.uranuscapstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.myapp.uranuscapstone.model.Users;
import com.myapp.uranuscapstone.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/index")
	public String userPage() {
		return "/User/index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
	    model.addAttribute("user", new Users());
	     
	    return "/User/Registration";
	}
	
	@PostMapping("/process_register")
	public String processRegister(Users user) {
	    //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    //String encodedPassword = passwordEncoder.encode(user.getPassword());
	    //user.setPassword(encodedPassword); 
	    userRepo.save(user);
	     
	    return "/User/register_success";
	}
}
