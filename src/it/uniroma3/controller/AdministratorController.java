package it.uniroma3.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;

import it.uniroma3.model.Administrator;
import it.uniroma3.model.AdministratorFacade;

import javax.faces.bean.ManagedBean;


@ManagedBean(name="administratorController")
@SessionScoped
public class AdministratorController {
	
	private String username;
	private String password;
	private Administrator administrator;
	private String message;
	
	@EJB
	private AdministratorFacade administratorFacade;

	public String administratorLogin() throws FileNotFoundException, ClassNotFoundException, IOException {
		Administrator temp = administratorFacade.getAdministrator(this.username);
		if (temp == null || !(temp.checkPassword(this.password))){
			setMessage("Username o password invalidi");
			return "administratorLogin";
		}
		this.administrator = temp;
		setMessage(null);
		return "administrator";
	}
	
	public String toAdministratorArea() {
		if (this.administrator == null)
			return "administratorLogin";
		return "administrator";
	}
	
	public String logout(){
		this.administrator = null;
		return "index";
	}
	
	//Getters & Setters

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
	
	public String getMessage(){
		return this.message;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public Administrator getAdministrator(){
		return this.administrator;
	}
	
	public void setAdministrator(Administrator administrator){
		this.administrator = administrator;
	}
	
}