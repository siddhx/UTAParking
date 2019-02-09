<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
<tr>
<td>
<form name="greetingForm" action="registerController" method="post" style="width: 300px; ">
<!-- <table> -->
<tr>
<td>Please enter your name</td>
</tr>
<tr>
	<td><input name="name" value="<c:out value='${nameForm.name}'/>" ></td>
</tr>
<tr>
<td>Please enter your Student ID</td>
</tr>
<tr>
	<td><input name="studentID" value="<c:out value='${nameForm.studentID}'/>" ></td>
	<td><input type="submit" value="Submit"></td>
</tr>
</table>
</form>
</body>
</html>