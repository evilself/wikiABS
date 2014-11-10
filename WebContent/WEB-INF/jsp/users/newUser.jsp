<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new user</title>
    
</head>
<body>
    <h2>Add new user</h2>
    <div id="list">
        <sf:form method="post" action="users">
            <ul>
                <li>
                    <label for="firstName">First Name:</label>
                    <input name="firstName" id="firstName" value="${user.firstName}"/>
                </li>
                <li>
                    <label for="lastName">Last Name:</label>
                    <input name="lastName" id="lastName" value="${user.lastName}"/>
                </li>
                <li>
                    <label for="userName">username</label>
                    <input name="userName" id="userName" value="${user.userName}"/>
                </li>
                <li>
                    <label for="password">Password:</label>
                    <input name="password" id="password" value="${user.password}"/>
                </li>
                <li>
                    <input type="submit" value="Save" id="save" />
                </li>
            </ul>
        </sf:form>
    </div>
 
    <br /><br />
    <a href="users">Go Back</a>
</body>
</html>