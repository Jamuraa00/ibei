package it.uniroma3.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import it.uniroma3.model.Product;
import it.uniroma3.model.ProductFacade;

@ManagedBean(name="productController")
@SessionScoped
public class ProductController {
	
	@ManagedProperty(value="#{param.id}")
	private Long id;
	private String name;
	private Float price;
	private String description;
	private String category;
	public final String electronics = "Electronics";
	public final String house = "House";
	public final String clothing = "Clothing";
	public final String sport = "Sport";
	public final String videogames = "Videogames";
	private Product product;
	private List<Product> products;
	
	@EJB
	private ProductFacade productFacade;
	
	public String createProduct() throws FileNotFoundException, IOException, ClassNotFoundException {
		this.product = new Product(name, price, description, category);
		productFacade.addProduct(this.product);
		return "product"; 
	}
	
	public String listProducts() throws FileNotFoundException, ClassNotFoundException, IOException {
		this.products = productFacade.getAllProducts();
		return "products"; 
	}

	public String findProduct() {
		this.product = productFacade.getProduct(this.name);
		return "product";
	}
	
	public String findProduct(String name) {
		this.product = productFacade.getProduct(name);
		return "product";
	}
	
	public String findByCategory(String category) throws FileNotFoundException, ClassNotFoundException, IOException{
		this.products = productFacade.findByCategory(category);
		return "products";
	}
	
	//Getters & Setters

	public Long getId() {
		return this.id;
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

	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCategory(){
		return this.category;
	}
	
	public void setCategory(String category){
		this.category = category;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getElectronics() {
		return electronics;
	}

	public String getHouse() {
		return house;
	}

	public String getClothing() {
		return clothing;
	}

	public String getSport() {
		return sport;
	}

	public String getVideogames() {
		return videogames;
	}
	
}


