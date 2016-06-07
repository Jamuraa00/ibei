<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.ico">
	<title>Reserved customer area</title>
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
            <li><a href="index.jsp">Home</a></li>
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
    </div>
    
	<div class="container">

    <div class="starter-template">
    <small>You are here: <h:commandLink action="index" value="Home"/>>User area</small>
    <c:if test="${customerController.currentCustomer != null }">
		<h1>${customerController.currentCustomer.name} ${customerController.currentCustomer.surname}</h1>
		<h2>Customer data:</h2>
		<div><b>Birthdate:</b> ${customerController.currentCustomer.birthDate}</div>
		<div><b>Registration date:</b> ${customerController.currentCustomer.registrationDate}</div>
		<div><b>Address:</b> ${customerController.currentCustomer.address.street}</div>
		<div><b>Zip code:</b> ${customerController.currentCustomer.address.zipCode}</div>
		<div><b>City:</b> ${customerController.currentCustomer.address.city}</div>
		<c:if test="${customerController.currentCustomer.address.state != ''}">
			<div><b>State:</b> ${customerController.currentCustomer.address.state}</div>
		</c:if>
		<div><b>Country:</b> ${customerController.currentCustomer.address.country}</div>
		<div><b>e-mail address:</b> ${customerController.currentCustomer.email}</div>
		<c:if test="${customerController.currentCustomer.phoneNumber != ''}">
			<div><b>Phone number:</b> ${customerController.currentCustomer.phoneNumber}</div>
		</c:if>
		<br>
		
		<div>Wrong informations? <h:commandButton action="editCustomer" value="Manage your personal data" styleClass="btn btn-default"/></div>
		
		<br>
		<c:if test="${administratorController.administrator == null}">
			<c:if test="${orderController.currentOrder == null}">
				<h:commandButton action="#{orderController.createOrder(customerController.currentCustomer)}" value="New cart" styleClass="btn btn-default"/>
			</c:if>
			<div><h:commandButton action="#{orderController.listOrders(customerController.currentCustomer.username)}" value="My orders" styleClass="btn btn-default"/></div>
			<c:if test="${orderController.currentOrder != null}">
				<div><h:commandButton action="#{orderController.toOrder}" value="View shopping cart" styleClass="btn btn-default"/></div>
			</c:if>
		</c:if>
		<div><h:commandButton action="#{customerController.logout}" value="Logout" styleClass="btn btn-default"/></div>
	</c:if>
	
	<c:if test="${administratorController.administrator != null }">
		<h1>${customerController.customer.name} ${customerController.customer.surname}</h1>
		<h2>Customer data:</h2>
		<div>Birthdate: ${customerController.customer.birthDate}</div>
		<div>Registration date: ${customerController.customer.registrationDate}</div>
		<div>Address: ${customerController.customer.address.street}</div>
		<div>Zip code: ${customerController.customer.address.zipCode}</div>
		<div>City: ${customerController.customer.address.city}</div>
		<div>State: ${customerController.customer.address.state}</div>
		<div>Country: ${customerController.customer.address.country}</div>
		<br>
		<c:if test="${administratorController.administrator != null}">
			<div><h:commandButton action="administrator" value="Back to administrator area" styleClass="btn btn-default"/></div>
		</c:if>
	</c:if>
	</div>
    </div>
	</h:form>
	</f:view>
</body>
</html>