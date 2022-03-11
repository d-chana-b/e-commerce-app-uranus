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
	private long id;
	
    private String ProductName;
    
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name= "category", referencedColumnName = "category_id")
    private Category category; 
    
    
    private double Price;
    private String ProductImageName;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}


	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
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


	public Product(long id, String productName, Category category, double price, String productImageName) {
		super();
		this.id = id;
		ProductName = productName;
		this.category = category;
		Price = price;
		ProductImageName = productImageName;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
  
    //private int quantity;
    
    
    
 
	

}
