package it.uniroma3.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.swing.JOptionPane;

import it.uniroma3.model.Address;
import it.uniroma3.model.Customer;
import it.uniroma3.model.CustomerFacade;
import it.uniroma3.model.OrderFacade;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name="customerController")
@SessionScoped
public class CustomerController{
	
	@ManagedProperty(value="#{param.id}")
	private Long id;
	
	private String username;
	private String password;
	private String password2;
	private String name;
	private String surname;
	private String email;
	private String phoneNumber;
	
	private String street;
	private String city;
	private String zipCode;
	private String state;
	private String country;
	
	private String birthDateString;
	private Date birthDate;
	private Customer customer;
	private Customer currentCustomer;
	private Address address;
	private String message;
	
	@EJB
	private CustomerFacade customerFacade;
	@EJB
	private OrderFacade orderFacade;
	
	public String customerLogin() throws FileNotFoundException, ClassNotFoundException, IOException {
		this.currentCustomer = customerFacade.getCustomerByUsername(this.username);
		if (this.currentCustomer == null || !(this.currentCustomer.checkPassword(this.password)))
		{
			setMessage("Invalid username or password");
			return "customerLogin";
		}
		this.message = null;
		return "customer";
	}
	
	public String toCustomerArea() {
		if (this.currentCustomer == null)
			return "customerLogin";
		return "customer";
	}
	
	public String createCustomer() throws FileNotFoundException, IOException{
		if (! this.password.equals(this.password2)){
			infoBox("Type your password correctly", "Error");
			return "newCustomer";
		}
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		try{
			this.birthDate = df.parse(this.birthDateString);
		}
		catch (ParseException e){
			return "newCustomer";
		}
		Address tempAddress = new Address(street, city, zipCode, state, country);
		Customer tempCustomer = new Customer(username, password, name, surname, tempAddress, birthDate, new Date(),
				email, phoneNumber);
		if (tempAddress.getStreet() != null)
			this.currentCustomer = customerFacade.addCustomer(tempCustomer);
		return "customer";
	}
	
	public String findCustomer(String username) throws FileNotFoundException, ClassNotFoundException, IOException{
		this.customer = this.customerFacade.getCustomerByUsername(username);
		return "customer";
	}
	
	public String updateCustomer() throws FileNotFoundException, IOException{
		Address tempAddress = new Address(street, city, zipCode, state, country);
		Customer tempCustomer = new Customer(username, password, name, surname, tempAddress, birthDate, currentCustomer.getRegistrationDate(),
				email, phoneNumber);
		this.currentCustomer = this.customerFacade.updateCustomer(tempCustomer);
		return "customer";
	}
	
	public String logout(){
		this.currentCustomer = null;
		return "index";
	}
	
	public void infoBox(String infoMessage, String titleBar){
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
	
	//Getters & Setters
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername(){
		return this.username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword2(){
		return this.password2;
	}
	
	public void setPassword2(String password2){
		this.password2 = password2;
	}
	
	public String getMessage(){
		return this.message;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public Customer getCustomer(){
		return this.customer;
	}
	
	public void setCustomer(Customer customer){
		this.customer = customer;
	}
	
	public Customer getCurrentCustomer(){
		return this.currentCustomer;
	}
	
	public void setCurrentCustomer(Customer customer){
		this.currentCustomer = customer;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getBirthDateString() {
		return birthDateString;
	}

	public void setBirthDateString(String birthDateString) {
		this.birthDateString = birthDateString;
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
	
}