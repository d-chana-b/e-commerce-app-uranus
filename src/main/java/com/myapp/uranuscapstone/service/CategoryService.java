package com.myapp.uranuscapstone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.uranuscapstone.model.Category;
import com.myapp.uranuscapstone.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public List<Category> getAllCategory() {
		return  categoryRepository.findAll();
	}

	
	/*
	 * public Category showCategory(Long id) { Optional<Category> optional=
	 * categoryRepository.findById(id);
	 * 
	 * 
	 */
		
	/*	Category category=null;
		if(optional.isPresent()) {
			category=optional.get();
		} else {
			throw new RuntimeException("Category not found for id::" + id);
			
		}
		return category;
		}*/

	public void addCategory(Category category) {
		categoryRepository.save(category);

	}
}