package com.myapp.uranuscapstone.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
    private String ProductName;
    private String CategoryName; 
    private double Price;
    private String ProductImageName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public String getProductImageName() {
		return ProductImageName;
	}
	public void setProductImageName(String productImageName) {
		ProductImageName = productImageName;
	}
	public Product(Long id, String productName, String categoryName, double price, String productImageName) {
		super();
		this.id = id;
		ProductName = productName;
		CategoryName = categoryName;
		Price = price;
		ProductImageName = productImageName;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
  
    //private int quantity;
    
    
    
 
	

}