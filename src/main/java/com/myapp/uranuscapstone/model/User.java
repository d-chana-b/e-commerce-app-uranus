package com.myapp.uranuscapstone.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
    private int userId;
     
    @Column(nullable = false, unique = true, length = 45)
    private String email;
     
    @Column(nullable = false, length = 64)
    private String password;

	@Column(name = "first_name", nullable = false, length = 20)
    private String firstName;
     
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;
    
    
    private String address;
	@Column(name = "phone_number", length = 38)
	private Long phoneNumber;
    
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<CartItems> cartItems;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_role",
			joinColumns = {@JoinColumn(name="user_id", referencedColumnName = "user_id")},
					inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName = "role_id")}
			
			)
	private List<Role> roles;
    
    
    //@OneToOne(mappedBy = "users")
    //private Cart cart;
	
	
	public User(int userId, String firstName, String lastName,
			@NotEmpty @Email(message = "{errors.invalid_email}") String email, @NotEmpty String password,
			String address, Long phoneNumber, List<Role> roles) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.roles = roles;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(User user) {
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getLastName();
		this.password = user.getPassword();
		this.address =  user.getAddress();
		this.phoneNumber = user.getPhoneNumber();
		this.roles = user.getRoles();
	}
	
	
    public String getAddress() {
		return address;
	}

	public List<CartItems> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItems> cartItems) {
		this.cartItems = cartItems;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
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
