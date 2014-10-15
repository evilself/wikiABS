<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Welcome to the Wiki!</title>
</head>
<body>
	<h1>Welcome to the Wiki App!</h1>
 
    <ul>
        <li><a href="users">List users</a></li>
        <li><a href="articles">List articles</a></li>        
    </ul>
    
    Today is: <fmt:formatDate value="${today}" pattern="dd-MM-yyyy" />
</body>
</html>