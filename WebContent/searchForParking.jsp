<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search For Parking Spot</title>
</head>
<body>
<p style="text-align:left;">
<a id="home" href="/UTAParking/SearhForParkingSpotController?action=goHome">Back to Homepage</a>
<span style="float:right;">
<a id="logout" href="/UTAParking/LogoutController?action=logout">Logout</a>
</span>
</p>
<h2>Search For Parking Spot</h2>
<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<table>
  <tr>
   <td>
    <form name="SearchPermit" action="SearhForParkingSpotController?action=SearchPermit" method="post">
    <table style="width: 1200px; ">
    <tr>
    <td> Parking Permit Type: </td>
    <td> <select name=permittype>
         <option>Basic</option>
         <option>Midrange</option>
         <option>Premium</option>
         <option>Access</option>
         </select>
    <td> <input id=permitError name="permiTypeError" value="<c:out value='${errorMsgs.permitTypeError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60">  </td>
    </tr>
    
   </table>
    <input name="searchBtn" type="submit" value="Search"> 
    
    </form>
</td>
</tr>
</table>
</body>
</html>