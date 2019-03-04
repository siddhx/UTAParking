<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Reservation Details</title>
</head>
<body>
<p style="text-align:left;">
<a id="home" href="/car_rental_app/ManagerReservationController?action=goHome">Back to Homepage</a>
<span style="float:right;">
<a id="logout" href="/car_rental_app/LogoutController?action=logout">Logout</a>
</span>
</p>
<h2>Reservation Details</h2>
<table>
  <tr>
   <td>
    <form name="viewReservationDetails" action="ManagerReservationController?action=viewReservations" method="post">
    <table border="1" style="width: 600px; ">
    <tr>
    <td> Renter Username: </td>
    <td><c:out value='${currentReservation.username}'/></td>
    </tr>

    <tr>
    <td> Car Name: </td>
    <td><c:out value='${currentReservation.carName}'/></td>
    </tr>

    <tr>
    <td> Car Capacity: </td>
    <td><c:out value='${currentReservation.capacity}'/></td>
    </tr>
    
    <tr>
    <td> Check-Out Date & Time: </td>
    <td><c:out value='${currentReservation.startTime}'/></td>
    </tr>
    
    <tr>
    <td> Return Date & Time: </td>
    <td><c:out value='${currentReservation.endTime}'/></td>
    </tr>
    
    <tr>
    <td> Total Rental Price: </td>
    <td>$<c:out value='${currentReservation.totalPrice}'/></td>
    </tr>
    
    <tr>
    <td> Additional Features: </td>
    <td><c:out value='${currentReservation.additionalFeatures}'/></td>
    </tr>
    
    <tr>
    <td> Rental Confirmation Number: </td>
    <td><c:out value='${currentReservation.id}'/></td>
    </tr>


    </table>
    <input name="backBtn" type="submit" value="Go Back">
    </form>
</td>
</tr>
</table>
</body>
</html>