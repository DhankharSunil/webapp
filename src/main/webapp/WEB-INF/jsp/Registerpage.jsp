<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
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
<script type="text/javascript">
	function onCheck() {
		  const password = document.querySelector('input[name=password]');
		  const confirm = document.querySelector('input[name=confirm]');
		  if (confirm.value === password.value) {
			  confirm.setCustomValidity('');
		  } else {
			  confirm.setCustomValidity('Passwords do not match');
		  }
	}
	</script>
	<script type="text/javascript">
	function getstutasemail(inputText) {
		  var mailFormat =  /^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i;
		  const confirmEmail = document.querySelector('input[name=email]');
		 if (inputText.value.match(mailFormat)) {
			 confirmEmail.setCustomValidity('');
		    return true;
		  } else {
			  confirmEmail.setCustomValidity('Invalid Email address!');
			  alert("Invalid Email");
		    return false;
		  }
	}
</script>
<script type="text/javascript">
	function validateEmail(inputText) {
		  var mailFormat =  /^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i;
		/* var mailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/; */
		const confirm = document.querySelector('input[name=email]');
		 if (inputText.value.match(mailFormat)) {
			  document.form1.action="registeruser";
				document.form1.submit();
		    return true;
		  } else {
			  confirm.setCustomValidity('Invalid Enter Email address!');
		    return false;
		  }
	}
	
</script>
</head>
<body>
<h2>Register Form</h2>
<div class="row">
		<div class="col-md-12">
			<%
			Object o = "abc" + session.getAttribute("A143");
			if (o.equals("abc2")) {
				if (session.getAttribute("usrMsg") != null) {
		%>
			<div
				class="alert alert-<%=session.getAttribute("alertType")%> alert-dismissable">
				<i class="fa fa-check"></i>
				<!-- <button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button> -->
				<b>Alert! </b><%=session.getAttribute("usrMsg")%>
			</div>
			<%
			}
				session.removeAttribute("");
				session.removeAttribute("usrMsg");
			}
		%>
<form class="modal-content animate" name="form1"  action="<%=request.getContextPath()%>/registeruser" method="Post">
    <div class="container">
    	<label for="fname"><b>First Name</b></label>
      <input type="text" placeholder="Enter First Name" name="fname" required>
      <label for="lname"><b>Last Name</b></label>
      <input type="text" placeholder="Enter Last Name" name="lname" required>
      <label for="email"><b>E-mail</b></label>
      <input type="text" placeholder="Enter Your E-mail" name="email" required onchange="getstutasemail(document.form1.email)">
         <label><b>Password</b><input name="password" placeholder="Enter Password" type="password" onChange="onCheck()" required/> </label><br />
  		<label><b>Confirm Password</b><input name="confirm" placeholder="Confirm Password"  type="password" onChange="onCheck()" required/> </label><br />
      <button type="submit" onchange="validateEmail(document.form1.email)">Register</button>
    </div>
  </form>
  <form class="modal-content animate" action="<%=request.getContextPath()%>/login" method="Post">
	<button type="submit">Login</button>
</form>
</div></div>
</body>
</html>