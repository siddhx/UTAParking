<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Home Page</title>
</head>
<body>
<div class="main">
  <div class="header">
    <div class="header_resize">

<!-- TO MAKE THE URL REFERENCES WORK YOU MUST HAVE SESSION ID DISABLED IN URL - SEE WEB.XML -->

      <div class="logo"><h1><a href="<c:url value='/' />">Parking Manager Home Page</a></h1></div>
  <div class="content">  

      <div class="menu_nav">
        <ul>
          <li><a href="formUpdateProfile.jsp"  target="_top"><span>Update Profile</span></a></li>
          
          <li><a href="formSearchUser.jsp"  target="_top"><span>Search User</span></a></li>  
           <li><a href="formAvailableSpace.jsp"  target="_top"><span>View Available Spots</span></a></li>
          <li><a href="formSearchParkingSpot.jsp"  target="_top"><span>Search Parking Spot</span></a></li>  
          <li><a href="formReservation.jsp"  target="_top"><span>View Reservations</span></a></li>  
           <li><a href="addNewParkingArea.jsp"  target="_top"><span>Add New Parking Area</span></a></li>
           <li><a href="modifyParkingArea.jsp"  target="_top"><span>Modify Parking Area</span></a></li>
          </ul>
      </div>
    </div>
  </div>
  </div>
  </div>  
</body>
</html>