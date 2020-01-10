<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Students Activation</title>
<style type="text/css">
.error {
	color: red;
}

#students {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#students td, #customers th {
	border: 1px solid #ddd;
	padding: 8px;
}

#students tr:nth-child(even) {
	background-color: #f2f2f2;
}

#students tr:hover {
	background-color: #ddd;
}

#students th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: center;
	background-color: #4CAF50;
	color: white;
}

#students td:hover {
	cursor: pointer;
}

.selected {
	background-color: brown;
	color: #FFF;
}

body {
	background: grey;
}

h3 {
	color: white;
}

h2 {
	text-align: center;
}
</style>
</head>
<body>

	<h2>Student List To Activate</h2>
	<table id="students">
		<thead>
			<tr>
				<th><strong>ID</strong></th>
				<th><strong>First Name</strong></th>
				<th><strong>Last Name</strong></th>
				<th><strong>Department</strong></th>
				<th><strong>Click to Activate</strong>
			</tr>
		</thead>
		<tbody>
			<tr>
				<!-- TODO: Loipon, auto to varStatus="status" einai kati typopoihmeno twn jsp to opoio mas epitrepei na paroume to index tou pinaka -->
				<c:forEach items="${students}" var="user" varStatus="status">
					<tr>
						<td class="studentIdbae">${user.id}</td>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td>${user.department.name}</td>
						<td>${students[status.index].id}</td>
						<td>
							<!-- TODO: href apo katw to <[status.index]> einai to index tou pinaka pou kaneis forEach -->
							<a
							href="<c:url value='/student/activateStudent?stud_id=${students[status.index].id}' />">Activate</a>
						</td>
					</tr>
				</c:forEach>
			</tr>
		</tbody>
	</table>
	<button id="elapame" type="button">ACTIVATE</button>
	<button id="pamepisw" type="button">GO BACK</button>

</body>
</html>