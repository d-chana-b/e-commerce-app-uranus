package com.myapp.uranuscapstone.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;






@Entity
public class Coupon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;
	
	
	private String CouponName;
	private long Discount;
	private String Expiration;
	
	
	
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getCouponName() {
		return CouponName;
	}
	public void setCouponName(String couponName) {
		CouponName = couponName;
	}
	public long getDiscount() {
		return Discount;
	}
	public void setDiscount(long discount) {
		Discount = discount;
	}

	
	
	




	

	
	
	public Coupon(long iD, String couponName, long discount, String expiration) {
		super();
		ID = iD;
		CouponName = couponName;
		Discount = discount;
		Expiration = expiration;
	}
	public String getExpiration() {
		return Expiration;
	}
	public void setExpiration(String expiration) {
		Expiration = expiration;
	}
	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
