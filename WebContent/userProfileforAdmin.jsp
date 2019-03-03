<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Another User's Profile</title>

 <style>
   #disabled {background-color:grey;}
 </style>

</head>
<body>
<p style="text-align:left;">
<a id="home" href="/UTAParking/UserProfileEditforAdminController?action=goHome">Back to Homepage</a>
<span style="float:right;">
<a id="logout" href="/UTAParking/LogoutController?action=logout">Logout</a>
</span>
</p>
<h2>User Profile : <c:out value="${User.username}"/></h2>
<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:300px" disabled="disabled">
<input name="successMsg"  value="<c:out value='${successMsg}'/>" type="text"  style ="background-color: white; color: blue; border: none; width:300px" disabled="disabled">
<table>
  <tr>
   <td>
    <form name="editUserProfileForm" action="UserProfileEditforAdminController?action=editUserProfile" method="post">
    <table style="width: 1200px; ">
    <tr >
    <td > First Name (*): </td>
    <td> <input name="firstName" value="<c:out value='${User.firstName}'/>" type="text" maxlength="50"> </td>
  	<td style="width: 375px; "> <input name="firstNameError"  value="<c:out value='${errorMsgs.firstNameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 300px"  disabled="disabled" maxlength="60"> </td>
    </tr>

    <tr>
    <td> Last Name (*): </td>
    <td> <input name="lastName" value="<c:out value='${User.lastName}'/>" type="text" maxlength="50"> </td>
  	<td style="width: 392px; "> <input name="lastNameError"  value="<c:out value='${errorMsgs.lastNameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 300px"  disabled="disabled" maxlength="60"> </td>
    </tr>

    <tr>
    <td> UTA ID (*): </td>
    <td> <input name="utaId" value="<c:out value='${User.utaId}'/>" type="text" maxlength="10">  </td>
  	<td> <input name="utaIdError"  value="<c:out value='${errorMsgs.utaIdError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 300px"   disabled="disabled" maxlength="60"> </td>
    </tr>

    <tr>
    <td> Username (*): </td>

    <td> <input id=disabled name="username" value="<c:out value='${User.username}'/>" type="text" maxlength= "45"></td>
    </tr>
    <tr>
        <td> Password (*): </td>
    <td> <input name="password" type="password" value="<c:out value='${User.password}'/>" type="text" maxlength="16">  </td>
  	<td> <input name="passwordError"  value="<c:out value='${errorMsgs.passwordError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 300px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Email (*): </td>
    <td> <input name="email" value="<c:out value='${User.email}'/>" type="text" maxlength="45">  </td>
  	<td style="width: 392px; "> <input name="emailError"  value="<c:out value='${errorMsgs.emailError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 300px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    
    <tr>
    <td> Role: </td>
    <td> <input id=role name="roleValue" value="<c:out value='${User.role}'/>" type="text" maxlength="10" readonly>  </td>
    </tr>
    <tr>
    <td></td>
    <td> 
          <input type="radio" id="user" name="role" value="User" />
          <label for="user">User</label> 
          
          <input type="radio" id="manager" name="role" value="Manager"/>
          <label for="manager">Manager</label> 
            
     </td>
     <td> <input name="roleError"  value="<c:out value='${errorMsgs.roleError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 300px"   disabled="disabled" maxlength="60"> </td>
    
    </tr>
    <tr>
    <td> Phone: </td>
    <td> <input id=p name="phone" value="<c:out value='${User.phone}'/>" type="text" maxlength="10" readonly>  </td>
    </tr>
    <tr>
    <td> State: </td>
    <td> <input id=s name="state" value="<c:out value='${User.state}'/>" type="text" maxlength="20" readonly>  </td>
    </tr>
    <tr>
    <td> Address: </td>
    <td> <input id=a name="address" value="<c:out value='${User.address}'/>" type="text" maxlength="50" readonly>  </td>
    </tr>
    <tr>
    <td> Zipcode: </td>
    <td> <input id=z name="zipcode" value="<c:out value='${User.zipcode}'/>" type="text" maxlength="5" readonly>  </td>
    </tr>
    <tr>
    <td> Parking Permit Type: </td>
    <td> <input id=p name="parkingpermitype" value="<c:out value='${User.parkingpermitype}'/>" type="text" maxlength="10" readonly>  </td>
    </tr>
    <tr>
    <td> City: </td>
    <td> <input id=c name="city" value="<c:out value='${User.city}'/>" type="text" maxlength="10" readonly>  </td>
    </tr>
    <tr>
    <td> Violation Status: </td>
    <td> <input id=disabled name="violation" value="<c:out value='${User.violationStatus}'/>" type="text" maxlength="10" readonly>  </td>
    </tr>
        <tr>
    <td> No show Status: </td>
    <td> <input id=disabled name="noShow" value="<c:out value='${User.noShowStatus}'/>" type="text" maxlength="10" readonly>  </td>
    </tr>
        <tr>
    <td> Status: </td>
    <td> <input id=c name="status" value="<c:out value='${User.status}'/>" type="text" maxlength="10" readonly>  </td>
    <td  style="width: 392px; "> <input name="statusError"  value="<c:out value='${errorMsgs.statusError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    <tr>
   		<td><input type ="submit" value="Revoke" name="revokeUser"></td>
   		<td><input type="submit" value ="Activate" name="activateUser"></td>
   		<td><input type="submit" value ="Change User Role" name="changeUserRole"></td>
   		
   	</tr>	
   </table>
   </form>
   
   
</table>
</body>
</html>