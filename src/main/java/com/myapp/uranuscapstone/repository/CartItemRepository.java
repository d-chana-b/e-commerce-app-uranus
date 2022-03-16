package com.myapp.uranuscapstone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.uranuscapstone.model.CartItems;
import com.myapp.uranuscapstone.model.Product;
import com.myapp.uranuscapstone.model.User;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems,Integer>{
	public List<CartItems> findByUser(User user);
	public CartItems findByUserAndProduct(User user, Product product);
}
