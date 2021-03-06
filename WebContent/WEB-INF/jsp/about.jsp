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
                        <h1 class="invalid">Tech Used</h1>
                        <div class="col-lg-4 col-sm-4 col-lg-offset-2 col-sm-offset-2">
                        <table>
                        	<tr>
                        		<th>
                        			<h2 class="successOutput">Backend</h2>
                        		</th>
                        	</tr>
                        	<tr>
                        		<td>
                        			<h4>Spring Web MVC</h4>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td>
                        			<h4>Spring WebFlow</h4>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td>
                        			<h4>Spring Security</h4>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td>
                        			<h4>Hibernate w/JPA</h4>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td>
                        			<h4>MySQL</h4>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td>
                        			<h4>JUnit/Mockito</h4>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td>
                        			<h4>LOG4J</h4>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td>
                        			<h4>Commons-FileUpload</h4>
                        		</td>
                        	</tr>
                        </table>
                        </div>
                        <div class="col-lg-4 col-sm-4 col-lg-offset-2 col-sm-offset-2">
                        <table>
                        	<tr>
                        		<th>
                        			<h2 class="successOutput">Frontend</h2>
                        		</th>
                        	</tr>
                        	<tr>
                        		<td>
                        			<h4>HTML/CSS/Vanilla JavaScript</h4>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td>
                        			<h4>jQuery</h4>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td>
                        			<h4>jQuery UI - DataTables</h4>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td>
                        			<h4>jQuery Validate</h4>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td>
                        			<h4>jQuery Form JS</h4>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td>
                        			<h4>Bootstrap</h4>
                        		</td>
                        	</tr>
                        	<tr>
                        		<td>
                        			<h4>Sweet Alert JS</h4>
                        		</td>
                        	</tr>  
                        	<tr>
                        		<td>
                        			<h4>Pagedown (Markdown to Html Rich Text Edition)</h4>
                        		</td>
                        	</tr>                       	
                        </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.container -->
    </div>
</body>
</html>    