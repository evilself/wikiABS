<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Wiki Page/Knowledge base">
    <meta name="author" content="BorisM">
    <title>ABS Wiki</title>
    
    <!-- Bootstrap Core CSS -->
    <link href="<spring:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <!-- Custom CSS for the main page-->
    <link href="<spring:url value="/resources/css/landing-page.css"/>" rel="stylesheet">
	<link rel="icon" href="/favicon.ico" type="image/x-icon" />

    <!-- Custom Fonts -->
    <link href="<spring:url value="/resources/font-awesome-4.1.0/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="background: url(<spring:url value="/resources/img/wall_two.jpg"/>) no-repeat center center;height:300px;">
           
    	</div>
    </div>
	</div>
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <img class="img-responsive navbar-brand" src="<spring:url value="/resources/img/newEagle.jpg"/>" alt=""></img>
                <a class="navbar-brand" target="_blank" href="http://www.americanbanksystems.com">Welcome to American Bank System's knowledge pool!</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="/">Home</a>
                    </li>
                    <li>
                        <a href="articles">Articles</a>
                    </li> 
                    <c:if test="${admin == 'true'}">                   
					    <li>
	                        <a href="products">Products</a>
	                    </li>
	                    <li>
	                        <a href="users">Users</a>
	                    </li>
					</c:if>                  
                    <c:if test="${loggedUser == null}">
	                    <li>
	                        <a style="color:red;" href="login" data-toggle="modal" data-target="#loginModal">Login</a>                        
	                    </li>
                    </c:if>
                    <c:if test="${loggedUser != null}">
                     <li>
                        <a style="color:purple;" href="<c:url value="/j_spring_security_logout" />">Logout</a>
                    </li>
                    </c:if>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

   <!-- Header -->
    <div class="intro-header">

        <div class="container">

            <div class="row">
                <div class="col-lg-12">               
                    <div class="intro-message">                    	
                        <h2>Products</h2>
                          <c:if test="${loggedUser != null}">
					   		 <a href="products?new">Add new product</a>
					      </c:if>
					      <c:if test="${fn:length(products) gt 0}">
							    <table class="table" >
							        <tr style="font-weigth:bold;font-size: 1.1em;">
							            <th class="text-left" style="width:30%">Name</th>
							            <th class="text-left" style="width:65%">Description</th>							            
							            <th class="text-center" style="width:5%"></th>
							        </tr>
							        <c:forEach items="#{products}" var="prod">
						            <tr style="color:#0066CC;">
						                <td class="text-left" style="width:30%;">${prod.productName}</td>
						                <td class="text-left" style="width:65%">${prod.description}</td>						                					                                
						                <td class="text-center" style="width:5%">
						                    <a class="btn btn-info" style="padding-top:1px; padding-bottom: 1px; background-color:#C9C9D5; color:#0066CC; border-color:#C9C9D5" href="products/${prod.id}">Edit</a>
						                </td>
						                <!-- td>
						                    <sf:form action="articles/${art.id}" method="delete" >
						                        <input type="submit" value="" >Delete</input>
						                    </sf:form>
						                </td-->
						            </tr>
							        </c:forEach>
							    </table> 
					     </c:if>
					     <c:if test="${fn:length(products) eq 0}"> 
					      <h2>No products present</h2> 
					     </c:if>                     	
                        <!-- h3>What are you looking for?</h3 -->
                        <hr class="intro-divider">                 
                        </ul>
                    </div>
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>
    <!-- /.intro-header -->

    <!-- Footer -->
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <ul class="list-inline">
                        <li>
                            <a href="/">Home</a>
                        </li>                        
                        <li class="footer-menu-divider">&sdot;</li>
                        <li>
                            <a href="articles">Articles</a>
                        </li>
                        <li class="footer-menu-divider">&sdot;</li>
                        <li>
                            <a href="#about">About</a>
                        </li>
                    </ul>
                    <p class="copyright text-muted small">Copyright &copy; American Bank Systems Inc 2014. All Rights Reserved</p>
                </div>
            </div>
        </div>
    </footer>

    <!-- jQuery Version 1.11.0 -->
    <script src="<spring:url value="/resources/js/jquery-1.11.0.js"/> "></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/> "></script>

</body>

</html>