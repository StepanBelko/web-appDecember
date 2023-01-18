<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>${user.name} HomePage</title>
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
        <th>Action</th>
    </tr>

    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td title="${user.office}">${user.office}</td>
            <td>как получить boolean поле is_active?</td>
            <td>${user.created_ts}</td>
            <td>${user.updated_ts}</td>
            <td>
                <c:if test="${fn:length(user.role) >= 1}">
                    <c:forEach var="role" items="${user.role}">
                        <a href="${role.name.toLowerCase()}">${role.name}</a> <br>
                    </c:forEach>
                </c:if>

                <c:if test="${(fn:length(user.role) < 1)}">
                    <a>NONE</a>
                </c:if>
            </td>
            <td>

                <c:if test="${(fn:length(userInSession.role) >= 1)}">

                    <c:forEach var="role" items="${userInSession.role}">
                        <c:if test="${role.id <=2}">

                            <form action="update" style="display: inline">
                                <button>Update</button>
                            </form>
                        </c:if>


                        <c:if test="${role.id <=1}">
                            <form action="delete" style="display: inline" method="get">
                                <input name="deleteUserId" value="${user.id}" hidden>
                                <input class="btn" type='submit' value='DELETE'>
                            </form>
                        </c:if>


                    </c:forEach>

                </c:if>
                <c:if test="${fn:length(userInSession.role) < 1}">
                    <a>Login for action</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<c:if test="${user != null}">
    <form action="resetPass" target="_self" method="get">
        <input type='email' id='email' name='email' value="${user.email}" readonly><br>
        <button>Reset Password</button>
    </form>
    <form action="logout" target="_blank" method="get">
        <button>LogOut</button>
    </form>
</c:if>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
