<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: skynet
  Date: 19.01.23
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new user</title>
<%--    <link href="templates/css/style.css" rel="stylesheet" type="text/css">--%>
    <style>
        body {
            background: #eee; /* цвет фона страницы */
        }

        .MyForm {
            width: 300px; /* ширина блока */
            height: auto; /* высота блока */
            background: #fff; /* фон блока */
            border-radius: 10px; /* закругленные углы блока */
            margin: 10% auto; /* отступ сверху и выравнивание по середине */
            box-shadow: 2px 2px 4px 0px #000000; /* тень блока */
        }

        .MyForm h1 {
            margin: 0; /* убираем отступы */
            background-color: #282830; /* фон заголовка */
            border-radius: 10px 10px 0 0; /* закругляем углы сверху */
            color: #fff; /* цвет текста */
            font-size: 14px; /* размер шрифта */
            padding: 20px; /* отступы */
            text-align: center; /* выравниваем текст по центру */
            text-transform: uppercase; /* все символы заглавные */
        }

        .inp {
            padding: 20px; /* отступы */
        }

        .inputUp {
            border: 1px solid #dcdcdc; /* рамка */
            padding: 12px 10px; /* отступы текста */
            width: 240px; /* ширина */
            border-radius: 5px 5px 0 0; /* закругленные углы сверху */
        }

        .inputMiddle {
            border: 1px solid #dcdcdc; /* рамка */
            padding: 12px 10px; /* отступы текста */
            width: 240px; /* ширина */
        }

        .inputDown {
            border: 1px solid #dcdcdc; /* рамка */
            padding: 12px 10px; /* отступы текста */
            width: 240px; /* ширина */
            border-radius: 0px 0px 5px 5px; /* закругленные углы снизу */
        }

        .btn {
            background: #1dabb8; /* фон */
            border-radius: 5px; /* закругленные углы */
            color: #fff; /* цвет текста */
            float: right; /* выравнивание справа */
            font-weight: bold; /* жирный текст */
            margin: 10px; /* отступы */
            padding: 12px 20px; /* оступы для текста */
        }

        .btn2 {
            display: block;
            background: #1dabb8; /* фон */
            border-radius: 5px; /* закругленные углы */
            color: #fff; /* цвет текста */
            font-weight: bold; /* жирный текст */
            margin-outside: 10px;
            margin-left: auto;
            margin-right: auto;
            padding: 12px 20px; /* оступы для текста */
        }

        .info {
            display: block;
            width: 130px; /* ширина */
            float: left; /* выравнивание слева */
            padding-top: 20px; /* оступ сверху для текста */
        }

        a {
            color: #999; /* цвет ссылки */
            text-decoration: none; /* убираем подчеркивание */

        }

        a:hover {
            color: #1dabb8; /* цвет ссылки при наведении */
        }

        .table{
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
        .table td{
            padding: 5px 10px;
            border: 1px solid #eee;
            text-align: left;
        }
        .table tbody tr:nth-child(odd){
            background: #fff;
        }
        .table tbody tr:nth-child(even){
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
Offices List : ${offices}

<div class="MyForm">
    <h1>Create new user</h1>
    <div class="inp">
        <form action='homePage?action=Crt' method="post">
            <input class="inputUp" type='text' id='name' name='name' placeholder="name" required><br>
            <input class="inputMiddle" type='email' id='email' name='email' placeholder="email" required><br>
            <input class="inputMiddle" type='password' id='password' name='password' placeholder="password" required>
            <select class="inputDown" name="office_id" required="required">
                <c:forEach var="office" items="${offices}">
                    <option value="${office.id}" ${office.id == 1 ? 'selected' : ''}>${office.location}</option>
                </c:forEach>
            </select> <br><br>
            <c:forEach var="role" items="${roles}">
                <input name="role_id" ${role.id == 4? 'checked' : ''} type="checkbox" value="${role.id}" id="roleId">
                <label for="roleId">${role.name}</label> <br>
            </c:forEach>
            <br>
            <label for="is_active">Is active</label>
            <input type="checkbox" id='is_active' name='is_active' value="true">
            <br><br>
            <input class="btn2" type='submit' value='Submit'>
        </form>
    </div>

</div>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
