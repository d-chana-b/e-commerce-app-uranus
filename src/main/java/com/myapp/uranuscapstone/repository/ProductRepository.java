package com.myapp.uranuscapstone.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myapp.uranuscapstone.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

	//@Query("select * from product where category_name = ?2")
	//@Query( value = "SELECT * FROM product u WHERE u.category_name = 1", nativeQuery = true)
	//@Query("SELECT u FROM product u WHERE u.category_name = :categoryName")
	//List<Product> findAllByName(@Param("categoryName") String name);
	
}
