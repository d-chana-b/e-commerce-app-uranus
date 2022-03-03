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
    private Long Quantity;
    private double Price;
    private String ProductImage;
  
    //private int quantity;
    
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id",referencedColumnName ="category_id"  )
    private Category category;

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

	public Long getQuantity() {
		return Quantity;
	}

	public void setQuantity(Long quantity) {
		Quantity = quantity;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public String getProductImage() {
		return ProductImage;
	}

	public void setProductImage(String productImage) {
		ProductImage = productImage;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

}
