package com.myapp.uranuscapstone.model;



import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;





@Entity
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name="event_id")
	private Long id;
	
	
	private String eventName;

	private Date startDate;
	private Date endDate;
	
	@OneToMany (mappedBy="event", cascade=CascadeType.ALL)
		private List<Coupon> coupons;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}







	public Event(Long id, String eventName, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
