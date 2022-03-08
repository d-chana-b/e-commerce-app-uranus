package com.myapp.uranuscapstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapp.uranuscapstone.model.User;

import com.myapp.uranuscapstone.repository.UserRepository;
import com.myapp.uranuscapstone.service.ProductService;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	
	/*
	@GetMapping("/index")
	public String userPage() {
		return "/User/index";
	}
	 */
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
	    model.addAttribute("user", new User());
	     
	    return "/User/Registration";
	}
	
	@GetMapping("/login")
	public String showLogin() {
		return "/User/Login";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
	    //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    //String encodedPassword = passwordEncoder.encode(user.getPassword());
	    //user.setPassword(encodedPassword);
	     
	    userRepo.save(user);
	     
	    return "/User/register_success";
	}
	
	////// INDEX SESSION
	// @RequestMapping ("/productService")
		@Autowired
		private ProductService productService;
		
		
		@GetMapping("/index") 		// or ("/index-All"), hindi ko kasi mahanap yung button name ng "All" na category
		public String listProduct(Model model)
		{
		model.addAttribute("products", productService.getAllProduct());
		return "/User/index";			// or "/index-All", again hindi ako sigurado
		}
		
	
}
