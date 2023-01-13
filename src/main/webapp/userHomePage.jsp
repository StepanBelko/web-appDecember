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
    //Лучше брать пользователя из сессии или по email из DAO?
//    String email = request.getParameter("email");
//    User user = new UsersDAO().getByEmail(email);

    User user = (User) session.getAttribute("user");
    String email = user.getEmail();
    String name = user.getName();

    Set<User> userSet = new UsersDAO().getAll();


    StringBuilder userTable = new StringBuilder();

    userTable.append("<table class='table'>\n" +
            "    <caption>Users database</caption>\n" +
            "    <tr>\n" +
            "        <th>id</th>\n" +
            "        <th>Name</th>\n" +
            "        <th>Email</th>\n" +
            "        <th>Password</th>\n" +
            "        <th>Office Id</th>\n" +
            "        <th>Is Active</th>\n" +
            "        <th>Created time</th>\n" +
            "        <th>Last update</th>\n" +
            "    </tr>\n");


    for (User users : userSet) {
        userTable.append("<tr>").append("<td>");
        userTable.append(users.getId()).append("</td><td>").append(users.getName()).append("</td><td>").append(users.getEmail()).append("</td><td>");
        userTable.append(users.getPassword()).append("</td><td>").append(users.getOffice_id()).append("</td><td>");
        userTable.append(users.is_active()).append("</td><td>").append(users.getCreated_ts()).append("</td><td>").append(users.getUpdated_ts()).append("</td></tr>");

    }
    userTable.append("</table>");
%>
<html>
<head>
    <title><%=name%>  HomePage</title>
    <link href="templates/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1><%=name%>  HomePage</h1>
Session : ID = <%=session.getId()%>, servlet_context = <%=session.getServletContext()%><br>
User in session : <%=session.getAttribute("user")%>    <br>
<br>
<%--<table>--%>
<%--    <caption>Users database</caption>--%>
<%--    <tr>--%>
<%--        <th>id</th>--%>
<%--        <th>Name</th>--%>
<%--        <th>Email</th>--%>
<%--        <th>Password</th>--%>
<%--        <th>Office Id</th>--%>
<%--        <th>Is Active</th>--%>
<%--        <th>Created time</th>--%>
<%--        <th>Last update</th>--%>
<%--    </tr>--%>
<%=userTable%>
<%--</table>--%>
<br>
<form action="resetPass" target="_self" method="get">
    <input type='email' id='email' name='email' value="<%=email%>" readonly><br>
    <button>Reset Password</button>
</form>

<form action="logout" target="_blank" method="get">
    <button>LogOut</button>
</form>


</body>
</html>
