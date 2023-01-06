<%--
  Created by IntelliJ IDEA.
  User: skynet
  Date: 6.01.23
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reset password</title>
</head>
<body>
<form action='resetPass' method="post">

  <label for='email'>email</label><br>
  <input type='email' id='email' name='email' value="<%=request.getParameter("email")%>" readonly><br>
  <label for='oldPassword'>old password:</label><br>
  <input type='password' id='oldPassword' name='oldPassword' required> <br><br>
  <label for='newPassword'>new password:</label><br>
  <input type='password' id='newPassword' name='newPassword' required><br><br>
  <label for='newPassword2'>new password:</label><br>
  <input type='password' id='newPassword2' name='newPassword2' required><br><br>

  <input type='submit' value='Submit'>
</form>
</body>
</html>
