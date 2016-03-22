<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Manage your personal data</title>
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
	<h1>Manage your personal data</h1>
    <div>Name: <h:inputText value="#{customerController.name}" 
    				 styleClass="form-control"
                     required="true"
                     requiredMessage="Name is mandatory"
                     id="name"/> <h:message for="name" />
	</div>
    <div>Surname: <h:inputText value="#{customerController.surname}" 
    				 styleClass="form-control"
                     required="true"
                     requiredMessage="Surname is mandatory"
                     id="surname"/> <h:message for="surname" />
	</div>
	<div>Address: <h:inputText value="#{customerController.street}" 
    				 styleClass="form-control"
                     required="true"
                     requiredMessage="Address is mandatory"
                     id="street"/> <h:message for="street" />
	</div>
	<div>City: <h:inputText value="#{customerController.city}" 
    				 styleClass="form-control"
                     required="true"
                     requiredMessage="City is mandatory"
                     id="city"/> <h:message for="city" />
	</div>
	<div>Zip code: <h:inputText value="#{customerController.zipCode}" 
    				 styleClass="form-control"
                     required="true"
                     requiredMessage="Zip code is mandatory"
                     id="zipCode"/> <h:message for="zipCode" />
	</div>
	<div>State (optional): <h:inputText value="#{customerController.state}" 
    				 styleClass="form-control"
                     id="state"/>
	</div>
	<div>Country: <h:inputText value="#{customerController.country}" 
    				 styleClass="form-control"
                     required="true"
                     requiredMessage="Country is mandatory"
                     id="country"/> <h:message for="country" />
	</div>
	<div>Birthdate (gg/mm/aaaa): <h:inputText value="#{customerController.birthDateString}" 
    				 styleClass="form-control"
                     required="true"
                     requiredMessage="Birthdate is mandatory"
                     id="birthDate"/> <h:message for="birthDate" />
	</div>
	<div>e-mail address: <h:inputText value="#{customerController.email}" 
    				 styleClass="form-control"
                     required="true"
                     requiredMessage="e-mail address is mandatory"
                     id="email"/> <h:message for="email" />
	</div>
	<div>Phone number (optional): <h:inputText value="#{customerController.phoneNumber}" 
    				 styleClass="form-control"
                     id="phoneNumber"/>
	</div>
	<div>
		<h:commandButton value="Submit"  action="#{customerController.updateCustomer}" styleClass="btn btn-lg btn-primary btn-block"/>
	</div>
</div>
</div>
</h:form>
</f:view>
</body>
</html>