<%@ page import="by.itstep.stpnbelko.homework.model.User" %>
<%@ page import="by.itstep.stpnbelko.homework.dao.impl.UsersDAO" %>
<%@ page import="java.util.Set" %>
<%@ page import="by.itstep.stpnbelko.homework.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //Лучше брать пользователя из сессии или по email из DAO?
//    String email = request.getParameter("email") ;
//    User user = new UsersDAO().getByEmail(email);

    User sessionUser = (User) session.getAttribute("user");
    String email = sessionUser.getEmail();
    String name = sessionUser.getName();

    Set<User> userSet = (Set<User>) request.getAttribute("users");
%>
<html>
<head>
    <title><%=name%>  HomePage</title>
    <link href="../templates/css/style.css" rel="stylesheet" type="text/css">
    <style>
        body {
            background: #eee; /* цвет фона страницы */
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
<h1><%=name%>  HomePage</h1>
Session : ID = <%=session.getId()%>, servlet_context = <%=session.getServletContext()%><br>
User in session : <%=session.getAttribute("user")%>    <br>
<br>
<table class="table">
    <caption>Users database</caption>
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Password</th>
        <th>Office Id</th>
        <th>Is Active</th>
        <th>Created time</th>
        <th>Last update</th>
        <th>Roles</th>
    </tr>

    <%for (User user : userSet) {%>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getPassword()%></td>
        <td><%=user.getOffice_id()%></td>
        <td><%=user.is_active()%></td>
        <td><%=user.getCreated_ts()%></td>
        <td><%=user.getUpdated_ts()%></td>
        <td><%if (user.getRole().size() >= 1) {%>
                <%for(Role role : user.getRole()) {%>
                <a href="<%=role.getName().toLowerCase()%>"><%=role.getName()%></a> <br>
                <%}%>
            <%} else {%>
            <a>NONE</a>
            <%}%>
        </td>
    </tr>
    <%}%>
</table>
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
