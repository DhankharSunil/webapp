<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

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
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
	padding-top: 60px;
}

/* Modal Content/Box */
.modal-content {
	background-color: #fefefe;
	margin: 5% auto 15% auto;
	/* 5% from the top, 15% from the bottom and centered */
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

.close:hover, .close:focus {
	color: red;
	cursor: pointer;
}

/* Add Zoom Animation */
.animate {
	-webkit-animation: animatezoom 0.6s;
	animation: animatezoom 0.6s
}

@
-webkit-keyframes animatezoom {
	from {-webkit-transform: scale(0)
}

to {
	-webkit-transform: scale(1)
}

}
@
keyframes animatezoom {
	from {transform: scale(0)
}

to {
	transform: scale(1)
}

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
<script type="text/javascript">

</script>

</head>
<body>
	<form class="modal-content animate" name="form1"<%-- action="<%=request.getContextPath()%>/password-change" --%> method="Post">
		<div class="row">
			<div class="col-md-2">
				<%
				Object o = "abc" + session.getAttribute("A143");
				if (o.equals("abc2")) {
					if (session.getAttribute("Message143") != null) {
				%>
				<div
					class="alert alert-<%=session.getAttribute("alertType")%> alert-dismissable">
					<i class="fa fa-check"></i>
					<!-- <button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button> -->
					<b>Alert! </b><%=session.getAttribute("Message143")%>
				</div>
				<%
				}
				session.removeAttribute("A143");
				session.removeAttribute("Message143");
				}
				%>
				<div class="imgcontainer">
					<div class="container">
						<h1	style="color: #247998; text-align: center; font-family: proxima-nova; margin-left: -10px; margin-right: auto; margin-bottom: 40px; margin-top: -30px;">OTP</h1>
						<label for="otp"><b>OTP</b></label>
						<input type="text" placeholder="Enter OTP" name="mailotp" Maxlength="6"
							oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*?)\..*/g, '$1').replace(/^0[^.]/, '0');" required>
						<button type="submit">Submit</button>
						<span class="group-btn"> <a	href="<%=request.getContextPath()%>/login"
							style="color: #1b7e9f;"><i class="fa fa-key" aria-hidden="true"> </i>
								<button type="button" style="color: white; background: #04AA6D; padding: 12px; margin: 0px, 10px; border-color: #04AA6D; text-transform: none;"
									value="Login">Login</button></a>
						</span> <a href="<%=request.getContextPath()%>/registerpage"
							style="color: #1b7e9f; margin: 10px" tabindex="7">
							<i class="fa fa-key" aria-hidden="true"> </i>Create an Account
						</a>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>