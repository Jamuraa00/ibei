package it.uniroma3.model;

import java.io.Serializable;

	public class Address implements Serializable{

	private String street;

	private String city;

	private String zipCode;
	
	private String state;
	
	private String country;
	
	public Address() {
	}

	public Address(String street, String city, String zipCode, String state, String country) {
		this.street = street;
		this.city = city;
		this.zipCode = zipCode;
		this.state = state;
		this.country = country;
	}
	
	//Getters & Setters

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

	public int hashCode() {
		//return this.street.hashCode()+this.zipCode.hashCode();
		return ((this.street==null || this.zipCode==null) ? 0 : this.street.hashCode()+this.zipCode.hashCode());
	}

	public boolean equals(Object obj) {
		Address other = (Address) obj;
		return(street.equals(other.street) && zipCode.equals(other.zipCode));
	}
	
	@Override
	public String toString(){
		if (this.state != null)
			return this.street+", "+this.zipCode+", "+this.city+", "+this.state+", "+this.country;
		return this.street+", "+this.zipCode+", "+this.city+", "+this.country;
	}
	
}