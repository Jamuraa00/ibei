package it.uniroma3.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.swing.JOptionPane;

import it.uniroma3.model.Address;
import it.uniroma3.model.Customer;
import it.uniroma3.model.CustomerFacade;
import it.uniroma3.model.Order;
import it.uniroma3.model.OrderFacade;
import it.uniroma3.model.OrderLine;
import it.uniroma3.model.Product;
import it.uniroma3.model.ProductFacade;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="orderController")
@SessionScoped
public class OrderController {
	
	private Long id;
	private Order order;
	private Order currentOrder;
	private Integer quantity;
	private Float price;
	private OrderLine orderLine;
	private List<Order> orders;
	private String message;
	
	private String street;
	private String city;
	private String zipCode;	
	private String state;	
	private String country;
	
	@EJB
	private ProductFacade productFacade;
	@EJB
	private OrderFacade orderFacade;
	@EJB
	private CustomerFacade customerFacade;
	
	public String createOrder(Customer customer) {
		this.currentOrder = new Order(new Date(), customer);
		infoBox("Cart created!", "Order info");
		return toOrder();
	}
	
	public String cancelOrder() throws FileNotFoundException, IOException{
		this.currentOrder = null;
		infoBox("Order cancelled!", "Order info");
		return "customer";
	}
	
	public String addOrderLine(Product product) throws FileNotFoundException, IOException, ClassNotFoundException{
		this.orderLine = new OrderLine(product, product.getPrice(), quantity);
		this.currentOrder.getOrderLines().add(this.orderLine);
		infoBox("Added to cart!", "Order info");
		return "products";
	}
	
	public String addOrderLineNewOrder(Product product, Customer customer) throws FileNotFoundException, IOException, ClassNotFoundException{
		createOrder(customer);
		addOrderLine(product);
		infoBox("Added to cart!", "Order info");
		return "products";
	}
	
	public String closeOrder() throws FileNotFoundException, IOException{
		this.currentOrder.setDataChiusura(new Date());
		this.orderFacade.addOrder(this.currentOrder);
		infoBox("Order closed!", "Order info");
		return "customer";
	}
	
	public String setShippingAddress(){
		Address shippingAddress = new Address(street, city, zipCode, state, country);
		this.order.setShippingAddress(shippingAddress);
		return "checkout";
	}
	
	public String findOrder(Long id) throws ClassNotFoundException, IOException{
		this.order = this.orderFacade.getOrder(id);
		return "order";
	}
	
	public String toOrder(){
		this.order = this.currentOrder;
		return "order";
	}
	
	public String makeCurrentOrder(){
		this.currentOrder = this.order;
		return "index";
	}
	
	public String findOrderData(Long id) throws ClassNotFoundException, IOException{
		this.order = this.orderFacade.getOrder(id);
		String customerUsername = this.order.getCustomer().getUsername();
		return customerUsername;
	}
	
	public String listOrders(String username) throws ClassNotFoundException, IOException{
		this.orders = this.orderFacade.getOrders(username);
		return "orders";
	}
	
	public String listUnevadedOrders() throws FileNotFoundException, IOException{
		this.orders = this.orderFacade.getUnevadedOrders();
		return "orders";
	}
	
	public String evadeOrder() throws FileNotFoundException, IOException{
		this.orderFacade.evadeOrder(this.order);
		infoBox("Order evaded!", "Order info");
		return "order";
	}
	
	public int getTotal() {
		return this.order.getTotal();
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
	
	public Order getOrder(){
		return this.order;
	}
	
	public void setOrder(Order order){
		this.order = order;
	}
	
	public Order getCurrentOrder(){
		return this.currentOrder;
	}
	
	public void setCurrentOrder(Order order){
		this.currentOrder = order;
	}
	
	public OrderLine getOrderLine(){
		return this.orderLine;
	}
	
	public void setOrderLine(OrderLine orderLine){
		this.orderLine = orderLine;
	}
	
	public Integer getQuantity(){
		return this.quantity;
	}
	
	public void setQuantity(Integer quantity){
		this.quantity = quantity;
	}
	
	public Float getPrice(){
		return this.price;
	}
	
	public void setPrice(Float price){
		this.price = price;
	}
	
	public List<Order> getOrders(){
    	return this.orders;
    }
    
    public void setOrders(List<Order> orders){
    	this.orders = orders;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

}


