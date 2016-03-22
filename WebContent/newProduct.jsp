<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>New product</title>
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
    <div>Name: <h:inputText value="#{productController.name}" 
    				 styleClass="form-control"
                     required="true"
                     requiredMessage="Name is mandatory"
                     id="name"/> <h:message for="name" />
	</div>
    <div>Price: <h:inputText value="#{productController.price}" 
    				 styleClass="form-control"
                     required="true"
                     requiredMessage="Price is mandatory"
                     converterMessage="Price must be a number"
                     id="price"/> <h:message for="price" />
	</div>
	<div>Category: <h:inputText value="#{productController.category}" 
    				 styleClass="form-control"
                     required="true"
                     requiredMessage="Category is mandatory"
                     id="category"/> <h:message for="category" />
	</div>
    <div>Description (optional): <h:inputTextarea value="#{productController.description}" 
    				 styleClass="form-control"
    				 required="false" 
    				 cols="20" 
    				 rows="5" /> 
                     
	</div>
	<div>
		<h:commandButton value="Submit"  action="#{productController.createProduct}" styleClass="btn btn-lg btn-primary btn-block" />
	</div>
	<h:commandLink action="#{productController.listProducts}"
						value="Product catalog" />
</div>
</div>
</h:form>
</f:view>
</body>
</html>