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

	public Optional<Product> getProductById(Long id) {
		return productRepository.findById(id);
	}
	
	public Product showProductById(Long id) {
		Optional<Product> optional = productRepository.findById(id);
		Product product = null;
		if (optional.isPresent()) {
			product = optional.get();
		} else {
			throw new RuntimeException(" Category not found for id :: " + id);
		}
		   return product;
	}

	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	
	public void deleteProductById(Long id) {
		productRepository.deleteById(id);	
	}
	
	public List<Product> getAllProductByCategoryId(int id){
		return productRepository.findAllProductByCategoryId(id);
	}
	
	
	public List<Product> getByCategoryName(String CategoryName){
		return productRepository.findByCategoryName(CategoryName);
	}
}

