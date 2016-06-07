<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="it">
  <head>
  
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.ico">

    <title>Welcome on IBEI</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link href="css/starter-template.css" rel="stylesheet">
    
    <style>
    	body {
 			background: url('img/background.jpg') no-repeat center center fixed;
 			-webkit-background-size: cover;
  			-moz-background-size: cover;
  			-o-background-size: cover;
 			background-size: cover;
		}
		.starter-template {
			text-shadow: 0px 0px 4px white;
		}
    </style>

  </head>

  <body>
  
  	<!-- N A V B A R -->
  
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
    </div>
    
    <!-- N A V B A R -->

    <div class="container">

      <div class="starter-template">
      
      	<!-- U S E R  N O T  L O G G E D -->
        
        <c:if test="${customerController.currentCustomer == null}">
        	<h1>Welcome on IBEI!</h1>
        	<p class="lead">A small e-commerce site<br> Find everything you want on IBEI</p>
        		<div><h:commandButton action="newCustomer" value="Sign up now!" styleClass="btn btn-default"/></div>
        		<br><div>Already a customer?</div>
        		<br><div><h:commandButton action="customerLogin" value="Login" styleClass="btn btn-default"/></div>
        </c:if>
        
        <!-- U S E R  L O G G E D -->
        
        <c:if test="${customerController.currentCustomer != null}">
        	<h1>Welcome back on IBEI ${customerController.currentCustomer.getName()}!</h1>
        	<p>Are you ready to start shopping?</p>
        	<br>
        	<h4>Here are some things you can do:</h4>
        	<br>
        	<h:commandButton action="#{productController.listProducts}" value="Browse the product catalog" styleClass="btn btn-default"/>
        	<br>
        	<br>
        	<h:commandButton action="#{customerController.toCustomerArea}" value="Go to your personal area" styleClass="btn btn-default"/>
        	<br>
        	<br>
        	<h:commandButton action="#{customerController.logout }" value="Logout" styleClass="btn btn-default" />
        	<c:if test="${orderController.currentOrder != null }">
        		<br>
        		<br>
        		<h:commandButton action="#{orderController.toOrder }" value="View shopping cart" />
        	</c:if>
        </c:if>
      </div>

    </div>
    </h:form>
    </f:view>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>