<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>BORAJI.COM</title>
</head>
<body>
	<h1>Spring MVC 5 + Spring Security 5 + Hibernate 5 example</h1>
	<h2>${welcomemessage}</h2>
	<h2>${roles}</h2>

	 <a href="<c:url value="/logout" />">Logout</a>
</body>


</html>