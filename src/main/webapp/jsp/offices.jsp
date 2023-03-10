<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Offices</title>
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

<table class="table">
    <caption>Offices database</caption>
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>Location</th>
        <th>Phone</th>
        <th>Fax</th>
    </tr>
    <c:forEach var="office" items="${offices}">
        <tr>
            <td>${office.id}</td>
            <td>${office.name}</td>
            <td>${office.location}</td>
            <td>${office.phone}</td>
            <td>${office.fax}</td>
        </tr>
    </c:forEach>

</table>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
