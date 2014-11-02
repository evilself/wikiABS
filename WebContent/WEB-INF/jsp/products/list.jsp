<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
 
<html>
<head>
    <title>Users</title>
    <!-- link rel="stylesheet" href="/timesheet-app/resources/style.css" type="text/css"-->
</head>
<body>
    <h1>List of users</h1>
    <a href="users?new">Add new employee</a>
    <table cellspacing="5" >
        <tr>
            <th>first name</th>
            <th>last name</th>
            <th>username</th>
            
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
 
    <br />
    <a href="welcome">Go back</a>
</body>
</html>