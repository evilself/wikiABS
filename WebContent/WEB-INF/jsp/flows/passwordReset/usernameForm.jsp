<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html>
<html lang="en">
<head>    
    <!-- WikiABS custom CSS classes -->
    <link href="<spring:url value="/resources/css/custom.css"/>" rel="stylesheet">
</head>

<body>
	
	    <div class="modal-body">
	        <h4 class="modal-title" id="myModalLabel">What is your username?</h4>
	        <br/><br/>
	 		<sf:form id="registerForm" modelAttribute="user" action="${flowExecutionUrl}">
	 			<div class="form-group col-lg-offset-2">		        			        	 
		            <input class="form-control" style="width:80%" type='text' placeholder="Username" id="userName" name='userName' />		            
		        	<input type='hidden' id="url" value="${flowExecutionUrl}"/>
	            </div>
	       </sf:form>	       
	       <button class="btn btn-default ajaxRegisterButton pull-right" id="ajaxSubmitBtn" onclick="ajaxPassReset($('#url').val()+'&_eventId_step1')">Next</button>	        	      
	       <div id="result"></div>
	     </div>	   
    <!-- Custom JavaScript -->
    <script src="<spring:url value="/resources/js/custom.js"/> "></script>   
</body>
</html>