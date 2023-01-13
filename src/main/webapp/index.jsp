<%@ page import="java.util.Date" %>

<%-- #1 Declaration--%>
<%!
    Date serverDate = new Date();
    int n = 3;
%>

<%-- #2 Scriptlet--%>
<%
    System.out.println("Scriptlet" + serverDate);

    for (int i = 0; i < 5; i++) {%>
<h4><%=i%>
</h4>
<%}%>


<html>
<body>
<h2>Start page</h2>


<% if (n == 5) {%>
<button>
    Hi, <%=n%>
</button>
<%} else {%>
<a>
    No button. n = <%=n%>
</a>
<%}%>


<%-- #3 Expression--%>
<h3><%=serverDate%>
</h3>

<a href='test'> test </a> <br>
<a href='test2'> test2 </a> <br>
<a href='homeWorkServlets'> Count number servlet </a> <br>
<a href='login'> Login </a> <br>
<a href='registration'> Registration </a> <br>
</body>
</html>