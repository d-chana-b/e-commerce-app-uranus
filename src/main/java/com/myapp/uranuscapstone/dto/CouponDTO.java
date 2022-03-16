package com.myapp.uranuscapstone.dto;

public class CouponDTO {
	
	
	private long couponId;
	private String couponName;
	private long discount;
	private Long categoryId;
	private Long eventId;
	
	public CouponDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public long getDiscount() {
		return discount;
	}

	public void setDiscount(long discount) {
		this.discount = discount;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public CouponDTO(long couponId, String couponName, long discount, Long categoryId, Long eventId) {
		super();
		this.couponId = couponId;
		this.couponName = couponName;
		this.discount = discount;
		this.categoryId = categoryId;
		this.eventId = eventId;
	}
	
	
	

}
