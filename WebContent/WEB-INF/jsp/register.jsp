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
	        <h4 class="modal-title" id="myModalLabel">Register</h4>
	    </div>
	    <div class="modal-body">		        
	 		<form id="registerForm" method="POST">
	 			<div class="form-group col-lg-offset-2">
	 				<label>First Name</label>
		            <input class="form-control" style="width:80%" type='text' placeholder="First Name" id="firstName" name='firstName' />
		            <label>Last Name</label>
		            <input class="form-control" style="width:80%" type='text' placeholder="Last Name" id="lastName" name='lastName' />
		        	<label>Username</label>
		            <input class="form-control" style="width:80%" type='text' placeholder="Username" id="userName" name='userName' />
		            <label>Password</label>
		            <input class="form-control" style="width:80%" type='password' placeholder="Password" id="rpassword" name='password' />
		            <label>Confirm Password</label>
		            <input class="form-control" style="width:80%" type='password' onKeyUp="checkPass(); return false;" placeholder="Password" id="rnewPassword" name='newPassword' />		            
	            </div>
	            <!-- div class="text-center" style="margin-top:5px;">	            	
          			<button name="submit" class="btn btn-default" type="submit" value="Login">Submit</button>
          		</div-->
	       </form>
	       <button class="btn btn-default pull-right" id="ajaxSubmitBtn" style="background-color:#C9C9D5; color:#0066CC; border-color:#C9C9D5;margin-right:17%;" onclick="ajaxRegister()">Register</button>	        
	           	   
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
		var match = false;
		
		function checkPass()
		{			
		    //Store the password field objects into variables ...
		    var pass1 = document.getElementById('rpassword');
		    var pass2 = document.getElementById('rnewPassword');
		    //Store the Confimation Message Object ...
		    var message = document.getElementById('result');
		    //Set the colors we will be using ...
		    var goodColor = "#CCFF99";
		    var badColor = "#FFE6E6";
		    //Compare the values in the password field
		    //and the confirmation field
		    if(pass2.value != "") {
			    if(pass1.value == pass2.value){
			        //The passwords match.
			        //Set the color to the good color and inform
			        //the user that they have entered the correct password
			        pass2.style.backgroundColor = goodColor;
			        message.style.color = goodColor;
			        match=true;
			        message.innerHTML = "Passwords Match!"
			    }else{
			        //The passwords do not match.
			        //Set the color to the bad color and
			        //notify the user.
			        pass2.style.backgroundColor = badColor;
			        message.style.color = badColor;
			        match=false
			        message.innerHTML = "Passwords do not match!"
			    }
		    }
		}
	
		$('#registerForm').keypress(function(event){			 
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if(keycode == '13'){
				$('#ajaxSubmitBtn').click();
			}		 
		});		
	
		function ajaxRegister(){
	        $('#result').html('');
	        if( ($('#firstName').val() != "") && ($('#lastName').val() != "") && ($('#userName').val() != "") && ($('#password').val() != "") && ($('#newPassword').val() != "")) {
		        if(match) {	          
			        $.ajax({
			        	url:  "/wikiABS/ajaxRegister",
				        type: "POST",
				        data: $("#registerForm").serialize(),
				        success: function(data){
				        	
				        	if (data.indexOf("Success") < 0) {
				        		$('#result').html("Register failed, please try again!").css({"color":"#FFE6E6", "font-size":"1.5em"});
				        		$('#username, #password').val("").css({"border-color":"red"});		        		
				        		
				            } else {		            	
				            	$('#result').html("Registered! Please log in with your credentials!").css({"color":"#CCFF99", "font-size":"1.5em"});
				            	$('#username, #password').css({"border-color":""});
				            	setTimeout(function() { window.location.reload(true); }, 1000);
				            }		                       
				        }		       
				    });	
		        } else {	        	
		        	$('#result').html('Passwords do not match!').css({"color":"#FFE6E6", "font-size":"1.2em"});	        	
		        }
	        } else {
	        	$('#result').html('Please provide all information!').css({"color":"#FFE6E6", "font-size":"1.2em"});	        	
	        }
	    }		
	</script>
</body>
</html>