<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title>Product catalog</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.ico">

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
            <li class="active"><h:commandLink action="#{productController.listProducts}" value="Product catalog" /></li>
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
			<small>You are here: <h:commandLink action="index" value="Home"/>>Product catalog</small>
			<h1>Product catalog<c:if test="${productController.currentCategory != null}">: category ${productController.currentCategory}</c:if></h1>
			<div class="panel panel-default">
	  			<!-- Default panel contents -->
	  			<div class="panel-heading">Products in store</div>
			  		<!-- Table -->
			  		<table class="table">
		   				<tr>
							<th>Name</th><th>Price</th>
						</tr>
						<c:forEach var="product" items="#{productController.products}">
							<tr><td>
								<h:commandLink action="#{productController.findProduct(product.name)}" value="#{product.name}" />
							</td><td>${product.price}$</td></tr>
						</c:forEach>
		  			</table>
				</div>
				<div><c:if test="${productController.currentCategory != null}"><h:commandButton action="#{productController.listProducts()}" value="Back to product catalog" styleClass="btn btn-default"/></c:if></div>
				<div><h:commandButton action="categories" value="Search by category" styleClass="btn btn-default"/></div>
				<c:if test="${orderController.currentOrder != null}">
					<div><h:commandButton action="#{orderController.toOrder }" value="View shopping cart" styleClass="btn btn-default"/></div>
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