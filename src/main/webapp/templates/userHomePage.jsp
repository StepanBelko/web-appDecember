<%@ page import="by.itstep.stpnbelko.homework.model.User" %>
<%@ page import="by.itstep.stpnbelko.homework.dao.impl.UsersDAO" %>
<%@ page import="java.util.Set" %>
<%--
  Created by IntelliJ IDEA.
  User: skynet
  Date: 6.01.23
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String email = request.getParameter("email");
    User user = new UsersDAO().getByEmail(email);
    String name = user.getName();

    StringBuilder stringBuilder = new StringBuilder();

    Set<User> userSet = new UsersDAO().getAll();

    for (User users : userSet) {
        stringBuilder.append(users).append("<br>");
    }

%>
<html>
<head>
    <title><%=name%>  HomePage</title>
</head>
<body>
<h1><%=name%>  HomePage</h1>
<br>
<%=stringBuilder%>
<br>
<form action="resetPass" target="_blank" method="get">
    <label for='email'>email</label><br>
    <input type='email' id='email' name='email' value="<%=email%>" readonly><br>
    <button>Reset Password</button>
</form>

<form action="logOut" target="_blank" method="get">
    <button>LogOut</button>
</form>


</body>
</html>
