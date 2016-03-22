package it.uniroma3.model;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import java.io.Serializable;

import javax.persistence.Column;

@Entity
@NamedQuery(name = "findAllAdministrators", query = "SELECT a FROM Administrator a")
public class Administrator implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String surname;

	@Column(unique=true, nullable = false)
	private String code;
	
	@Column(unique=true, nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	public Administrator() {
	}

	public Administrator(String name, String surname, String code) {
		this.name = name;
		this.surname = surname;
		this.code = code;
	}
	
	public boolean checkPassword(String password){
		return this.password.equals(password);
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
    
    public String getCode(){
    	return this.code;
    }
    
    public void setCode(String code){
    	this.code = code;
    }
       
    public String getUsername(){
    	return this.username;
    }
    
    public void setUsername(String username){
    	this.username = username;
    }
    
//    public String getPassword(){
//    	return this.password;
//    }
    
    //Risvolti di sicurezza?
    public void setPassword(String password){
    	this.password = password;
    }
	
    public boolean equals(Object obj) {
        Administrator administrator = (Administrator)obj;
        return this.getCode().equals(administrator.getCode());
    }

    public int hashCode() {
         return this.code.hashCode();
    }
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Administrator"); 
        sb.append("{id=").append(id); 
        sb.append(", name='").append(name); 
        sb.append(", surname=").append(surname); 
        sb.append(", username='").append(username); 
        sb.append(", password='").append(password);     
        sb.append(", code='").append(code);
        sb.append("}\n");
        return sb.toString();
    }
	
}


