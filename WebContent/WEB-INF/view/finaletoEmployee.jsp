<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Menu</title>
<style>
body{
background: #475d62;
}
.myButton {
	box-shadow:inset 0px 1px 0px 0px #bee2f9;
	background:linear-gradient(to bottom, #63b8ee 5%, #468ccf 100%);
	background-color:#63b8ee;
	border-radius:6px;
	border:1px solid #3866a3;
	display:inline-block;
	cursor:pointer;
	color:#14396a;
	font-family:Arial;
	font-size:15px;
	font-weight:bold;
	padding:6px 24px;
	text-decoration:none;
	text-shadow:0px 1px 0px #7cacde;
}
.myButton:hover {
	background:linear-gradient(to bottom, #468ccf 5%, #63b8ee 100%);
	background-color:#468ccf;
}
.myButton:active {
	position:relative;
	top:1px;
}
h2{
color: white;
}
</style>
</head>
<body>
	<h2 style="text-align:center;">${welcomemessage}</h2>
	<h2 style="text-align:center;">${roles}</h2>
	<div style="text-align:center">
  <a class="myButton"  href="<c:url value="/student/lactivate" />">Activate Students</a>
  </div>
  <br><br><br>
  <div style="text-align:center">
  <a  class="myButton"  href="<c:url value="/student/lapprove" />">Approve Applications</a>
  </div>
  <br><br><br>
			
		<sec:authorize access="hasRole('ROLE_CHIEF')">
    <div style="text-align:center">
    <a  class="myButton" href="<c:url value="/department/ledepart" />">Get Departments</a>
  </div>
   </sec:authorize>
  <br><br><br>
  <div style="text-align:center">
  <a  class="myButton"  href="<c:url value="/logout" />">Logout</a>
  </div>
</body>
</html>