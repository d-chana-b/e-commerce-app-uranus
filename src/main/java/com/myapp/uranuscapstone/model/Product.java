package com.myapp.uranuscapstone.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="product_id")
	private Long productId;
	
    private String productName;
    
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name= "category", referencedColumnName = "category_id")
    private Category category; 
    
    @OneToMany (mappedBy="product", cascade=CascadeType.MERGE)
	private List<CartItems> cartItems;
    
    
    private double price;
    private String productImageName;
	
    public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Long productId, String productName, Category category, double price, String productImageName) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.productImageName = productImageName;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductImageName() {
		return productImageName;
	}

	public void setProductImageName(String productImageName) {
		this.productImageName = productImageName;
	}

	
	

}
