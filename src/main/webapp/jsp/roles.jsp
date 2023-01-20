<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: skynet
  Date: 19.01.23
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Roles</title>
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

<table class="table">
    <caption>Roles database</caption>
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>Description</th>
    </tr>
    <c:forEach var="role" items="${roles}">
        <tr>
            <td>${role.id}</td>
            <td>${role.name}</td>
            <td>${role.description}</td>
        </tr>
    </c:forEach>

</table>


<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
