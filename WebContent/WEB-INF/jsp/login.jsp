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
	<div class="col-lg-12 col-sm-12">
		<div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
	        <h4 class="modal-title" id="myModalLabel">Login</h4>
	    </div>
	    <div class="modal-body">	    	
	        <c:url var="loginUrl" value="/j_spring_security_check"></c:url>
	 		<form id="loginForm" method="POST">
	 			<div class="form-group col-lg-offset-2">
		        	<label>Username</label>
		            <input class="form-control" style="width:80%" type='text' placeholder="Username" id="username" name='username' />
		            <label>Password</label>
		            <input class="form-control" style="width:80%" type='password' placeholder="Password" id="password" name='password' />		            
	            </div>
	            <!-- div class="text-center" style="margin-top:5px;">	            	
          			<button name="submit" class="btn btn-default" type="submit" value="Login">Submit</button>
          		</div-->
	       </form>
	       <button class="btn btn-default pull-right" id="ajaxSubmitBtn" style="margin-right:17%;" onclick="ajaxLogin()">Login</button>	        
	           	   
	       <div id="result"></div>  	      
	     </div>	     
     </div>
    <!-- jQuery Version 1.11.0 -->
    <script src="<spring:url value="/resources/js/jquery-1.11.0.js"/> "></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/> "></script>
    
    <!-- jQuery Validation -->    
    <script src="<spring:url value="/resources/js/jquery.validate.min.js"/> "></script>
    
	<script>
		$('#loginModal').on('hidden.bs.modal', function() {
			//$(this).removeData('bs.modal')
		});
	</script>
	
	<script>
		$('#loginForm').keypress(function(event){			 
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if(keycode == '13'){
				$('#ajaxSubmitBtn').click();
			}		 
		});
		
		// When the browser is ready...
		$(function() {  
		  
		    // Setup form validation on the #register-form element
		    $("#loginForm").validate({
		    
		        // Specify the validation rules
		        rules: {
		            username: "required",
		            password: "required"		            
		        },
		        
		        // Specify the validation error messages
		        messages: {
		            username: "Username is missing",
		            password: "Password is missing"		            
		        },
		        
		        submitHandler: function(form) {
		        	$('#ajaxSubmitBtn').click();
		        }
		    });
		  });
	
		function ajaxLogin(){
	        $('#result').html('');
	        if(($('#username').val() != "") && ($('#password').val() != "")) {	          
		        $.ajax({
		        	url:  "ajaxLogin",
			        type: "POST",
			        data: $("#loginForm").serialize(),
			        success: function(data){
			        	
			        	if (data.indexOf("Success") < 0) {
			        		$('#result').html("Login failed, please try again").css({"color":"red", "font-size":"1.5em"});
			        		$('#username, #password').val("").css({"border-color":"red"});		        		
			        		
			            } else {		            	
			            	$('#result').html("Login successful!").css({"color":"green", "font-size":"1.5em"});
			            	$('#username, #password').css({"border-color":""});
			            	setTimeout(function() { window.location.reload(true); }, 1000);
			            }		                       
			        }		       
			    });	
	        } else {	        	
	        	 $('#result').html('Please provide both username and password!').css({"color":"red", "font-size":"1.2em"});	        	
	        }
	    }		
	</script>
</body>
</html>