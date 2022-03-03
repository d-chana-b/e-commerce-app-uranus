package com.myapp.uranuscapstone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.uranuscapstone.model.Product;
import com.myapp.uranuscapstone.repository.ProductRepository;



@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	public Product getProductById(Long id) {
		return productRepository.findById(id).get();
	}

	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	
	public void deleteProductById(Long id) {
		productRepository.deleteById(id);	
	}
	
}

