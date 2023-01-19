<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Offices</title>
</head>
<body>

<table class="table">
  <caption>Users database</caption>
  <tr>
    <th>id</th>
    <th>Name</th>
    <th>Location</th>
    <th>Phone</th>
    <th>Fax</th>
  </tr>
  <c:forEach var="office" items="offices">
<tr>
  <td>${office.id}</td>
  <td>${office.name}</td>
  <td>${office.location}</td>
  <td>${office.phone}</td>
  <td>${office.fax}</td>
</tr>
  </c:forEach>

</table>


</body>
</html>
