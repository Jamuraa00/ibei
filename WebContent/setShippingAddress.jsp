<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Set shipping address</title>
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/signin.css" rel="stylesheet">
</head>
<body>
<f:view>
<h:form>
<div class="container">
<div class="form-signin">
	<h1>Set the shipping address details for your order</h1>
	<div>Address: <h:inputText value="#{orderController.street}" 
    				 styleClass="form-control"
                     required="true"
                     requiredMessage="Address is mandatory"
                     id="street"/> <h:message for="street" />
	</div>
	<div>City: <h:inputText value="#{orderController.city}" 
    				 styleClass="form-control"
                     required="true"
                     requiredMessage="City is mandatory"
                     id="city"/> <h:message for="city" />
	</div>
	<div>Zip code: <h:inputText value="#{orderController.zipCode}" 
    				 styleClass="form-control"
                     required="true"
                     requiredMessage="Zip code is mandatory"
                     id="zipCode"/> <h:message for="zipCode" />
	</div>
	<div>State (optional): <h:inputText value="#{orderController.state}" 
    				 styleClass="form-control"
                     id="state"/>
	</div>
	<div>Country: <h:inputText value="#{orderController.country}" 
    				 styleClass="form-control"
                     required="true"
                     requiredMessage="Country is mandatory"
                     id="country"/> <h:message for="country" />
	</div>
	<div>
		<h:commandButton value="Submit"  action="#{orderController.setShippingAddress}" styleClass="btn btn-lg btn-primary btn-block"/>
	</div>
	<div>
		<h:commandButton value="Back" action="checkout" />
	</div>
</div>
</div>
</h:form>
</f:view>
</body>
</html>