<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <!-- FontAwesome 5 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
    <style type="text/css">
    /* Import Font Dancing Script */
@import url(https://fonts.googleapis.com/css?family=Dancing+Script);

* {
    margin: 0;
}

body {
    background-color: #e8f5ff;
    font-family: Arial;
    overflow: hidden;
}

/* Set a style for all buttons */
button {
  background-color: #04AA6D;
  color: white;
  padding: 14px 20px;
  margin: 15px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

.main {
    margin-top: 2%;
    margin-left: 20%;
    font-size: 28px;
    padding: 0 10px;
    width: 50%;
}

.main h2 {
    color: #333;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    font-size: 24px;
    margin-bottom: 10px;
}

.main .card {
    background-color: #fff;
    border-radius: 18px;
    box-shadow: 1px 1px 8px 0 grey;
    height: auto;
    margin-bottom: 20px;
    padding: 20px 0 20px 50px;
}

.main .card table {
    border: none;
    font-size: 16px;
    height: 270px;
    width: 50%;
}
    </style>
</head>
<body>
  <%
if(request.getSession().getAttribute("username") == null){
	response.sendRedirect(request.getContextPath()+"/login");
}
%>
    <div class="main">
        <h2>Profile</h2>
        <div class="card">
            <div class="card-body">
                <i class="fa fa-pen fa-xs edit"></i>
                <table>
                    <tbody>
                        <tr>
                            <td>First Name</td>
                            <td>:</td>
                            <td>${data.getFname() }</td>
                        </tr>
                        <tr>
                            <td>Last Name</td>
                            <td>:</td>
                            <td>${data.getLname() }</td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td>:</td>
                            <td>${data.getUname() }</td>
                        </tr>
                        <tr>
                            <td>Mobile No</td>
                            <td>:</td>
                            <td>${data.getMobileNo() }</td>
                        </tr>
                        <tr>
                            <td>District</td>
                            <td>:</td>
                            <td>${data.getDistrict() } </td>
                        </tr>
                        <tr>
                            <td>State</td>
                            <td>:</td>
                            <td>${data.getState() } </td>
                        </tr>
                    </tbody>
                </table>
                
            </div>
        </div>
    </div>
    <form class="modal-content animate" action="<%=request.getContextPath()%>/logout" method="Post">
		<button type="submit">Logout</button>
	</form>
	<form class="modal-content animate" action="<%=request.getContextPath()%>/deletedaccount" method="Post">
		<button type="submit">Delete Account</button>
	</form>
</body>
</html>