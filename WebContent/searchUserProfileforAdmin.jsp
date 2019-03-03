<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Another User's Profile</title>
</head>
<body>
<p style="text-align:left;">
<a id="home" href="/UTAParking/UserProfileEditforAdminController?action=goHome">Back to Homepage</a>
<span style="float:right;">
<a id="logout" href="/UTAParking/LogoutController?action=logout">Logout</a>
</span>
</p>

<h2>Find User Profile To Edit</h2>
<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<table>
  <tr>
   <td>
    <form name="searchUserProfileForm" action="UserProfileEditforAdminController?action=displayUserProfile" method="post">
    <table style="width: 1200px; ">

    <tr>
    <td> Username (*): </td>
    <td> <input name="username" value="<c:out value='${User.username}'/>" type="text" maxlength="16">  </td>
  	<td> <input name="usernameError"  value="<c:out value='${errorMsgs.usernameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    </table>
    <input name="searchBtn" type="submit" value="Search">
    </form>
</td>
</tr>
</table>

</body>
</html>