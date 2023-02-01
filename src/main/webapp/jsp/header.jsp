<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        .topnav {
            overflow: hidden;
            background-color: #333;
        }

        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        .topnav a.active {
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>
<body>

<div class="topnav">
    <a class="active" href="http://localhost:8080/web-app/">Start page</a>
    <a href="homePage">Users</a>
    <a href="offices">Offices</a>
    <a href="roles">Roles</a>

    <c:if test="${user == null}">
        <a class="active" style="float: right" href="login">Login</a>
    </c:if>
    <c:if test="${user != null}">
        <a class="active" style="float: right" href="logout">Logout</a>
        <a style="float: right">${user.email}</a>
    </c:if>

</div>

</body>