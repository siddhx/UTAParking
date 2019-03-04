<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Request Reservation</title>
</head>
<body>
<p style="text-align:left;">
<a id="home" href="/arlington_parking_app/RequestController?action=goHome">Back to Homepage</a>
<span style="float:right;">
<a id="logout" href="/arlington_parking_app/LogoutController?action=logout">Logout</a>
</span>
</p>
<h2>Request Reservation</h2>
<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<table>
  <tr>
   <td>
    <form name="requestparking" action="RequestReservationController?action=requestParking" method="post">
    <table style="width: 1200px; ">
    <tr>
    <td> Parking Permit Type: </td>
    <td> <input id=p name="parkingpermitype" value="<c:out value='${User.parkingpermitype}'/>" type="text" maxlength="10" readonly>  </td>
    </tr>
    <tr>
    <td> Check-Out Date-Time (*): </td>
    <td> <input placeholder="yyyy-MM-dd HH:mm" name="startTime" value="<c:out value='${Reservation.startTimeAsString}'/>" type="text" maxlength="16">  </td>
  	<td> <input name="startTimeError"  value="<c:out value='${errorMsgs.startTimeError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Return Date-Time (*): </td>
    <td> <input placeholder="yyyy-MM-dd HH:mm" name="endTime" value="<c:out value='${Reservation.endTimeAsString}'/>" type="text" maxlength="16">  </td>
  	<td> <input name="endTimeError"  value="<c:out value='${errorMsgs.endTimeError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    
    </table>
    <input name="searchBtn" type="submit" value="Search"> 
    <input name="clearBtn" type="submit" value="Clear">
    </form>
</td>
</tr>
</table>
</body>
</html>