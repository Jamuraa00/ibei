package it.uniroma3.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import it.uniroma3.model.Address;
import it.uniroma3.model.Product;
import it.uniroma3.model.ProductFacade;
import it.uniroma3.model.Supplier;
import it.uniroma3.model.SupplierFacade;

@ManagedBean(name="supplierController")
@SessionScoped
public class SupplierController {
	

	private Long id;
	private String pIva;
	private Address address;
	private String code;
	private List<Product> products;
	private Supplier supplier;
	private List<Supplier> suppliers;
	
	@EJB
	private SupplierFacade supplierFacade;
	@EJB
	private ProductFacade productFacade;
	
	public String createSupplier() {
		this.supplier = new Supplier(pIva, address, code);
		supplierFacade.addSupplier(this.supplier);
		return "supplier"; 
	}
	
	public String listSuppliers() {
		this.suppliers = this.supplierFacade.getAllSuppliers();
		return "products"; 
	}
	
	public String findSupplier(Long id) {
		this.supplier = supplierFacade.getSupplier(id);
		return "supplier";
	}
	
	public String findSuppliersByPIva(String pIva){
		this.suppliers = this.supplierFacade.getSuppliersByPIva(pIva);
		return "suppliers";
	}
	
	// Getters & Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getpIva() {
		return pIva;
	}

	public void setpIva(String pIva) {
		this.pIva = pIva;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

}
