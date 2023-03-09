<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.commen.controller.EncryptionUtil"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
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
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<ul>
  <li><a href="#home">Home</a></li>
  <li><a class="active" href="#">Cricket</a></li>
  <li><a href="#contact">Contact</a></li>
  <li><a href="#about">About</a></li>
  <li style="float:right"><a class="active" href="getprofile"><%= request.getSession().getAttribute("username") %></a></li>
</ul>
	<br/>
					<form class="form-horizontal" id="form1" action="<%=request.getContextPath()%>/cricketpage" method="POST">
							<br>
							<div class="box-body topPadding">
								<div class="row ">
									<label class="col-sm-2 ">Year</label>
									<div class="col-sm-4 ">
										<select name="year" id="year" class=" form-control" required="required">
											<option value="<c:out value=""></c:out>"><c:out	value="Select All"></c:out></option>
											<option value="<c:out value="2022"></c:out>"><c:out	value="2022"></c:out></option>
											<option value="<c:out value="2023"></c:out>"><c:out	value="2023"></c:out></option>
											<option value="<c:out value="2024"></c:out>"><c:out	value="2024"></c:out></option>
										</select>
									</div>
								<div class="row form-panel">
									<div class="col-sm-4">
										<input title="Search" value="Search" type="submit" class="btn btn-primary">
									</div>
								</div>
							</div>
						</div>
					</form>
</body>
</html>