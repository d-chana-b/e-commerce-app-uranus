package com.myapp.uranuscapstone.model;



import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;
	
	
	private String EventName;
	private long Discount;
	private Date StartDate;
	private Date EndDate;
	
	
	
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getEventName() {
		return EventName;
	}
	public void setEventName(String eventName) {
		EventName = eventName;
	}
	public long getDiscount() {
		return Discount;
	}
	public void setDiscount(long discount) {
		Discount = discount;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	public Event(long iD, String eventName, long discount, Date startDate, Date endDate) {
		super();
		ID = iD;
		EventName = eventName;
		Discount = discount;
		StartDate = startDate;
		EndDate = endDate;
	}
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
