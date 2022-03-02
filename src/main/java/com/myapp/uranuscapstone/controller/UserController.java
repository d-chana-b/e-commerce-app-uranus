package com.myapp.uranuscapstone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/index")
	public String userPage() {
		return "/User/index";
	}
}
