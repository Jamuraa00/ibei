package it.uniroma3.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

	public class Order implements Serializable{
        
	private Long id;

	private Date dataCreazione;
	
	private Date dataChiusura;
	
	private Date dataEvasione;
	
	private Customer customer;
	
	private Address shippingAddress;

	private List<OrderLine> orderLines;
	
	public Order() {
    }

	public Order(Date dataCreazione, Customer customer) {
        this.dataCreazione = dataCreazione;
        this.customer = customer;
        this.orderLines = new LinkedList<OrderLine>();
}

    //Getters & Setters        
    
   public Long getId() {
        return this.id;
    }
   
	public void setId(Long id) {
		this.id = id;
	}

    public Date getDataCreazione() {
        return this.dataCreazione;
    }

    public void setDataCreazione(Date dataCreazione) {
        this.dataCreazione = dataCreazione;
    }
    
    public Date getDataChiusura() {
        return this.dataChiusura;
    }
    
    public void setDataChiusura(Date dataChiusura) {
        this.dataChiusura = dataChiusura;
    }

    public Date getDataEvasione(){
    	return this.dataEvasione;
    }
    
    public void setDataEvasione(Date dataEvasione){
    	this.dataEvasione = dataEvasione;
    }
    
    public List<OrderLine> getOrderLines(){
    	return this.orderLines;
    }
    
    public void setOrderLines(List<OrderLine> orderLines){
    	this.orderLines = orderLines;
    }
    
    public int getOrderLinesLength(){
    	return this.orderLines.size();
    }
    
    public Customer getCustomer(){
    	return this.customer;
    }
    
    public void setCustomer(Customer customer){
    	this.customer = customer;
    }
    
    public void setShippingAddress (Address address){
    	this.shippingAddress = address;
    }
    
    public Address getShippingAddress (){
    	return this.shippingAddress;
    }
	
    public boolean isSame(Order obj) {
        return this.getId().equals(obj.getId());
    }

	public int getTotal() {
		int result = 0;
		for (OrderLine tempOL : this.orderLines) {
			result += tempOL.getPrice() * tempOL.getQuantity();
		}
		return result;
	}
    
}