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
    <link href="templates/css/style.css" rel="stylesheet" type="text/css">
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
