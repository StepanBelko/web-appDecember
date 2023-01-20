<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Users HomePage</title>
    <link href="../templates/css/style.css" rel="stylesheet" type="text/css">
    <style>
        body {
            background: #eee; /* цвет фона страницы */
        }

        .table {
            border: 1px solid #eee;
            table-layout: auto;
            width: 100%;
            margin-bottom: 20px;
            border-collapse: collapse;
        }

        .table th {
            font-weight: bold;
            padding: 5px;
            background: #efefef;
            border: 1px solid #dddddd;
        }

        .table td {
            padding: 5px 10px;
            border: 1px solid #eee;
            text-align: left;
        }

        .table tbody tr:nth-child(odd) {
            background: #fff;
        }

        .table tbody tr:nth-child(even) {
            background: #F7F7F7;
        }

        .table caption {
            margin: 0; /* убираем отступы */
            background-color: #282830; /* фон заголовка */
            border-radius: 10px 10px 0 0; /* закругляем углы сверху */
            color: #fff; /* цвет текста */
            font-size: 14px; /* размер шрифта */
            padding: 20px; /* отступы */
            text-align: center; /* выравниваем текст по центру */
            text-transform: uppercase; /* все символы заглавные */
        }
    </style>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<br>
<table class="table">
    <caption>Users database</caption>
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Password</th>
        <th>Office</th>
        <th>Is Active</th>
        <th>Created time</th>
        <th>Last update</th>
        <th>Roles</th>

        <th>Update</th>
        <th>Delete</th>
    </tr>

    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td><a href="offices?id=${user.office.id}">view details</a></td>
            <td><a>${user.is_active ? "Active" : "-"}</a></td>
            <td>${user.created_ts}</td>
            <td>${user.updated_ts}</td>
            <td>
                <c:if test="${fn:length(user.role) != 0}">
                    <a href="roles?userId=${user.id}">View roles list</a>
                </c:if>
                <c:if test="${fn:length(user.role) == 0}">
                    <a>-</a>
                </c:if>

            </td>

            <td>
                <button><a href="homePage?action=Upd&userId=${user.id}">UPD usr:${user.id}</a></button>
            </td>
            <td>
                <button><a href="homePage?action=Del&userId=${user.id}">DEL usr:${user.id}</a></button>
            </td>

        </tr>
    </c:forEach>
</table>

<button>
    <a href="homePage?action=Crt">Create new user</a>
</button>

<c:if test="${user != null}">
    <button><a href="resetPass">Reset Password for ${user.email}</a></button>
</c:if>

<br>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
