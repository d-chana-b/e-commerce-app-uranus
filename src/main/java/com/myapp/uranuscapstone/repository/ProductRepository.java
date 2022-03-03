package com.myapp.uranuscapstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.uranuscapstone.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

}
