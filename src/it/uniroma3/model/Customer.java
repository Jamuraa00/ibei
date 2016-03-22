package it.uniroma3.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.OneToMany;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

	@Entity
	@NamedQuery(name = "findAllCustomers", query = "SELECT c FROM Customer c")
	public class Customer implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String surname;

	@Column(nullable = false)
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Address address;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date registrationDate;
	
	@Column(unique=true, nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String email;
	
	private String phoneNumber;
	
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;
	
	public Customer() {
	}

	public Customer(String username, String password, String name, String surname,
			Address address, Date birthDate, Date registrationDate, String email,
			String phoneNumber) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.birthDate = birthDate;
		this.registrationDate = registrationDate;
		this.email = email;
		this.phoneNumber = phoneNumber;
//		this.code = code;
	}
	
	public boolean checkPassword(String password){
		return this.password.equals(password);
	}
	
	public void addOrder(Order order){
		this.orders.add(order);
	}

    //Getters & Setters
	
	public Long getId() {
		return id;
	 }
	   
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
    	return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    public Date getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    
//    public String getCode(){
//    	return this.code;
//    }
//    
//    public void setCode(String code){
//    	this.code = code;
//    }
    
    public List<Order> getOrders(){
    	return this.orders;
    }
    
    public void setOrders(List<Order> orders){
    	this.orders = orders;
    }
    
    public String getUsername(){
    	return this.username;
    }
    
    public void setUsername(String username){
    	this.username = username;
    }

    public void setPassword(String password){
    	this.password = password;
    }
	
    public boolean equals(Object obj) {
        Customer customer = (Customer)obj;
        return this.getUsername().equals(customer.getUsername());
    }

    public int hashCode() {
         return this.username.hashCode();
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString(){
		return this.name+" "+this.surname+", "+this.address.toString();
	}
	
}