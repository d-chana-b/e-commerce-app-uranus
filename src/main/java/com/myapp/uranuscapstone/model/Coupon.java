package com.myapp.uranuscapstone.model;



import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event", referencedColumnName = "event_id")
	private Event event;

	private String couponName;
	private long discount;

	
	
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public Event getEvent() {
		return event;
	}



	public void setEvent(Event event) {
		this.event = event;
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



	public Coupon(long id, Event event, String couponName, long discount) {
		super();
		this.id = id;
		this.event = event;
		this.couponName = couponName;
		this.discount = discount;
	}



	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
	}

}
