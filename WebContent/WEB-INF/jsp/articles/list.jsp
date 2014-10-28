<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
 
<html>
<head>
    <title>Articles</title>
    <!-- link rel="stylesheet" href="/timesheet-app/resources/style.css" type="text/css"-->
</head>
<body>
    <h1>List of articles</h1>
    <a href="articles?new">Add new article</a>
    <table cellspacing="5" >
        <tr>
            <th>Title</th>
            
        </tr>
        <c:forEach items="#{articles}" var="art">
            <tr>
                <td>${art.title}</td>                
                <td>
                    <a href="articles/${art.id}">Read</a>
                </td>
                <td>
                    <sf:form action="articles/${art.id}" method="delete" >
                        <input type="submit" value="" >Delete</input>
                    </sf:form>
                </td>
            </tr>
        </c:forEach>
    </table>
 
    <br />
    <a href="/">Home</a>
</body>
</html>