<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View My Reservations</title>
</head>
<body>
<p style="text-align:left;">
<a id="home" href="/arlington_parking_app/CustomerReservationController?action=goHome">Back to Homepage</a>
<span style="float:right;">
<a id="logout" href="/arlington_parking_app/LogoutController?action=logout">Logout</a>
</span>
</p>
<h2>My Reservations</h2>
<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<table>
  <tr>
   <td>
    <form name="customerViewRRForm" action="CustomerReservationController?action=viewReservations" method="post">
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
    <input name="filterBtn" type="submit" value="Filter"> 
    <input name="clearBtn" type="submit" value="Clear">
    </form>
</td>
</tr>
</table>

<p><i>*List sorted based on reservation check-out date (newest to oldest)</i></p>

<table class="myReservationTable" border="1"> 
			<tr class="myTableRow">
			    <th align="left" class="myTableHead" style="width: 80px; ">Confirmation Number</th> 
				<th align="left" class="myTableHead" style="width: 80px; ">Parking</th> 
				<th align="left" class="myTableHead" style="width: 50px; ">Capacity</th>
				<th align="left" class="myTableHead" style="width: 150px; ">Check-Out By</th> 
				<th align="left" class="myTableHead" style="width: 150px; ">Options</th>
			    <th align="left" class="myTableHead" style="width: 80px; ">Total Price</th>
			</tr>

 		<c:forEach items="${RESERVATIONS}" var="item">
			<tr class="myTableRow">
			<td align="center" class="myTableCell" style="width: 50px; "><c:out value="${item.id}" /></td>
			<td class="myTableCell" style="width: 50px; "><c:out value="${item.parkingareaName}" /></td>
			<td align="center" class="myTableCell" style="width: 50px; "><c:out value="${item.capacity}" /></td>
			<td class="myTableCell" style="width: 150px; "><c:out value="${item.startTime}" /></td>
			<td class="myTableCell" style="width: 150px; "><c:out value="${item.endTime}" /></td>
			<td align="center" class="myTableCell" style="width: 150px; "><c:out value="${item.options}" /></td>
			<td class="myTableCell" style="width: 80px; ">$<c:out value="${item.totalPrice}" /></td>
            <td> <!-- Actions for the individual item -->
              <a href="/arlington_parking_app/CustomerReservationController?action=cancelReservation&id=${item.id}">Cancel</a>
            </td>
		</c:forEach>
 </table>


</body>
</html>