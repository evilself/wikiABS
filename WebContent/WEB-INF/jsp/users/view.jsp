<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
 
<html>
<head>
    <title>User page</title>
  
</head>
<body>
    <h2>User info</h2>
    <div id="list">
        <sf:form method="post">
            <ul>
                <li>
                    <label for="name">First Name:</label>
                    <input name="name" id="name" value="${user.firstName}" disabled="true"/>
                </li>
                <li>
                    <label for="department">Last Name:</label>
                    <input name="department" id="department" value="${user.lastName}" disabled="true" />
                </li>
                <li>
                    <input type="button" value="Unlock" id="unlock" />
                    <input type="submit" value="Save" id="save" class="hidden" />
                </li>
            </ul>
        </sf:form>
    </div>
 
    <br /><br />
    <a href="../users">Go Back</a> 
   
</body>
</html>