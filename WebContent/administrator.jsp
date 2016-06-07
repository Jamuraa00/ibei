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
	<link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/starter-template-aligned.css" rel="stylesheet">
    <script src="js/d3.min.js"></script>
    <script src="js/jquery-2.1.4.js"></script>
	<title>Administrator Area</title>
	<style>
		
		.bar:hover {
		  fill: brown;
		}
		
		.axis {
		  font: 10px sans-serif;
		}
		
		.axis path,
		.axis line {
		  fill: none;
		  stroke: #000;
		  shape-rendering: crispEdges;
		}
		
		.x.axis path {
		  display: none;
		}

</style>
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
    </div>
    
	<div class="container">

      <div class="starter-template">
      
		<h1>${administratorController.administrator.name} ${administratorController.administrator.surname}</h1>
		<h2>Administrator data:</h2>
		<div>Username: ${administratorController.administrator.username}</div>
		<div>Administrator code: ${administratorController.administrator.code}</div>

		<br>
		<div><h:commandButton action="newProduct" value="Add a product" styleClass="btn btn-default"/></div>
		<div>Retrieve customer data by order ID: <h:inputText value="#{orderController.id}" /> <h:commandButton action="#{customerController.findCustomer(orderController.findOrderData(orderController.id))}" value="Find" styleClass="btn btn-default"/></div>
		<div><h:commandButton action="#{orderController.listUnevadedOrders}" value="Show non evaded orders" styleClass="btn btn-default"/></div>
		<div><h:commandButton action="#{administratorController.logout}" value="Logout" styleClass="btn btn-default"/></div>

	 </div>
	 
	 <div class="col-md-offset-1" id="chart">
	 <h1>Sales' trend in the last month</h1>
	 <div class="row" id="button-row">
	 	<div id="ovrl" class="btn btn-default">Overall</div>
	 	<div id="elect" class="btn btn-default">Electronics</div>
	 	<div id="diy" class="btn btn-default">Home and tools</div>
	 	<div id="cloth" class="btn btn-default">Clothing</div>
	 	<div id="sport" class="btn btn-default">Sports and outdoors</div>
	 	<div id="videogames" class="btn btn-default">Videogames</div>
	 </div>
	 <script src="js/script.js"></script>
	 </div>

    </div>
	</h:form>
	</f:view>
</body>
</html>