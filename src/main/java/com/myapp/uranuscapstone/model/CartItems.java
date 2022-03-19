package com.myapp.uranuscapstone.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart_items")
public class CartItems {
	@Id
	@Column(name = "cart_items_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartItemsId;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name= "user_id", referencedColumnName = "user_id")
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name= "product_id", referencedColumnName = "product_id")
	private Product product;
		
	private int quantity;

	public CartItems() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItems(int cartItemsId, User user, Product product, int quantity) {
		super();
		this.cartItemsId = cartItemsId;
		this.user = user;
		this.product = product;
		this.quantity = quantity;
	}

	public int getCartItemsId() {
		return cartItemsId;
	}

	public void setCartItemsId(int cartItemsId) {
		this.cartItemsId = cartItemsId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
