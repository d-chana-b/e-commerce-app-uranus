package com.myapp.uranuscapstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.myapp.uranuscapstone.custom.CustomUserDetails;
import com.myapp.uranuscapstone.model.User;
import com.myapp.uranuscapstone.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User getAuthUser(Authentication auth) {
		Object principal = auth.getPrincipal();
		return ((CustomUserDetails) principal).getUser();
	}
}
