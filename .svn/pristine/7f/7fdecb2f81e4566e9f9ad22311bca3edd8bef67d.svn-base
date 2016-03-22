package it.uniroma3.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Column;
import javax.persistence.OneToOne;

import java.util.List;

	@Entity
	@NamedQuery(name = "findAllSuppliers", query = "SELECT s FROM Supplier s")
	public class Supplier {
        
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String pIva;

	@Column(nullable = false)
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Address address;
	
	@Column(unique=true, nullable = false)
	private String code;
	
	@ManyToMany
	private List<Product> products;
	
	public Supplier() {
    }

	public Supplier(String pIva, Address address, String code) {
        this.pIva = pIva;
        this.address = address;
        this.code = code;
}

    //Getters & Setters        
    
   public Long getId() {
        return id;
    }
   
   public void setId(Long id) {
		this.id = id;
	}

    public String getPIva() {
        return this.pIva;
    }

    public void setPIva(String pIva) {
        this.pIva = pIva;
    }
    
    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    public String getCode(){
    	return this.code;
    }
    
    public void setCode(String code){
    	this.code = code;
    }

    public boolean equals(Object obj) {
        Supplier supplier = (Supplier)obj;
        return this.getCode().equals(supplier.getCode());
    }

    public int hashCode() {
         return this.code.hashCode();
    }
	
}