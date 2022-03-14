package com.myapp.uranuscapstone.model;



import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;





@Entity
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name="event_id")
	private long id;
	
	
	private String eventName;

	private Date startDate;
	private Date endDate;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
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







	public Event(long id, String eventName, Date startDate, Date endDate) {
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
