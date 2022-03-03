package com.myapp.uranuscapstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.myapp.uranuscapstone.model.Product;
import com.myapp.uranuscapstone.repository.ProductRepository;
import com.myapp.uranuscapstone.service.ProductService;

@Controller
public class AdminController {
	
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductService productService;
	
	
	@GetMapping("/admin")
	public String adminHome() {
		return "/Admin/adminHome";
	}
	
	/* @GetMapping("/admin/product")
	public String adminProduct() {
		return "/Admin/productlist";
	}
	 */
	
	/* @GetMapping("/admin/product/add")
	public String getProduct() {
		return "/Admin/addproduct";
	} 
	 */
	
	@GetMapping("/admin/product")
	public String listProduct(Model model) {
		model.addAttribute("students", productService.getAllProduct());
		return "/Admin/adminHome";
	}
	

	@GetMapping("/admin/product/add")
	public String createProductForm(Model model) {
		
		// create student object to hold student form data

		model.addAttribute("product", new Product());
		return "/Admin/addproduct";  
		
		}
		
		
	
	
	@PostMapping("/admin/product/add")
	public String saveProduct(@ModelAttribute("product") Product product) {
		productService.saveProduct(product);
		return "redirect:/adminHome";
	}
	
	@GetMapping("/product/edit/{id}")
	public String editProductForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", productService.getProductById(id));
		return "/Admin/addproduct";
	}

	@PostMapping("/product/{id}")
	public String updateProduct(@PathVariable Long id,
			@ModelAttribute("product") Product product,
			Model model) {
	
		// get Product from database by id
				Product existingProduct = productService.getProductById(id);
				existingProduct.setId(id);
				existingProduct.setProductName(product.getProductName());
				//existingProduct.setCategory(product.getCategory());
				existingProduct.setQuantity(product.getQuantity());
				existingProduct.setPrice(product.getPrice());
				existingProduct.setProductImage(product.getProductImage());

			// save updated student object
				productService.updateProduct(existingProduct);
				return "redirect:/adminHome";		
			}
			
			// handler method to handle delete student request
			
			@GetMapping("/product/{id}")
			public String deleteProduct(@PathVariable Long id) {
				productService.deleteProductById(id);
				return "redirect:/adminHome";
			}
	
	
	
	
	
	
}
