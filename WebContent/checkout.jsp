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
			<c:if test="${customerController.currentCustomer == null}">
				<li><h:commandLink action="newCustomer" value="Sign up" /></li>
			</c:if>
          </ul>
        </div>
      </div>
    </div> <!-- /Navbar -->
    
	<div class="container">
	<div class="starter-template">
	
	<!-- U S E R  V I E W -->
	
	<c:if test="${customerController.currentCustomer != null}">
	
		<small>You are here: <h:commandLink action="index" value="Home"/>><h:commandLink action="customer" value="User area"/>><h:commandLink action="orders" value="My orders"/>><h:commandLink action="order" value="Order info"/>>Checkout</small>
		
		<h1>Review your order</h1>
			<ul>
				<c:forEach var="orderLine" items="#{orderController.order.orderLines}">
					<li>${orderLine.product.name} ${orderLine.price}$ x${orderLine.quantity}</li>
				</c:forEach>
			</ul>
		<h2>Total to be paid: ${orderController.getTotal()}$</h2>
		<br>
		
		<c:if test="${orderController.currentOrder.shippingAddress == null}">
			<div>Your order will be shipped to: ${customerController.currentCustomer.address.toString()}.</div>
		</c:if>
		<c:if test="${orderController.currentOrder.shippingAddress != null}">
			<div>Your order will be shipped to: ${orderController.currentOrder.shippingAddress.toString()}.</div>
		</c:if>
		
		<div><h:commandLink value="Ship to a different address?" action="setShippingAddress" /></div>		
		
		<h:commandButton action="#{productController.listProducts}" value="Back to product catalog" styleClass="btn btn-default"/>
		<c:if test="${(orderController.order.dataChiusura == null) && (orderController.order.orderLinesLength != 0)}">
			<div><h:commandButton action="#{orderController.closeOrder}" value="Confirm order" styleClass="btn btn-default" /></div>
		</c:if>
		<c:if test="${orderController.order.dataChiusura == null}">
			<div><h:commandButton action="#{orderController.cancelOrder}" value="Delete order" styleClass="btn btn-default"/></div>
		</c:if>
	</c:if>
	</div>
	</div>
</h:form>
</f:view>
</body>
</html>