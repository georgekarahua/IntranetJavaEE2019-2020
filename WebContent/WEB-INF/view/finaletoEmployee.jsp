<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Menu</title>
<style>
body{
background: black;
}
a:link, a:visited {
  background-color: #f44336;
  color: white;
  padding: 14px 25px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
}

a:hover, a:active {
  background-color: cyan;
}

h2{
color:grey;
}
</style>
</head>
<body>
	<h2>${welcomemessage}</h2>
	<h2>${roles}</h2>
	<div>
  <a style="margin: 0 auto; display:block; text-align: center" href="<c:url value="/student/lactivate" />">Activate Students</a>
  </div>
  <br><br><br>
  <div>
  <a style="margin: 0 auto; display:block; text-align: center" href="<c:url value="/student/lapprove" />">Approve Applications</a>
  </div>
  <br><br><br>
  <div>
  <a style="margin: 0 auto; display:block; text-align: center" href="<c:url value="/logout" />">Logout</a>
  </div>
</body>
</html>