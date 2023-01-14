<%@ page import="by.itstep.stpnbelko.homework.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% User user = (User) request.getAttribute("userToDelete"); %>
<html>
<head>
    <title>Delete message</title>
    <link href="templates/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>


<div class="MyForm" style="height: 250px">
    <h1>Delete user</h1>
    <div class="inp">
        <a>User <%=user.getName()%> successfully deleted</a>
            <br>
        <br>
        <br>
        <a href="homePage">
            <button class="btn2">Back to home page</button>
        </a>

    </div>

</div>


<%--<h1>User <%=user.getName()%> successfully deleted</h1>--%>
<%--<a href="homePage" methods="get">Back to homepage</a>--%>
</body>
</html>
