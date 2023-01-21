<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: skynet
  Date: 6.01.23
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String email = request.getParameter("email");
    if (email == null) {
        email = "email";
    }
%>
<html>
<head>
    <title>Reset password</title>
    <link href="templates/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<a>Updated user : ${updatedUser}</a> <br>
<a>Offices list : ${offices}</a>

<div class="MyForm" style="height: auto">
    <h1>Update user</h1>
    <div class="inp">
        <form action='homePage?action=Upd&userId=${updatedUser.id}' method="post">
            <input class="inputUp" type='text' id='name' name='name' value="${updatedUser.name} " мrequired><br>
            <input class="inputMiddle" type='email' id='email' name='email' value="${updatedUser.email}" required><br>
            <input class="inputMiddle" type='text' id='password' name='password'
                   value="${updatedUser.password}" required>
            <select class="inputDown" name="office_id" required="required">
                <c:forEach var="office" items="${offices}">
                    <option value="${office.id}" ${office.id == updatedUser.office_id ? 'selected' : ''}>${office.location}</option>
                </c:forEach>
            </select> <br><br>

            <c:forEach var="role" items="${roles}">
                <input name="role_id"
                <c:forEach var="usrRole" items="${updatedUser.role}">
                    ${usrRole.id == role.id ? 'checked' : ''}
                </c:forEach> type="checkbox" value="${role.id}" id="roleId">
                <label for="roleId">${role.name}</label> <br>
            </c:forEach>
            <br><br>

            <label for="is_active">Is active</label>
            <input type="checkbox" id='is_active' name='is_active' value="true">
            <br><br>
            <input class="btn2" type='submit' value='Submit'>
        </form>
    </div>

</div>


<%--<jsp:include page="footer.jsp"></jsp:include>       --%>
</body>
</html>
