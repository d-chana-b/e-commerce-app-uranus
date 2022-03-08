package com.myapp.uranuscapstone.service;

import java.util.List;
import java.util.Optional;

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

	public Optional<Product> getProductById(long id) {
		return productRepository.findById(id);
	}

	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	
	public void deleteProductById(Long id) {
		productRepository.deleteById(id);	
	}
	
	public List<Product> sortedName(String categoryName) {
		return productRepository.findAll();
	}
}

