<%--
  Created by IntelliJ IDEA.
  User: skynet
  Date: 20.01.23
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String action = request.getParameter("action"); %>
<html>
<head>
    <title>Confirmation</title>
    <link href="templates/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>


<a></a> <br>

<div class="MyForm">
    <h1>A you sure want to <%=action%> ?</h1>
    <div class="inp">
        <form action='homePage?action=<%=action%>&userId=${userToDelete.id}' method="post">
            <a type='text' id='name' name='name'>Name: ${userToDelete.name}</a><br>
            <a type='email' id='email' name='email'>Email: ${userToDelete.email}</a><br>
            <a type='password' id='password' name='password'>Password: ${userToDelete.password}</a>  <br>
            <a name="office_id">Office: ${userToDelete.office.location}</a> <br>
            <a>${userToDelete.is_active ? "Active" : "Inactive"}</a>
            <%--    <label for="is_active">Is active</label>--%>
            <%--    <input type="checkbox" id='is_active' name='is_active' value="true">--%>
            <br><br>
            <input class="btn" type="button" onclick="history.back();" value="No"/>
            <input class="btn" type='submit' value='Yes'>
        </form>
    </div>

</div>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
