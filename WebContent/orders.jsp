<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title>Orders</title>
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
    </div>
    
    <div class="container">
	<div class="starter-template">
	<c:if test="${customerController.currentCustomer != null}">
		<small>You are here: <h:commandLink action="index" value="Home"/>><h:commandLink action="customer" value="User area"/>>My orders</small>
	</c:if>
	<h1>My orders</h1>
	<div class="panel panel-default">
	<!-- Default panel contents -->
	<div class="panel-heading">My orders</div>
		<c:if test="${orderController.orders != null}">
			<table class="table">
				<tr>
					<th>Order ID</th><th>Creation date</th><th>Closing date</th><th>Evasion date</th>
				</tr>
				<c:forEach var="temp" items="#{orderController.orders}">
					<tr>
						<td><h:commandLink action="#{orderController.findOrder(temp.id)}" value="#{temp.id}"/></td>
						<td>${temp.dataCreazione}</td>
						<td>${temp.dataChiusura}</td>
						<td>${temp.dataEvasione}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
	</div>
	</div>
</h:form>
</f:view>
</body>
</html>