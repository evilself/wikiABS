<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Bootstrap Core CSS -->
    <link href="<spring:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    
    <!-- Template CSS -->
    <link href="<spring:url value="/resources/css/landing-page.css"/>" rel="stylesheet">
    
    <!-- FAVICON -->
	<link rel="icon" href="/favicon.ico" type="image/x-icon" />
	
	<!-- SweetAlert CSS. This is a superb custom alert popup -->
    <link href="<spring:url value="/resources/css/sweet-alert.css"/>" rel="stylesheet">
    
    <!-- DataTable CSS. This is a superb custom data table -->
    <link href="<spring:url value="/resources/css/dataTables.bootstrap.css"/>" rel="stylesheet">
    
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
    <style>    
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
                    <c:if test="${admin == 'true'}">                   
					    <li>
	                        <a href="${pageContext.request.contextPath}/products"><spring:message code="menu.products"/></a>
	                    </li>	                    
					</c:if>                  
                    <c:if test="${loggedUser == null}">
	                    <li>
	                        <a href="${pageContext.request.contextPath}/login" class="loginButton" data-toggle="modal" data-target="#loginModal"><spring:message code="menu.login"/></a>                        
	                    </li>	                    
                    </c:if>
                    <c:if test="${loggedUser != null}">
                     <li>
                        <a id="logoutLink" class="logoutButton" onclick="confirmLogout(event);"><spring:message code="menu.logout"/></a>
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
                    <h2><spring:message code="product.createProductTitle"/></h2> 
                    	<div id="list">
					        <sf:form id="productForm" modelAttribute="product" method="post" action="${pageContext.request.contextPath}/products">	
					        	<sf:errors path="*">
					        		<div style="color:#FFE6E6;"><spring:message code="error.global" /></div>
					        	</sf:errors>
					        	<div class="col-lg-4 col-sm-4 col-lg-offset-4 col-sm-offset-4">				            
			                    <label class="pull-left"  for="productName"><spring:message code="product.name"/></label><label class="pull-right" style="color:#FFE6E6;"><sf:errors path="productName" htmlEscape="false"></sf:errors></label>
			                    <input name="productName" id="productName"  class="form-control" value="${product.productName}"/>
			              
			                    <label class="pull-left" for="description"><spring:message code="product.description"/></label><label class="pull-right" style="color:#FFE6E6;"><sf:errors path="description" htmlEscape="false"></sf:errors></label>
			                    <input name="description" id="description" class="form-control" value="${product.description}" />		                    
			             			
			             		<a class="btn btn-info cancelButton pull-right" style="margin-top:5px" href="${pageContext.request.contextPath}/products"><spring:message code="general.cancelButton"/></a>             
			                    <input type="submit" value="Save" style="margin-right:5px;margin-top:5px" id="save" class="btn btn-default commonButton pull-right"/>					              
					        	</div>
					        </sf:form>
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
	                        <a href="${pageContext.request.contextPath}/about"><spring:message code="menu.about"/></a>
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
    
    <!-- Bootstrap Core JavaScript -->
    <script src="<spring:url value="/resources/js/sweet-alert.min.js"/> "></script>
    
    <!-- jQuery Validation -->    
    <script src="<spring:url value="/resources/js/jquery.validate.min.js"/> "></script>
    
    <!-- Custom JavaScript -->
    <script src="<spring:url value="/resources/js/custom.js"/> "></script>
    
    <script>    
	    $(document).keypress(function(event){			 
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if(keycode == '13'){
				$("#productForm").submit();
			}		 
		});	
	    
	    // When the browser is ready...
		$(function() {  	
			
		    // Setup form validation on the #register-form element
		    $("#productForm").validate({		    	
		        // Specify the validation rules
		        rules: {
		        	productName: "required",
		        	description: "required"		        		
		        },
		        
		        errorPlacement: function(error, element) {
		        	$(error).addClass('pull-right');        	
		            error.insertBefore(element);
		        },
		        
		        // Specify the validation error messages
		        messages: {
		        	productName: "Product name is required!",
		        	description: "Description is required!"	        	
		        },
		        
		        submitHandler: function(form) {
		        	//alert($('#product').val());
		        	form.submit();
		        }
		    });
		  });  
      </script>
</body>
</html>