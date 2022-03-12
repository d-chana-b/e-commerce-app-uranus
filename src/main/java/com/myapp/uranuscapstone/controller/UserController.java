package com.myapp.uranuscapstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapp.uranuscapstone.model.Cart;
import com.myapp.uranuscapstone.model.User;
import com.myapp.uranuscapstone.repository.CartRepository;
import com.myapp.uranuscapstone.repository.ProductRepository;
import com.myapp.uranuscapstone.repository.UserRepository;
import com.myapp.uranuscapstone.service.CategoryService;
//import com.myapp.uranuscapstone.service.CustomProductService;
import com.myapp.uranuscapstone.service.ProductService;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	CartRepository cartRepo;
	
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
		
		//@Autowired
		//private CustomProductService customProductService;
		
		@GetMapping("/index")
		public String listProduct(Model model)
		{
		 model.addAttribute("products", productService.getAllProduct());
		 model.addAttribute("category", categoryService.getAllCategory());
		//model.addAttribute("products", customProductService.getAllByName());
		return "/User/index";
		}
		
		@GetMapping("/index/product/{id}")
		public String categorylist(@PathVariable(value = "id") String id, Model model)
		{
		model.addAttribute("products", productService.getByCategoryName(id));
		model.addAttribute("category", categoryService.getAllCategory());
		// model.addAttribute("products", customProductService.getAllByName());
		return "redirect:/index";
		}
		
		// cart controller
		@GetMapping("/cart")
		public String cartUser() {
			return "/User/cart";
		}
		
		@GetMapping("/index/category/{id}")
		public String shopByCategory(Model model,
				@PathVariable int id
				) {
			model.addAttribute("category", categoryService.getAllCategory());
			 model.addAttribute("products", productService.getAllProductByCategoryId(id));
			return "/User/index";
		}

		
	}
