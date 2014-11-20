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
    <!-- SweetAlert-->
    <link href="<spring:url value="/resources/css/sweet-alert.css"/>" rel="stylesheet">
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
    <style> .invalid {  }
    
    label.error {
	 color:#FFE6E6;
	}
	input.error {
	 background-color:#FFE6E6;
	}
    
    </style>
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
                        <a style="color:purple;" id="logoutLink" onclick="confirmLogout(event);" >Logout</a>
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
                    <h2>New User Creation</h2>                   			
                    	<div id="list">
					        <sf:form id="userForm" modelAttribute="user" method="post">
					        	<sf:errors path="*">
					        		<div style="color:#FFE6E6;"><spring:message code="error.global" /></div>
					        	</sf:errors>
					        	<div class="col-lg-4 col-sm-4 col-lg-offset-4 col-sm-offset-4">				            
			                    <label class="pull-left" for="firstName">First Name</label><label class="pull-right" style="color:#FFE6E6;"><sf:errors path="firstName" htmlEscape="false"></sf:errors></label>
			                    <input name="firstName" id="firstName"  class="form-control" value="${user.firstName}"/>		              		
			              		
			                    <label class="pull-left" for="lastName">Last Name</label><label class="pull-right" style="color:#FFE6E6;"><sf:errors path="lastName" htmlEscape="false"></sf:errors></label>
			                    <input name="lastName" id=""lastName" class="form-control" value="${user.lastName}" />
			          
			                    <label class="pull-left" for="username">Username</label><label class="pull-right" style="color:#FFE6E6;"><sf:errors path="userName" htmlEscape="false"></sf:errors></label>
			                    <input name="userName" id="userName" class="form-control" value="${user.userName}" />
			             					             	
			                    <label class="pull-left" for="password">New Password</label><label class="pull-right" style="color:#FFE6E6;"><sf:errors path="password" htmlEscape="false"></sf:errors></label>
			                    <input name="password" id="password" class="form-control" type="password" value="${user.password}" />
			                    
			                    <label class="pull-left" for="newpassword">Confirm New Password</label><label class="pull-right" id="result"></label>
			                    <input name="newpassword" onKeyUp="checkPass(); return false;" id="newpassword" type="password" class="form-control" />
			             		
			             		<div style="margin-top:10px;"></div>		      
				             		 <a class="btn btn-info pull-right" style="padding-top:1px; padding-bottom: 1px; background-color:#C9C9D5; color:#0066CC; border-color:#C9C9D5" href="${pageContext.request.contextPath}/users">Cancel</a>             
				                     <input type="submit" value="Save" id="save" style=" margin-right: 5px; padding-top:1px; padding-bottom: 1px; background-color:#CCFF99; color:#0066CC; border-color:#C9C9D5;" class="btn btn-default pull-right" />
			                    </div>					              
					        </sf:form>					        
					        <div style="margin-bottom:10px" class="col-lg-12 col-sm-12">
					        	
					    	</div>
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
    
    <!-- Bootstrap Core JavaScript -->
    <script src="<spring:url value="/resources/js/sweet-alert.min.js"/> "></script>
    
    <!-- jQuery Validation -->    
    <script src="<spring:url value="/resources/js/jquery.validate.min.js"/> "></script>
    
    <script>	
		var match = false;
		
		function checkPass()
		{			
		    //Store the password field objects into variables ...
		    var pass1 = document.getElementById('password');
		    var pass2 = document.getElementById('newpassword');
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
    
    </script>
    <script>
    
 // When the browser is ready...
	$(function() {  	
		
	    // Setup form validation on the #register-form element
	    $("#userForm").validate({
	    	//errorClass: 'invalid',
	        // Specify the validation rules
	        rules: {
	        	firstName: "required",
	        	lastName: "required",
	            userName: "required",
	            password: "required",
	            newpassword: "required"
	        },
	        
	        errorPlacement: function(error, element) {
	        	$(error).addClass('pull-right');
	        	//$(element).css({'background-color':'#FFE6E6'});
	            error.insertBefore(element);
	        },
	        
	        // Specify the validation error messages
	        messages: {
	        	firstName: "First name is missing!",
	        	lastName: "Last name is missing!",
	        	userName: "Username is missing!",
	        	password: "Password is missing!",
		        newpassword: "New password is missing!"
	        },        
	        
	        submitHandler: function(form) {
	        	//alert($('#product').val());
	        	 if(match) {
	        		form.submit();
	        	 }
	        }
	    });
	  });
 
    	function confirmLogout(e) {
    	 	
    		swal({
    			  title: "Are you sure?",    			  
    			  type: "warning",
    			  showCancelButton: true,
    			  confirmButtonColor: "#DD6B55",
    			  confirmButtonText: "Yes, log me out!"
    			},
    			function(){    			  
    			  window.location="logout";
    			});
    	 	
    	}
   
    </script>

</body>

</html>