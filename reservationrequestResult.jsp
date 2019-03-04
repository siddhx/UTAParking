<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reservation Request Result</title>
</head>
<body>
<p style="text-align:left;">
<a id="home" href="/UTAParking/userHome.jsp?action=goHome">Back to Homepage</a>
<span style="float:right;">
<a id="logout" href="/UTAParking/LogoutController?action=logout">Logout</a>
</span>
</p>
<h2>Available reservations :</h2>

<table>

	<tr>
	<th>Parking Area </th>
	<th>Parking Type</th>
	<th>Cart</th>
	<th>Floor</th>
	<th>Capacity</th>
	</tr>
		<c:forEach items="${parkingList}" var="parkingList">
		<tr>
            
            <td><c:out value="${parkingList.parkingAreaName}"/> </td>
            <td><c:out value="${parkingList.parkingType}" /></td>
            <td><c:out value="${parkingList.cart}" /></td>
            <td><c:out value="${parkingList.floor}" /></td>
             <td><c:out value="${parkingList.capacity}" /></td>
        </tr>
    </c:forEach>
		
</table>

</body>
</html>