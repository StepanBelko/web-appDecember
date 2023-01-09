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

<div class="MyForm" style="height: 325px">
    <h1>Reset password</h1>
    <div class="inp">
        <form action='resetPass' method="post">
            <input class="inputUp" type='email' id='email' name='email' value="<%=email%>"
                   placeholder="old password">
            <input class="inputMiddle" type='password' id='oldPassword' name='oldPassword' required
                   placeholder="old password">
            <input class="inputMiddle" type='password' id='newPassword' name='newPassword' required
                   placeholder="new password">
            <input class="inputDown" type='password' id='newPassword2' name='newPassword2' required
                   placeholder="repeat new password">
                <br>
                <br>

            <input class="btn2" type='submit' value='Submit'>
        </form>
    </div>

</div>

</body>
</html>
