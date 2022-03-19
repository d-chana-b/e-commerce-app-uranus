package com.myapp.uranuscapstone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.myapp.uranuscapstone.model.OrderDetail;
import com.myapp.uranuscapstone.model.User;
import com.myapp.uranuscapstone.repository.OrderDetailRepository;

@Service
public class OrderDetailService {
	@Autowired
	OrderDetailRepository orderDetailRepository;

	public List<OrderDetail> index(User user) {
		return orderDetailRepository.findByUser(user);
	}
	
	public Optional<OrderDetail> getOrderDetailsById(long id) {
		return orderDetailRepository.findById(id);
	}
}
