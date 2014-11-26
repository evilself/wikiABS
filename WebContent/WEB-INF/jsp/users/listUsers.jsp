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
						<li>
	                        <a href="${pageContext.request.contextPath}/users"><spring:message code="menu.users"/></a>
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
                        <h2><spring:message code="user.listUsers"/></h2>                                   
					   	  <a class="btn btn-default commonButton btn-sm" href="${pageContext.request.contextPath}/users?new"><spring:message code="user.addUser"/></a>					     
						    <table id="userTable" class="table" >
						    	<thead>
						        <tr style="font-weigth:bold;font-size: 1.1em;">
						            <th class="text-left" style="width:20%"><spring:message code="user.firstName"/></th>
						            <th class="text-left" style="width:20%"><spring:message code="user.lastName"/></th>
						            <th class="text-center" style="width:20%"><spring:message code="user.username"/></th>
						            <th class="text-center" style="width:20%"><spring:message code="user.password"/></th>
						            <th class="text-center" style="width:10%"><spring:message code="user.role"/></th>
						            <th class="text-center" style="width:5%"></th>
						             <c:if test="${admin == 'true'}">
						            	<th class="text-center" style="width:5%"></th>
						            </c:if>
						        </tr>
						        </thead>
						        <tbody>
						        <c:forEach items="#{users}" var="usr">
					            <tr style="color:#0066CC;">						            
					                <td class="text-left" style="width:20%;">${usr.firstName}</td>
					                <td class="text-left" style="width:20%">${usr.lastName}</td>
					                <td class="text-center" style="width:20%">${usr.userName}</td>
					                <td class="text-center" style="width:20%">Password hashed</td>
					                <th class="text-center" style="width:10%">${usr.role.role}</th>	                                
					                <td class="text-center" style="width:5%">
					                    <a class="btn btn-info commonButton" href="${pageContext.request.contextPath}/users/${usr.id}"><spring:message code="user.editButton"/></a>
					                </td>
					                <c:if test="${admin == 'true'}">
						                <td class="text-center" style="width:5%"">
						                    <sf:form id="deleteForm_${usr.id}" action="${pageContext.request.contextPath}/users/${usr.id}" method="delete" >
						                        <input class="btn btn-info commonButton" type="submit" onclick="confirmUserDel(event)" value="Delete"></input>
						                    </sf:form>
						                </td>					                
				                	</c:if>
					            </tr>
						        </c:forEach>
						        </tbody>
						    </table>				    
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
    
    <!-- Bootstrap Core JavaScript -->
    <script src="<spring:url value="/resources/js/sweet-alert.min.js"/> "></script>
    
    <!-- jQuery Datatable -->
    <script src="<spring:url value="/resources/js/jquery.dataTables.min.js"/> "></script>
    
    <!-- Bootstrap Datatable -->
    <script src="<spring:url value="/resources/js/dataTables.bootstrap.js"/> "></script>
    
    <!-- Custom JavaScript -->
    <script src="<spring:url value="/resources/js/custom.js"/> "></script>
    
    <script>
    	$(document).ready(function() {
    		$('#userTable').DataTable({
    		    "bFilter": false, "bLengthChange":false , "bPaginate":false,  "aoColumnDefs": [
    		                                                                                   { "bSortable": false, "aTargets": [ 5,6 ] }
    		                                                                                   ]  } );
    	});    	
    </script>	
</body>
</html>