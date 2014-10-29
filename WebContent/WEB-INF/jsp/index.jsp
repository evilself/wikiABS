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
    <meta name="description" content="Wiki page/Knowledge base">
    <meta name="author" content="BorisM">
    <title>Wiki</title>
    <!-- Bootstrap Core CSS -->
    <link href="<spring:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <!-- Custom CSS -->
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
                        <a href="#">About</a>
                    </li>
                    <li>
                        <a href="articles">Articles</a>
                    </li>
                    <li>
                        <a href="users">Login</a>
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
                        
                        	<sf:form action="articles" method="get" >
                        	   <input type="text" name="searchCriteria" id="searchCriteria" class="form-control" style="font-height:120%;height:45px;border-radius:10px; " placeholder="What are you looking for?"/>
		                       <button type="submit" style="margin:5px;" class="btn btn-default btn-lg">Search</button>
		                    </sf:form>                  
		                   
		                    <a href="articles?new" class="btn btn-default btn-lg">Create Article</a>
		                 
                        	
                        <!-- h3>What are you looking for?</h3 -->
                        <hr class="intro-divider">
                        <!--<ul class="list-inline intro-social-buttons">
                            <li>
                                <a href="https://twitter.com/SBootstrap" class="btn btn-default btn-lg"><i class="fa fa-twitter fa-fw"></i> <span class="network-name">Twitter</span></a>
                            </li>
                            <li>
                                <a href="https://github.com/IronSummitMedia/startbootstrap" class="btn btn-default btn-lg"><i class="fa fa-github fa-fw"></i> <span class="network-name">Github</span></a>
                            </li>
                            <li>
                                <a href="#" class="btn btn-default btn-lg"><i class="fa fa-linkedin fa-fw"></i> <span class="network-name">Linkedin</span></a>
                            </li> -->
                        </ul>
                    </div>
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>
    <!-- /.intro-header -->

    <!-- Page Content -->

    <div class="content-section-b">

        <div class="container">

            <div class="row">
                <div class="col-lg-5 col-sm-6">
                    <hr class="section-heading-spacer">
                    <div class="clearfix"></div>
                    <h2 class="section-heading">BankManagerElite</h2>
                    <p class="lead">
                    	<table cellspacing="5" >
					        <tr>
					            <th>Latest articles</th>					            
					        </tr>
					        <c:forEach items="#{users}" var="usr">
					            <tr>
					                <td>${usr.firstName}</td>
					                <td>${usr.lastName}</td>
					                <td>${usr.userName}</td>
					                <td>${usr.password}</td>
					                <td>
					                    <a href="users/${usr.id}">Go to page</a>
					                </td>					                
					            </tr>
					        </c:forEach>
					    </table>					    
                    </p>
                </div>
                <div class="col-lg-5 col-lg-offset-2 col-sm-6">
                    <img class="img-responsive" src="<spring:url value="/resources/img/bm_work.png"/>" alt="">
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>
    <!-- /.content-section-a -->

    <div class="content-section-a">

        <div class="container">

            <div class="row">
                <div class="col-lg-5 col-lg-offset-1 col-sm-push-6  col-sm-6">
                    <hr class="section-heading-spacer">
                    <div class="clearfix"></div>
                    <h2 class="section-heading">CompliancePro Loans and Deposits</h2>
                    <p class="lead">
                    	<table cellspacing="5" >
					        <tr>
					            <th>Latest articles</th>					            
					        </tr>
					        <c:forEach items="#{users}" var="usr">
					            <tr>
					                <td>${usr.firstName}</td>
					                <td>${usr.lastName}</td>
					                <td>${usr.userName}</td>
					                <td>${usr.password}</td>
					                <td>
					                    <a href="users/${usr.id}">Go to page</a>
					                </td>
					                <td>
					                    <sf:form action="users/${usr.id}" method="delete" >
					                        <input type="submit" value="" >Delete</input>
					                    </sf:form>
					                </td>
					            </tr>
					        </c:forEach>
					    </table>
                    </p>
                </div>
                <div class="col-lg-5 col-sm-pull-6  col-sm-6">
                    <img class="img-responsive" src="<spring:url value="/resources/img/ABSlogo.jpg"/>" alt="">
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>
    <!-- /.content-section-b -->

    <div class="content-section-b">

        <div class="container">

            <div class="row">
                <div class="col-lg-5 col-sm-6">
                    <hr class="section-heading-spacer">
                    <div class="clearfix"></div>
                    <h2 class="section-heading">CompliancePro</h2>
                    <p class="lead">
                    	<table cellspacing="5" >
					        <tr>
					            <th>Latest articles</th>					            
					        </tr>
					        <c:forEach items="#{users}" var="usr">
					            <tr>
					                <td>${usr.firstName}</td>
					                <td>${usr.lastName}</td>
					                <td>${usr.userName}</td>
					                <td>${usr.password}</td>
					                <td>
					                    <a href="users/${usr.id}">Go to page</a>
					                </td>
					                <td>
					                    <sf:form action="users/${usr.id}" method="delete" >
					                        <input type="submit" value="" >Delete</input>
					                    </sf:form>
					                </td>
					            </tr>
					        </c:forEach>
					    </table>
                    </p>
                </div>
                <div class="col-lg-5 col-lg-offset-2 col-sm-6">
                    <img class="img-responsive" src="<spring:url value="/resources/img/masthead.jpg"/>" alt="">
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>
    <!-- /.content-section-a -->

    <div class="banner">

        <div class="container">

            <div class="row">
                <div class="col-lg-6">
                    <h2>Useful Links</h2>
                </div>
                <div class="col-lg-6">
                    <ul class="list-inline banner-social-buttons">
                        <li>
                            <a href="https://twitter.com/SBootstrap" class="btn btn-default btn-lg"><i class="fa fa-twitter fa-fw"></i> <span class="network-name">Twitter</span></a>
                        </li>
                        <li>
                            <a href="https://github.com/IronSummitMedia/startbootstrap" class="btn btn-default btn-lg"><i class="fa fa-github fa-fw"></i> <span class="network-name">Github</span></a>
                        </li>
                        <li>
                            <a href="#" class="btn btn-default btn-lg"><i class="fa fa-linkedin fa-fw"></i> <span class="network-name">Linkedin</span></a>
                        </li>
                    </ul>
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>
    <!-- /.banner -->

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
                            <a href="#about">About</a>
                        </li>
                        <li class="footer-menu-divider">&sdot;</li>
                        <li>
                            <a href="articles">Articles</a>
                        </li>
                        <li class="footer-menu-divider">&sdot;</li>
                        <li>
                            <a href="users">Login</a>
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

</body>

</html>
    