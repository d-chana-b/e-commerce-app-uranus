package com.myapp.uranuscapstone.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.myapp.uranuscapstone.dto.ProductDTO;
import com.myapp.uranuscapstone.model.Product;
import com.myapp.uranuscapstone.repository.ProductRepository;
import com.myapp.uranuscapstone.service.ProductService;

@Controller
public class AdminController {
	
	
	//@Autowired
	//ProductRepository productRepository;
	
	@Autowired
	ProductService productService;
	
	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/product-photos";
	/*
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
	
	@GetMapping("/admin")
	public String listProduct(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		return "/Admin/adminHome";
	}
	

	@GetMapping("/admin/product/add")
	public String createProductForm(Model model) {		
		// create student object to hold student form data
		model.addAttribute("product", new Product());
		return "/Admin/addproduct";  
		}
		
		
	 /*@GetMapping("/product/edit/{id}")
	public String editProductForm(@PathVariable Long id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		return "/Admin/addproduct";
	}

	
 /*	@PostMapping("/admin/product/add")
	public String saveProduct(@ModelAttribute("product") Product product) {
		productService.saveProduct(product);
		return "redirect:/admin";
	} */
	
	@PostMapping("/admin/product/add")
	public String saveProduct(
			@ModelAttribute("product") Product product,
			@RequestParam("productImage") MultipartFile file,
	@RequestParam("imageName")String imgName) throws IOException{
	
		Product existingProduct =new Product();
		existingProduct.setId(product.getId());
		existingProduct.setProductName(product.getProductName());
		existingProduct.setCategoryName(product.getCategoryName());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setProductImageName(product.getProductImageName());

	String imageUUId;
	if(!file.isEmpty()){
	  imageUUId=file.getOriginalFilename();
	  Path fileNameAndPath=Paths.get(uploadDirectory,imageUUId);
	  Files.write(fileNameAndPath,file.getBytes()); 
	}
	else{
		imageUUId=imgName;
		} 
	product.setProductImageName(imageUUId);
	productService.saveProduct(product);
	return "redirect:/admin";
	}
	

	
	
	@GetMapping("admin/update/{id}")
	public String updateProduct(@PathVariable int id, Model model) {
		Optional<Product> product = productService.getProductById(id);
	
/*
		Product product1 =new Product();
		product1.setId(product.getId());
		product1.setProductName(product.getProductName());
		product1.setCategoryName(product.getCategoryName());
		product1.setPrice(product.getPrice());
		product1.setProductImageName(product.getProductImageName());
		
		
	
		
		model.addAttribute("product", product.getId());
		
		
		return "/Admin/addproduct";
		
	}
	*/
	// get Product from database by id
		//	Product existingProduct = productService.getProductById(id);
		//	existingProduct.setId(id);
		//	existingProduct.setProductName(product.getProductName());
		//	existingProduct.setCategoryName(product.getCategoryName());				
		//	existingProduct.setPrice(product.getPrice());
		//	existingProduct.setProductImageName(product.getProductImageName());

		
		
			// save updated student object
			//	productService.updateProduct(existingProduct);
		
		
		if(product.isPresent()) {
			
		model.addAttribute("product", product.get());
			return "/Admin/addproduct";		
		}else {
			return "404";
		}
		
			
		}
			
			// handler method to handle delete student request
		
			@GetMapping("admin/delete/{id}")
		public String deleteProduct(@PathVariable Long id) {
				productService.deleteProductById(id);
				return "redirect:/admin";
		}
	
	
	
	
	
	
}
