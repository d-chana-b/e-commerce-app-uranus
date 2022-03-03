package com.myapp.uranuscapstone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@GetMapping("/admin")
	public String adminHome() {
		return "/Admin/adminHome";
	}
	
	@GetMapping("/admin/product")
	public String adminProduct() {
		return "/Admin/productlist";
	}
	@GetMapping("/admin/product/add")
	public String getProduct() {
		return "/Admin/addproduct";
	}
	

}
