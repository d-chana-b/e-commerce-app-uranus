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
	@Column(name="Coupon_ID")
	private long CouponID;
	
	
	private String CouponName;
	private long Discount;
	private String ExpiryDate;
	
	
	public long getCouponID() {
		return CouponID;
	}
	public void setCouponID(long couponID) {
		CouponID = couponID;
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
	public String getExpiryDate() {
		return ExpiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		ExpiryDate = expiryDate;
	}
	
	
	public Coupon(long couponID, String couponName, long discount, String expiryDate) {
		super();
		CouponID = couponID;
		CouponName = couponName;
		Discount = discount;
		ExpiryDate = expiryDate;
	}
	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
