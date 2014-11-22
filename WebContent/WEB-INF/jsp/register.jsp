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
	<div class="col-lg-12 col-sm-12">
		<div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
	        <h4 class="modal-title" id="myModalLabel"><spring:message code="newUserRegistration.pageTitle"/></h4>
	    </div>
	    <div class="modal-body">		        
	 		<form id="registerForm" method="POST">
	 			<div class="form-group col-lg-offset-2">
	 				<label><spring:message code="newUserRegistration.firstname"/></label>
		            <input class="form-control" style="width:80%" type='text' placeholder="First Name" id="firstName" name='firstName' />
		            <label><spring:message code="newUserRegistration.lastname"/></label>
		            <input class="form-control" style="width:80%" type='text' placeholder="Last Name" id="lastName" name='lastName' />
		        	<label><spring:message code="newUserRegistration.username"/></label>
		            <input class="form-control" style="width:80%" type='text' placeholder="Username" id="userName" name='userName' />
		            <label><spring:message code="newUserRegistration.password"/></label>
		            <input class="form-control" style="width:80%" type='password' placeholder="Password" id="rpassword" name='password' />
		            <label><spring:message code="newUserRegistration.confirmpassword"/></label>
		            <input class="form-control" style="width:80%" type='password' onKeyUp="checkPass(); return false;" placeholder="Password" id="rnewPassword" name='newPassword' />		            
	            </div>
	            <!-- div class="text-center" style="margin-top:5px;">	            	
          			<button name="submit" class="btn btn-default" type="submit" value="Login">Submit</button>
          		</div-->
	       </form>
	       <button class="btn btn-default ajaxRegisterButton pull-right" id="ajaxSubmitBtn" onclick="ajaxRegister()"><spring:message code="newUserRegistration.registerbutton"/></button>	           	   
	       <div id="result"></div>  	      
	     </div>	     
     </div>
    <!-- jQuery Version 1.11.0 -->
    <script src="<spring:url value="/resources/js/jquery-1.11.0.js"/> "></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/> "></script>
    
    <!-- jQuery Validation -->    
    <script src="<spring:url value="/resources/js/jquery.validate.min.js"/> "></script>
    
    <!-- Custom JavaScript -->
    <script src="<spring:url value="/resources/js/custom.js"/> "></script>
</body>
</html>