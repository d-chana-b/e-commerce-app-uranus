package com.myapp.uranuscapstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.uranuscapstone.model.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart,Integer>{

}
