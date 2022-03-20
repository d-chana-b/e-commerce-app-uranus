package com.myapp.uranuscapstone.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myapp.uranuscapstone.custom.CustomUserDetails;
import com.myapp.uranuscapstone.model.User;
import com.myapp.uranuscapstone.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		/*
		 * User user = userRepo.findByEmail(username); if(user == null) { throw new
		 * UsernameNotFoundException("User not found"); } return new
		 * CustomUserDetails(user);
		 */
		Optional<User> user = userRepo.findUserByEmail(email);
		user.orElseThrow(() -> new UsernameNotFoundException("User not found!"));
		return user.map(CustomUserDetails::new).get();
	}

}
