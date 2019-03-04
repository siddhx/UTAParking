<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pay & Confirm</title>
</head>
<body>
<p style="text-align:left;">
<a id="home" href="/arlington_parking_app/PaymentController?action=goHome">Back to Homepage</a>
<span style="float:right;">
<a id="logout" href="/arlington_parking_app/LogoutController?action=logout">Logout</a>
</span>
</p>
<h2>Pay & Confirm Rental</h2>

<table border="1" style="width: 600px; ">
    <tr>
    <td> Parking Area Name: </td>
    <td><c:out value='${Parking.name}'/></td>
    </tr>

    <tr>
    <td> Parking Capacity: </td>
    <td><c:out value='${Parking.capacity}'/></td>
    </tr>
    
    <tr>
    <td> Check-Out Date & Time: </td>
    <td><c:out value='${Reservation.startTimeAsString}'/></td>
    </tr>
    
    <tr>
    <td> Return Date & Time: </td>
    <td><c:out value='${Reservation.endTimeAsString}'/></td>
    </tr>
     
    <tr>
    <td> Final Price: </td>
    <td>$<c:out value='${Reservation.totalPrice}'/></td>
    </tr> 

    
    </table>

<h3>Payment Information:</h3>
<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<table>
  <tr>
   <td>
    <form name="paymentForm" action="PaymentController?action=submitPayment" method="post">
    <table style="width: 1100px; ">
    <tr>
    <td> Name on Card (*): </td>
    <td> <input name="nameOnCard" value="<c:out value='${Payment.nameOnCard}'/>" type="text" maxlength="50"> </td>
  	<td> <input name="nameOnCardError"  value="<c:out value='${errorMsgs.nameOnCardError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
    </tr>

    <tr>
    <td> Card Number (*): </td>
    <td> <input name="cardNumber" value="<c:out value='${Payment.cardNumber}'/>" type="text" maxlength="16"> </td>
  	<td> <input name="cardNumberError"  value="<c:out value='${errorMsgs.cardNumberError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
    </tr>

    <tr>
    <td> Card Expiration Date (*): </td>
    <td> <input placeholder="yyyy-MM" name="cardExpires" value="<c:out value='${Payment.cardExpiresAsString}'/>" type="text" maxlength="10">  </td>
  	<td> <input name="cardExpiresError" value="<c:out value='${errorMsgs.cardExpiresError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

    <tr>
    <td> CVV Security Code (*): </td>
    <td> <input name="cardSecurityCode" value="<c:out value='${Payment.cardSecurityCode}'/>" type="text" maxlength="3">  </td>
  	<td> <input name="cardSecurityCodeError"  value="<c:out value='${errorMsgs.cardSecurityCodeError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>  
    </table>
    <input name="payBtn" type="submit" value="Pay & Confirm">
    <input name="cancelPayBtn" type="submit" value="Cancel">
    </form>
</td>
</tr>
</table>
</body>
</html>