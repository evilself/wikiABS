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
    
    <!-- WikiABS custom CSS classes -->
    <link href="<spring:url value="/resources/css/custom.css"/>" rel="stylesheet">   
</head>

<body>
	<div class="col-lg-12 col-sm-12">
		<div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
	        <h4 class="modal-title" id="myModalLabel"><spring:message code="login.login"/></h4>
	    </div>
	    <div class="modal-body">	    	
	        <c:url var="loginUrl" value="/j_spring_security_check"></c:url>
	 		<form id="loginForm" method="POST">
	 			<div class="form-group col-lg-offset-2">
		        	<label><spring:message code="login.username"/></label>
		            <input class="form-control" style="width:80%" type='text' placeholder="Username" id="username" name='username' />
		            <label><spring:message code="login.password"/></label>
		            <input class="form-control" style="width:80%" type='password' placeholder="Password" id="password" name='password' />		            
	            </div>
	            <!-- div class="text-center" style="margin-top:5px;">	            	
          			<button name="submit" class="btn btn-default" type="submit" value="Login">Submit</button>${pageContext.request.contextPath}/passwordReset
          		</div--><input type="hidden" id="url" value="${pageContext.request.contextPath}" />
	       </form>
	       <button class="btn btn-default ajaxRegisterButton pull-right" id="ajaxSubmitBtn" onclick="ajaxLogin()"><spring:message code="login.loginButton"/></button>
	       <a class="btn btn-sm cancelButton pull-right" title="Did you forget your password?" style="padding:4px; margin-right:5px" id="ajaxForgotPassBtn" onclick="ajaxPassReset($('#url').val()+'/passwordReset')">?</a>	           	   
	       <div id="result"></div>  	      
	     </div>	     
     </div>
    
    <!-- Custom JavaScript -->
    <script src="<spring:url value="/resources/js/custom.js"/> "></script>    
</body>
</html>