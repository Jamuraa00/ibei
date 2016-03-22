<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order summary</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/starter-template-aligned.css" rel="stylesheet">
</head>
<body>
<f:view>
<h:form>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">IBEI</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="index.jsp">Home</a></li>
            <li><h:commandLink action="#{productController.listProducts}" value="Product catalog" /></li>
			<c:if test="${customerController.currentCustomer == null}">
				<li><h:commandLink action="#{customerController.toCustomerArea}" value="Sign in" /></li>
			</c:if>
			<c:if test="${customerController.currentCustomer != null}">
				<li><h:commandLink action="#{customerController.toCustomerArea}" value="#{customerController.currentCustomer.name}" /></li>
			</c:if>
			<c:if test="${administratorController.administrator == null}">
				<li><h:commandLink action="#{administratorController.toAdministratorArea}" value="Administrator area" /></li>
			</c:if>
			<c:if test="${administratorController.administrator != null}">
				<li><h:commandLink action="#{administratorController.toAdministratorArea}" value="#{administratorController.administrator.name}" /></li>
			</c:if>
			<li><h:commandLink action="newCustomer" value="Sign up" /></li>
          </ul>
        </div>
      </div>
    </div> <!-- /Navbar -->
    
	<div class="container">
	<div class="starter-template">
	
	<!-- A D M I N I S T R A T O R  V I E W -->
	
	<c:if test="${administratorController.administrator != null}">
      
		<h1>Order summary</h1>
		<div>Creation date: ${orderController.order.dataCreazione}</div>
		<div>Closing date: ${orderController.order.dataChiusura}</div>
		<div>Evasion date: ${orderController.order.dataEvasione}</div>
		<h2>Products details</h2>
			<ul>
				<c:forEach var="orderLine" items="#{orderController.order.orderLines}">
					<li>${orderLine.product.name} ${orderLine.price}$ x${orderLine.quantity}</li>
				</c:forEach>
			</ul>
		<c:if test="${orderController.order.dataEvasione != null}">
			<div>Order evaded.</div>
		</c:if>
		<div>${orderController.message}</div>
		<div><c:if test="${administratorController.administrator != null && orderController.order.dataEvasione == null}">
			<h:commandButton action="#{orderController.evadeOrder}" value="Evade order" />
		</c:if></div>
	</c:if>
	
	<!-- U S E R  V I E W -->
	
	<c:if test="${customerController.currentCustomer != null}">
	
		<small>You are here: <h:commandLink action="index" value="Home"/>><h:commandLink action="customer" value="User area"/>><h:commandLink action="orders" value="My orders"/>>Order info</small>
		
		<h1>Order summary</h1>
		<div>Creation date: ${orderController.order.dataCreazione}</div>
		<div>Closing date: ${orderController.order.dataChiusura}</div>
		<div>Evasion date: ${orderController.order.dataEvasione}</div>
		<h2>Products details</h2>
			<ul>
				<c:forEach var="orderLine" items="#{orderController.order.orderLines}">
					<li>${orderLine.product.name} ${orderLine.price}$ x${orderLine.quantity}</li>
				</c:forEach>
			</ul>
		<h2>Total to be paid: ${orderController.getTotal()}$</h2>
		
		<!-- S H I P P I N G  A D D R E S S -->
		
		<c:if test="${orderController.currentOrder.shippingAddress != null}">
			<h2>Shipping address: ${orderController.currentOrder.shippingAddress.toString()}</h2>
		</c:if>
		<c:if test="${orderController.currentOrder.shippingAddress == null}">
			<h2>Shipping address: ${orderController.currentOrder.customer.address.toString()}</h2>
		</c:if>
		
		<c:if test="${orderController.order.dataEvasione != null}">
			<div>Order evaded.</div>
		</c:if>
		<div>${orderController.message}</div>
		<br>
		<c:if test="${orderController.orders != null && customerController.customer != null}">
			<h:commandLink action="#{orderController.listOrders(customerController.currentCustomer.username)}" value="My orders" />
		</c:if>
		<c:if test="${(orderController.order.dataChiusura == null) && (orderController.order.orderLinesLength != 0)}">
			<div><h:commandButton action="checkout" value="Checkout" /></div>
		</c:if>
		<c:if test="${(orderController.order.dataChiusura != null) && (orderController.order.dataEvasione == null)}">
			<div>Order closed.</div>
		</c:if>
		<c:if test="${orderController.order.dataChiusura == null }">
			<div><h:commandButton action="#{orderController.cancelOrder }" value="Cancel order" /></div>
		</c:if>
	</c:if>
	</div>
	</div>
</h:form>
</f:view>
</body>
</html>