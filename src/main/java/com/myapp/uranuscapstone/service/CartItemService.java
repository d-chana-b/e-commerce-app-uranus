package com.myapp.uranuscapstone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.uranuscapstone.model.CartItems;
import com.myapp.uranuscapstone.model.Product;
import com.myapp.uranuscapstone.model.User;
import com.myapp.uranuscapstone.repository.CartItemRepository;
import com.myapp.uranuscapstone.repository.ProductRepository;
@Service
public class CartItemService {
	@Autowired
	CartItemRepository cartItemRepository;
	
	@Autowired
	ProductRepository productRepo;
	
	
	public List<CartItems> index(User user){
		return cartItemRepository.findByUser(user);
	}
	
	public void addProduct(Product product, Integer quantity, User user) {
		Integer addedQuantity = quantity;
		CartItems cartItem = cartItemRepository.findByUserAndProduct(user, product);
		
		
		if(cartItem != null) {
			addedQuantity = cartItem.getQuantity() + quantity;
			cartItem.setQuantity(addedQuantity);
		}else {
			cartItem = new CartItems();
			cartItem.setQuantity(quantity);
			cartItem.setProduct(product);
			cartItem.setUser(user);
		}
		cartItemRepository.save(cartItem);
	}
	
	public void save(CartItems cartItem) {
		cartItemRepository.save(cartItem);
	}
	
	public CartItems show(Integer id) {
		Optional<CartItems> optional = cartItemRepository.findById(id);
		CartItems cartItem = null;
		if (optional.isPresent()) {
			cartItem = optional.get();
		} else {
			throw new RuntimeException(" Cartitem not found for id :: " + id);
		}
		return cartItem;
	}
	
	public void delete(Integer id) {
		cartItemRepository.deleteById(id);
	}
}
