<%@ page import="java.util.Date" %>

<%-- #1 Declaration--%>
<%!
    Date serverDate = new Date();
    int n = 3;
%>

<%-- #3 Expression--%>
<h3><%=serverDate%>
</h3>

<a href='test'> test </a> <br>
<a href='test2'> test2 </a> <br>
<a href='homeWorkServlets'> Count number servlet </a> <br>
<a href='login'> Login </a> <br>
<a href='logout'> Logout </a> <br>
<a href='homePage'> Home Page </a> <br>
<a href='registration'> Registration </a> <br>



<p>Modify the text in the input field, then click outside the field to fire the onchange event.</p>

Enter some text: <input type="text" name="txt" value="Hello" onchange="myFunction(this.value)">

<script>
    function myFunction(val) {
        alert("The input value has changed. The new value is: " + val);
    }
</script>



</body>
</html>