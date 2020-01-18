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
<title>Application Approval</title>
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
	background-color: #6869bd;
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
color:white;
	text-align: center;
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
	margin-top:15px;

	width:150px;
	text-align:center;
}
.myButton:hover {
	background:linear-gradient(to bottom, #468ccf 5%, #63b8ee 100%);
	background-color:#468ccf;
}
.myButton:active {
	position:relative;
	top:1px;
}

.myButton1 {
	box-shadow:inset 0px 1px 0px 0px #caefab;
	background:linear-gradient(to bottom, #77d42a 5%, #5cb811 100%);
	background-color:#77d42a;
	border-radius:6px;
	border:1px solid #268a16;
	display:inline-block;
	cursor:pointer;
	color:#306108;
	font-family:Arial;
	font-size:15px;
	font-weight:bold;
	padding:6px 24px;
	text-decoration:none;
	text-shadow:0px 1px 0px #aade7c;
		margin-top:15px;

	text-align:center;
	width:150px;
}
.myButton1:hover {
	background:linear-gradient(to bottom, #5cb811 5%, #77d42a 100%);
	background-color:#5cb811;
}
.myButton1:active {
	position:relative;
	top:1px;
}

.mogol{
text-align:center;
}
</style>
</head>
<body>

	<h2>Departments</h2>
	<table id="students">
		<thead>
			<tr>
				<th><strong>Department ID</strong></th>
				<th><strong>Name</strong></th>
				<th><strong>Location</strong></th>
				<th><strong>Total Students</strong></th>				
				<th><strong>Beneficiaries</strong></th>
			</tr>
		</thead>
		<tbody>
			
				<c:forEach items="${departments}" var="dep" varStatus="status">
					<tr>
					
						<td>${dep.id}</td>
						<td>${dep.name}</td>
						<td>${dep.location}</td>
						<td>${dep.totalStudents}</td>
						<td>${dep.beneficiaries}</td>
					</tr>
				</c:forEach>
			
			
		</tbody>
	</table>
	<div class="mogol">
		<a class="myButton1"
							href="<c:url value='/department/updateBeneficiariesOfDepartments' />">UPDATE</a>
							<br>
	<a class="myButton" href="<c:url value="/" />">GO BACK</a>
</div>
</body>
</html>