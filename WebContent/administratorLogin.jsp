<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.ico">
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Administrator area</title>

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
	<h1>Access to administrator area</h1>
    <div>Username: <h:inputText value="#{administratorController.username}" 
    				 styleClass="form-control"
                     required="true"
                     requiredMessage="Username is mandatory"
                     id="username"/> <h:message for="username" />
	</div>
    <div>Password: <h:inputSecret value="#{administratorController.password}" 
    				 styleClass="form-control"
                     required="true"
                     requiredMessage="Password is mandatory"
                     id="password"/> <h:message for="password" />
	</div>
	<div>
		<h:commandButton value="Submit"	  action="#{administratorController.administratorLogin}" styleClass="btn btn-lg btn-primary btn-block"/>
	</div>
	<div>${administratorController.message}</div>
	<div><h:commandButton action="index"	value="Homepage" styleClass="btn btn-default"/></div>
</div>
</div>
</h:form>
</f:view>
</body>
</html>