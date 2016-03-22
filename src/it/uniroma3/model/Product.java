package it.uniroma3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Column;

import java.io.Serializable;
import java.util.List;

	@Entity
	@NamedQuery(name = "findAllProducts", query = "SELECT p FROM Product p")
	public class Product implements Serializable{
        
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Float price;
	
	@Column(length = 2000)
	private String description;

	@Column(unique=true, nullable = false)
	private String category;
	
	@ManyToMany(mappedBy = "products")
	private List<Supplier> suppliers;
	
	public Product() {
    }

	public Product(String name, Float price, String description, String category) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.category = category;
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
    
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
    
	public boolean equals(Object obj) {
		Product product = (Product)obj;
		return this.getName().equals(product.getName());
	}

	public int hashCode() {
		return this.name.hashCode();
	}
	
}