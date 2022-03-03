package com.myapp.uranuscapstone.dto;

public class ProductDTO {
	
private Long id;
	
    private String ProductName;
    private String CategoryName;
    private Long Quantity;
    private double Price;
    private String ProductImagename;

    private Long CategoryID;

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

	public String getProductImagename() {
		return ProductImagename;
	}

	public void setProductImagename(String productImagename) {
		ProductImagename = productImagename;
	}

	public Long getCategoryID() {
		return CategoryID;
	}

	public void setCategoryID(Long categoryID) {
		CategoryID = categoryID;
	}

	public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductDTO(Long id, String productName, String categoryName, Long quantity, double price,
			String productImagename, Long categoryID) {
		super();
		this.id = id;
		ProductName = productName;
		CategoryName = categoryName;
		Quantity = quantity;
		Price = price;
		ProductImagename = productImagename;
		CategoryID = categoryID;
	}
    
	
    
}


