<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>.:: WebApp ::.</title>
<style>
body {font-family: Arial, Helvetica, sans-serif;}

/* Full-width input fields */
input[type=text], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

/* Set a style for all buttons */
button {
  background-color: #04AA6D;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button:hover {
  opacity: 0.8;
}

/* Extra styles for the cancel button */
.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

/* Center the image and position the close button */
.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
  position: relative;
}

img.avatar {
  width: 40%;
  border-radius: 50%;
}

.container {
  padding: 16px;
}

span.psw {
  float: right;
  padding-top: 16px;
}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
  padding-top: 60px;
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}

/* The Close Button (x) */
.close {
  position: absolute;
  right: 25px;
  top: 0;
  color: #000;
  font-size: 35px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: red;
  cursor: pointer;
}

/* Add Zoom Animation */
.animate {
  -webkit-animation: animatezoom 0.6s;
  animation: animatezoom 0.6s
}

@-webkit-keyframes animatezoom {
  from {-webkit-transform: scale(0)} 
  to {-webkit-transform: scale(1)}
}
  
@keyframes animatezoom {
  from {transform: scale(0)} 
  to {transform: scale(1)}
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
     display: block;
     float: none;
  }
  .cancelbtn {
     width: 100%;
  }
}
</style>

<style type="text/css">
        body {
            font-family:Arial;
            color:green;
        }
    </style>
      
    <script type="text/javascript">
        window.history.forward();
        function noBack() {
            window.history.forward();
        }
    </script>
</head>
<body>
<%
if(session.getAttribute("username") == null){
	response.sendRedirect(request.getContextPath()+"/login");
}
%>
	<h1>Welcome in HomePage</h1>
	<div class="row">
		<div class="col-md-12">
			<%
			Object o = "abc" + session.getAttribute("A143");
			if (o.equals("abc2")) {
				if (session.getAttribute("uploadTest") != null) {
		%>
			<div
				class="alert alert-<%=session.getAttribute("alertType")%> alert-dismissable">
				<i class="fa fa-check"></i>
				<!-- <button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button> -->
				<b>Alert! </b><%=session.getAttribute("uploadTest")%>
			</div>
			<%
			}
				session.removeAttribute("A143");
				session.removeAttribute("uploadTest");
			}
		%>
<form action="uploadfile" method="post"  enctype="multipart/form-data">
	<!-- <label class="col-sm-2 required"> File Type </label>
	<div class="col-sm-2 ">
			<select name="fileType" id="fileType" class="validate[required] form-control">
				<option value="">Select Type</option>
				<option value="IPL">IPL</option>
				<option value="other">Other</option>
			</select>
	</div> -->
<label class="col-sm-2 required"><b>Select file:</b></label><input type="file" name="file" size="50" />
<input type="submit" name="submit" >
<br /></form>
	<form class="modal-content animate" action="<%=request.getContextPath()%>/logout" method="Post">
		<button type="submit">Logout</button>
	</form>
	</div>
</body>
</html>