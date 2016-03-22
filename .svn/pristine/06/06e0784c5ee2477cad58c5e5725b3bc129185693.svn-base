<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>New Product</title>
</head>
<body>
<f:view>
<h:form>
    <div>Name: <h:inputText value="#{productController.name}" 
                     required="true"
                     requiredMessage="Name is mandatory"
                     id="name"/> <h:message for="name" />
	</div>
    <div>Code: <h:inputText value="#{productController.code}" 
                     required="true"
                     requiredMessage="Code is mandatory"
                     id="code"/> <h:message for="code" />
	</div>
    <div>Price: <h:inputText value="#{productController.price}" 
                     required="true"
                     requiredMessage="Price is mandatory"
                     converterMessage="Price must be a number"
                     id="price"/> <h:message for="price" />
	</div>
    <div>Description: <h:inputTextarea value="#{productController.description}" 
    				required="false" 
    				cols="20" 
    				rows="5" /> 
                     
	</div>
	<div>
		<h:commandButton value="Submit"  action="#{productController.createProduct}"/>
	</div>
	<h:commandLink action="#{productController.listProducts}"
						value="List all Products" />
</h:form>
</f:view>
</body>
</html>