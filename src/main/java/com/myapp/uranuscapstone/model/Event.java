package com.myapp.uranuscapstone.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;
	
	
	private String EventName;
	private long Discount;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date StartDate;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date EndDate;
	
	
	
}
