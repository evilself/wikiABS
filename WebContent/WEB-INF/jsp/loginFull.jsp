<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
    
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
    
    <!-- Template CSS -->
    <link href="<spring:url value="/resources/css/landing-page.css"/>" rel="stylesheet">
    
    <!-- FAVICON -->
	<link rel="icon" href="/favicon.ico" type="image/x-icon" />
	
	<!-- SweetAlert CSS. This is a superb custom alert popup -->
    <link href="<spring:url value="/resources/css/sweet-alert.css"/>" rel="stylesheet">
    
    <!-- WikiABS custom CSS classes -->
    <link href="<spring:url value="/resources/css/custom.css"/>" rel="stylesheet">

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
                <a class="navbar-brand" target="_blank" href="http://www.americanbanksystems.com"><spring:message code="welcome.message"/></a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="${pageContext.request.contextPath}"><spring:message code="menu.home"/></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/articles"><spring:message code="menu.articles"/></a>
                    </li>              
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
                        	<div class="col-lg-6 col-sm-6 col-lg-offset 3 col-sm-offset-3 text-left" style="top:30%; ">
							        <c:url var="loginUrl" value="/j_spring_security_check"></c:url>
							 		<form action="${loginUrl}" id="loginForm" method="POST" novalidate="novalidate">
							 			<c:if test="${not empty error}">
											<div class="errorLogin text-center">${error}</div>
										</c:if>
										<c:if test="${not empty denied}">
											<div class="errorLogin text-center">${denied}</div>
										</c:if>
							 			<div class="form-group">
								        	<label><spring:message code="login.username"/></label>
								            <input class="form-control"  type='text' placeholder="Username" name='username' />
								        </div>
								        <div class="form-group">
								            <label><spring:message code="login.password"/></label>
								            <input class="form-control" type='password' placeholder="Password" name='password' />		            
							            </div>
							            <div style="margin-top:5px; margin-bottom:5px; margin-left:15%" class="text-center">	            	
						          			<button name="submit" class="btn btn-default ajaxRegisterButton" type="submit" value="Login"><spring:message code="login.loginButton"/></button>
						          		</div>
						          		<div class="clear"></div>
							       </form>							         
						     </div>                        	
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
                            <a href="${pageContext.request.contextPath}"><spring:message code="menu.home"/></a>
                        </li>                        
                        <li class="footer-menu-divider">&sdot;</li>
                        <li>
                            <a href="${pageContext.request.contextPath}/articles"><spring:message code="menu.articles"/></a>
                        </li>
                        <li class="footer-menu-divider">&sdot;</li>
                        <li>
                            <a href="#about"><spring:message code="menu.about"/></a>
                        </li>
                    </ul>
                    <p class="copyright text-muted small">Copyright &copy; American Bank Systems Inc 2015. All Rights Reserved</p>
                </div>
            </div>
        </div>
    </footer>

    <!-- jQuery Version 1.11.0 -->
    <script src="<spring:url value="/resources/js/jquery-1.11.0.js"/> "></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/> "></script>
    
    <!-- jQuery Validation -->    
    <script src="<spring:url value="/resources/js/jquery.validate.min.js"/> "></script>
    
    <!-- Custom JavaScript -->
    <script src="<spring:url value="/resources/js/custom.js"/> "></script>

	<script>
		$(document).keypress(function(event){			 
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if(keycode == '13'){
				$("#loginForm").submit();
			}		 
		});	
		
		//When the browser is ready...
		$(function() {
		    // Setup form validation on the #register-form element
		    $("#loginForm").validate({
		    	errorClass: 'invalid',
		        // Specify the validation rules
		        rules: {
		            username: "required",
		            password: "required"		            
		        },
		        
		        errorPlacement: function(error, element) {
		        	$(error).addClass('pull-right');        	
		            error.insertBefore(element);
		        },
		        
		        // Specify the validation error messages
		        messages: {
		            username: "Username is required!",
		            password: "Password is required!"		            
		        },
		        
		        submitHandler: function(form) {
		        	form.submit();
		        }
		    });
		  });
	</script>
</body>
</html>    