<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Form</title>
</head>
<body>
<a href="/arlington_parking_app/RegistrationController?action=goHome">Back to Homepage</a>
<h2>Registration Form</h2>
<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<table>
  <tr>
   <td>
    <form name="registrationForm" action="RegistrationController?action=register" method="post">
    <table style="width: 1200px; ">
    <tr>
    <td> First Name (*): </td>
    <td> <input name="firstName" value="<c:out value='${User.firstName}'/>" type="text" maxlength="50"> </td>
  	<td> <input name="firstNameError"  value="<c:out value='${errorMsgs.firstNameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
    </tr>

    <tr>
    <td> Last Name (*): </td>
    <td> <input name="lastName" value="<c:out value='${User.lastName}'/>" type="text" maxlength="50"> </td>
  	<td> <input name="lastNameError"  value="<c:out value='${errorMsgs.lastNameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
    </tr>

    <tr>
    <td> UTA ID (*): </td>
    <td> <input name="utaId" value="<c:out value='${User.utaId}'/>" type="text" maxlength="10">  </td>
  	<td> <input name="utaIdError"  value="<c:out value='${errorMsgs.utaIdError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

    <tr>
    <td> Username (*): </td>
    <td> <input name="username" value="<c:out value='${User.username}'/>" type="text" maxlength="16">  </td>
  	<td> <input name="usernameError"  value="<c:out value='${errorMsgs.usernameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Password (*): </td>
    <td> <input name="password" type="password" value="<c:out value='${User.password}'/>" type="text" maxlength="16">  </td>
  	<td> <input name="passwordError"  value="<c:out value='${errorMsgs.passwordError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Email (*): </td>
    <td> <input name="email" value="<c:out value='${User.email}'/>" type="text" maxlength="45">  </td>
  	<td> <input name="emailError"  value="<c:out value='${errorMsgs.emailError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Phone (*): </td>
    <td> <input name="phone" value="<c:out value='${User.phone}'/>" type="text" maxlength="3">  </td>
  	<td> <input name="phoneError"  value="<c:out value='${errorMsgs.phoneError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Role (*): </td>
     <td> 
          <input type="radio" id="user" name="role" value="User" checked/>
          <label for="user">User</label> 
          
          <input type="radio" id="manager" name="role" value="Manager"/>
          <label for="manager">Manager</label> 
          
          <input type="radio" id="admin" name="role" value="Admin"/>
          <label for="admin">Admin</label>      
     </td>
    </tr>
    <tr>
    <td> State (*): </td>
    <td> <input name="state" value="<c:out value='${User.state}'/>" type="text" maxlength="3">  </td>
  	<td> <input name="stateError"  value="<c:out value='${errorMsgs.stateError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    <tr>
    <td> Address (*): </td>
    <td> <input name="address" value="<c:out value='${User.address}'/>" type="text" maxlength="3">  </td>
  	<td> <input name="addressError"  value="<c:out value='${errorMsgs.addressError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    <tr>
    <td> Zipcode (*): </td>
    <td> <input name="zipcode" value="<c:out value='${User.zipcode}'/>" type="text" maxlength="3">  </td>
  	<td> <input name="zipcodeError"  value="<c:out value='${errorMsgs.zipcodeError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
     <tr>
    <td>Parking Permit Type (*): </td>
    <td> <input type="radio" id="b" name="parkingpermitype" value="Basic" checked/>
          <label for="Basic">Basic</label>
          
          <input type="radio" id="m" name="parkingpermitype" value="Midrange"/>
          <label for="Midrange">Midrange</label> 
          
          <input type="radio" id="p" name="parkingpermitype" value="Premium"/>
          <label for="Premium">Premium</label>
          
          <input type="radio" id="a" name="parkingpermitype" value="Access"/>
          <label for="Access">Access</label>
    </td>
    </tr>
    <tr>
    <td> City (*): </td>
    <td> <input name="city" value="<c:out value='${User.city}'/>" type="text" maxlength="3">  </td>
  	<td> <input name="cityError"  value="<c:out value='${errorMsgs.cityError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    <tr>
    <td colspan="2"><i>(*) Mandatory field</i></td>
    </tr>
    </table>
    <input id="registerButton" type="submit" value="Register">
    </form>
</td>
</tr>
</table>
</body>
</html>