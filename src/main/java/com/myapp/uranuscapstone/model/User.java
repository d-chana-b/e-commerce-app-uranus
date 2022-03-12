package com.myapp.uranuscapstone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
    private int userId;
     
    //@Column(nullable = false, unique = true, length = 45)
    private String email;
     
    //@Column(nullable = false, length = 64)
    private String password;

	//@Column(name = "first_name", nullable = false, length = 20)
    private String firstName;
     
    //@Column(name = "last_name", nullable = false, length = 20)
    private String lastName;
    
    //@OneToOne(mappedBy = "users")
    //private Cart cart;
    
    public User() {
    	
    }
    
    public User(int id, String email, String password, String firstName, String lastName) {
		super();
		this.userId = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int id) {
		this.userId = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
