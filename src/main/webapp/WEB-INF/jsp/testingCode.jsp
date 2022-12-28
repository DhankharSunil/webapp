<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Job Role</title>
</head>
<body>
<form class="modal-content animate" name="form1" action="<%=request.getContextPath()%>/checkNumber" method="Post">
	<div class="container">
	 	<input type="number" placeholder="Enter Value" name="numvalue" required>
	 	<button type="submit">Submit</button>
	</div>
</form>


<div class="row">
		<div class="col-md-12">
			<div class="alert alert-<%=session.getAttribute("alertType")%> alert-dismissable">
				<i class="fa fa-check"></i>
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button> <%=session.getAttribute("retext")%>
			</div>
			<%
				session.removeAttribute("retext");
		%>
		</div>
		</div>
</body>
</html>