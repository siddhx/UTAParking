<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View All Reservations</title>
</head>
<body>
<p style="text-align:left;">
<a id="home" href="/car_rental_app/ManagerReservationController?action=goHome">Back to Homepage</a>
<span style="float:right;">
<a id="logout" href="/car_rental_app/LogoutController?action=logout">Logout</a>
</span>
</p>
<h2>All Reservations</h2>
<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<table>
  <tr>
   <td>
    <form name="managerViewRRForm" action="ManagerReservationController?action=viewReservations" method="post">
    <table style="width: 1200px; ">

    <tr>
    <td> Start Date-Time (*): </td>
    <td> <input placeholder="yyyy-MM-dd HH:mm" name="startTime" value="<c:out value='${Reservation.startTimeAsString}'/>" type="text" maxlength="30">  </td>
  	<td> <input name="startTimeError"  value="<c:out value='${errorMsgs.startTimeError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> End Date-Time (*): </td>
    <td> <input placeholder="yyyy-MM-dd HH:mm" name="endTime" value="<c:out value='${Reservation.endTimeAsString}'/>" type="text" maxlength="30">  </td>
  	<td> <input name="endTimeError"  value="<c:out value='${errorMsgs.endTimeError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    </table>
    <input name="filterBtn2" type="submit" value="Filter"> 
    <input name="clearBtn2" type="submit" value="Clear">
    </form>
</td>
</tr>
</table>

<p><i>*List sorted based on reservation check-out date (newest to oldest)</i></p>

<table class="myReservationTable" border="1"> 
			<tr class="myTableRow"> 
				<th align="left" class="myTableHead" style="width: 90px; ">Renter</th> 
				<th align="left" class="myTableHead" style="width: 90px; ">Car</th>
				<th align="left" class="myTableHead" style="width: 150px; ">Check-Out By</th> 
				<th align="left" class="myTableHead" style="width: 150px; ">Return By</th> 
				<th align="left" class="myTableHead" style="width: 80px; ">Total Price</th>
			</tr>

 		<c:forEach items="${RESERVATIONS}" var="item">
			<tr class="myTableRow">
			<td class="myTableCell" style="width: 90px; "><c:out value="${item.username}" /></td>
			<td align="center" class="myTableCell" style="width: 90px; "><c:out value="${item.carName}" /></td>
			<td class="myTableCell" style="width: 150px; "><c:out value="${item.startTime}" /></td>
			<td class="myTableCell" style="width: 150px; "><c:out value="${item.endTime}" /></td>
			<td class="myTableCell" style="width: 80px; ">$<c:out value="${item.totalPrice}" /></td>
            <td> <!-- Actions for the individual item -->
              <a href="/car_rental_app/ManagerReservationController?action=viewDetails&id=${item.id}">View</a>
            </td>
            <td>  
              <a href="/car_rental_app/ManagerReservationController?action=deleteReservation&id=${item.id}">Delete</a>
            </td>
		</c:forEach>
 </table>


</body>
</html>