<%@page import="com.commen.controller.EncryptionUtil"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
<style>
body {
  font-size: 28px;
}

ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
  position: -webkit-sticky; /* Safari */
  position: sticky;
  top: 0;
}

li {
  float: left;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover {
  background-color: #111;
}

.active {
  background-color: #4CAF50;
}
 th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #04AA6D;
  color: white;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<%! String url="/login"; %>
<%
		Object user_name = session.getAttribute("username");
		if (user_name == null) {
		response.sendRedirect(request.getContextPath()+url);
		}
	%>
	
	<ul>
  <li><a href="#home">Home</a></li>
  <li><a class="active" href="#">Cricket</a></li>
  <li><a href="#contact">Contact</a></li>
  <li><a href="#about">About</a></li>
  <li style="float:right"><a class="active" href="getprofile"><%= request.getSession().getAttribute("username") %></a></li>
</ul>
	<br/>
	
							<table>
								<thead>
									<tr>
									<tr>
										<th>Date</th>
										<th>Time</th>
										<th>Home Team</th>
										<th>Team</th>
										<th>City</th>
										<th>Dekh</th>
										<th>Chal</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="data" items="${data}">
										<tr>
											<td>${data.getDate()}</td>
											 <td>${data.getTime()}</td>
											<td>${data.getMatch() }</td>
											<td>${data.getVsmatch() }</td>
											<td>${data.getCityPlace() }</td>
											<td>Comming Soon...</td>
											<td>Comming Soon...</td>
										</tr>
									</c:forEach>

								</tbody>
							</table>

</body>
</body>
</html>