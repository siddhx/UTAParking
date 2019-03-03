package UTAParking.model;
import UTAParking.data.UserDAO;


import java.io.Serializable;

import UTAParking.data.UserDAO;

public class User implements Serializable {	
	private static final long serialVersionUID = 2L;	
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private String utaId;
	private String role;
	private String zipcode;
	private String phone;
	private String state;
	private String address;
	private String parkingpermitype;
	private String city;
	private String noShowStatus;
	private String violationStatus;
	private String status;

	
	public String getNoShowStatus() {
		return noShowStatus;
	}
	public void setNoShowStatus(String noShowStatus) {
		this.noShowStatus = noShowStatus;
	}
	public String getViolationStatus() {
		return violationStatus;
	}
	public void setViolationStatus(String violationStatus) {
		this.violationStatus = violationStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getId() { return id; }		
	public void setId(int id) { this.id=id; }	 
	
	public String getUtaId() { return utaId; }	
	public void setUtaId(String utaId) { this.utaId=utaId; }
	
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username=username; }
	
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName=firstName; }
	
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName=lastName; }
	
	public String getPassword() { return password; }
	public void setPassword(String password){ this.password=password; }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email=email; }
	
		
	
	public String getRole() { return role; }
	public void setRole(String role) { this.role=role; }
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getParkingpermitype() {
		return parkingpermitype;
	}
	public void setParkingpermitype(String parkingpermitype) {
		this.parkingpermitype = parkingpermitype;
	}	

	
	
	
	/************ VALIDATIONS *************/	
	public void validateUser (User user, UserErrorMsgs errorMsgs, String action) {	
		switch(action) {
			case "register": 
				errorMsgs.setUsernameError(validateUsername(user.getUsername()));
			case "editProfile":
			case "editUserProfile": 
				errorMsgs.setFirstNameError(validateFirstName(user.getFirstName()));
				errorMsgs.setLastNameError(validateLastName(user.getLastName()));		
				errorMsgs.setPasswordError(validatePassword(user.getPassword()));
				errorMsgs.setEmailError(validateEmail(user.getEmail()));
				errorMsgs.setUtaIdError(validateUtaId(user.getUtaId()));
				errorMsgs.setAddressError(validateAddress(user.getAddress()));
				errorMsgs.setCityError(validateCity(user.getCity()));
				//errorMsgs.setParkingpermitypeError(validateParkingpermitype(user.getParkingpermitype()));
				errorMsgs.setStateError(validateState(user.getCity()));
				errorMsgs.setZipcodeError(validateZipcode(user.getZipcode()));
				errorMsgs.setPhoneError(validatePhone(user.getPhone()));
				errorMsgs.setErrorMsg();
			
				break;		
				
		}
	}
	public void validateRole(User user,UserErrorMsgs errorMsgs)
	{
		
	}
	public void validateRole(User user,UserErrorMsgs errorMsgs,String role,String username)
	{
		String result="";
		System.out.println("inside validate role function");
		if(UserDAO.getRole(username).equals(role))
				{
					System.out.println("inside if");
					 result="Cannot change to same user role";
				}
		else
			result="";
		
		errorMsgs.setRoleError(result);
		//return result;
	}
	private String validatePhone(String phone) {
		String result="";
		phone = phone.trim(); //trim out trailing and leading spaces		
		if (phone.length()!=10)
			result= "Your phone number must be 10 digits";
		else
			if (!phone.matches("^[0-9]*$"))
				result="Your phone number should only have digits";	
		return result;
	}
	private String validateZipcode(String zipcode) {
		String result="";
		zipcode = zipcode.trim(); //trim out trailing and leading spaces		
		if (zipcode.length()!=5)
			result= "Your zipcode must be 5 characters";
		else
			if (!zipcode.matches("^[0-9]*$"))
				result="Your zipcode must be a number";		
		return result;
	}
	private String validateCity(String city) {
		String result="";
		city = city.trim(); //trim out trailing and leading spaces		
		if (!stringSize(city,3,20))
			result= "Your city must between 3 and 20 characters";
		else
			if (!city.matches("^[a-zA-Z]+$"))
				result="Your city must only contain alphabets";		
		return result;
	}
	private String validateState(String state) {
		String result="";
		state = state.trim(); //trim out trailing and leading spaces		
		if (!stringSize(state,3,20))
			result= "Your state must between 3 and 20 characters";
		else
			if (!state.matches("^[a-zA-Z]+$"))
				result="Your state must only contain alphabets";		
		return result;
	
	}
	/**
	 * This function checks whether the parking permit type of
	 * a user matches the entered permit type for his parking
	 * spot search.
	 */
	public  String verifyParkingpermitype(User user,UserErrorMsgs errorMsgs,String parkingpermitype) {
		String result="";	
		String permitTypeinDB="";
		if (!parkingpermitype.equalsIgnoreCase("access")  && !parkingpermitype.equalsIgnoreCase("basic") &&
				!parkingpermitype.equalsIgnoreCase("midrange")&& !parkingpermitype.equalsIgnoreCase("premium"))
			result= "Permit Type does not exist";
		else
		{
			
			permitTypeinDB=UserDAO.getParkingPermitType(username);
			if (!permitTypeinDB.equalsIgnoreCase(parkingpermitype))
			{
				if(permitTypeinDB.equalsIgnoreCase("basic") && (parkingpermitype.equalsIgnoreCase("midrange") || 
						parkingpermitype.equalsIgnoreCase("premium") ||	parkingpermitype.equalsIgnoreCase("access"))){
					result = "You do not have access to Midrange,Premium and Access Parking Permits";
				}
				else if(permitTypeinDB.equalsIgnoreCase("midrange") && (parkingpermitype.equalsIgnoreCase("premium")
						||	parkingpermitype.equalsIgnoreCase("access"))){
					result = " You do not have access to Premium & Access Parking Permits";
				}
				else if(permitTypeinDB.equalsIgnoreCase("premium") && parkingpermitype.equalsIgnoreCase("access")){
					result = "You do not have access to 'Access' Parking Permits";
				}
				else if(permitTypeinDB.equalsIgnoreCase("access") && (!parkingpermitype.equalsIgnoreCase("access"))){
					result = " You do no have access to Premium, Midrange or Basic Parking Permits";
				}	
			}
		}
		
		return result;		
				
				
			
		
	} 
	private String validateAddress(String address) {
		String result="";
		address = address.trim(); //trim out trailing and leading spaces		
		if (!stringSize(address,4,50))
			result= "Your address must between 4 and 50 characters";
		else
			result = result;	
		return result;
	}
	private String validateFirstName (String firstName) {
		String result="";
		firstName = firstName.trim(); //trim out trailing and leading spaces		
		if (!stringSize(firstName,2,50))
			result= "Your First Name must between 2 and 50 characters";
		else
			if (!firstName.matches("^[a-zA-Z]+$"))
				result="Your First Name must only contain alphabets";		
		return result;
	}	
	private String validateLastName (String lastName) {
		String result="";
		lastName = lastName.trim(); //trim out trailing and leading spaces		
		if (!stringSize(lastName,3,50))
			result= "Your Last Name must between 3 and 50 characters";
		else
			if (!lastName.matches("^[a-zA-Z]+$"))
				result="Your Last Name must only contain alphabets";	
		return result;
	}
	private String validateUsername(String username) {
		username = username.trim();
		String result="";
		if (!stringSize(username,3,15))
			result= "Your username must between 3 and 15 characters";
		else
			if (!username.matches("^[a-zA-Z0-9]+$"))
				result="Your username must only contain alphabets and numbers";
			else
				if (!UserDAO.uniqueUsername(username))
					result="Username already exists in database";
		return result;				
	}	
	private String validatePassword(String password) {
		password = password.trim();
		String result="";
		if (!stringSize(password,8,15))
			result= "Your password must between 8 and 15 characters";
		else 
			if(!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@._#$%^&+=]).{8,}$"))
				result = "Your password must contain at least 1 upper case letter & at least 1 lower case letter & at least 1 number & at least 1 special character(@#$%^&+=._)";
		return result;
	}	
	private String validateEmail(String email) {
		email = email.trim();
		String result="";
		if (!stringSize(email,8,45))
			result= "Your email must between 8 and 45 characters";
		else 
		   if(!email.matches("^[a-zA-Z0-9]+(\\.[_A-Za-z0-9-])*+@[a-zA-Z0-9]+(.com|.gov|.net|.org|.edu|.mil)$"))
			 result= "Your email must contain @ and any one of the following extensions {.com .gov .net .org .edu .mil}"; 		  
		return result;
	}	
	private String validateUtaId(String utaId) {
		utaId = utaId.trim();
		String result="";
		if (utaId.length()!=10)
			result= "Your UTA ID must be exactly 10 digits long";
		else
			if (!utaId.matches("^[0-9]*$"))
				result="Your UTA ID must be a number";
		return result;
	}	

	/************* VERIFY (FOR LOGIN & ADMIN USERNAME SEARCH) ****************/
    public void verifyUsername (User user, UserErrorMsgs errorMsgs) {	
		errorMsgs.setUsernameError(validateUsernameForLogin(user.getUsername()));
		errorMsgs.setErrorMsg();
	}    
    public void verifyPassword (User user, UserErrorMsgs errorMsgs) {	
		errorMsgs.setPasswordError(validatePasswordForLogin(user.getPassword()));
	} 	
	private String validateUsernameForLogin(String username) {
		String result="";
		username = username.trim();
		if (username.isEmpty())
			result = "Username cannot be blank";
		else
			if (UserDAO.uniqueUsername(username))
				result="This username is not registered yet";
		return result;				
	}
	private String validatePasswordForLogin(String password) {
		String result="";
		if (password.isEmpty())
			result = "Password cannot be blank";
		else
			if (!UserDAO.uniqueUsername(username))//if user exists, get password from DB & check for match
				if (! (UserDAO.getUser(username).getPassword().equals(password)) )
					result="Incorrect password";
		return result;	
	}
	
	/*
	 * Function to check status
	 */
	
	public void verifyStatusifRevoke (User user, UserErrorMsgs errorMsgs) {	
		System.out.println("inside verify"); //debug
		errorMsgs.setStatusError(validateStatusifRevoke(user.getUsername()));
	}
	
	public void verifyStatusifActive (User user, UserErrorMsgs errorMsgs) {	
		System.out.println("inside verify"); //debug
		errorMsgs.setStatusError(validateStatusifActive(user.getUsername()));
	}
	
	public void verifyStatuswithoutViolation (User user, UserErrorMsgs errorMsgs) {	
		System.out.println("inside verify"); //debug
		errorMsgs.setStatusError(validateStatuswithouViolation(user.getUsername()));
	}
	private String validateStatusifActive(String username)
	{
		String result="";
		String currentStatus="";
		System.out.println("inside validate");   //debug
		if(UserDAO.getStatus(username).equals("1"))
			result="User already active";
		else{
			currentStatus="0";
			if(UserDAO.changeStatus(username,currentStatus).equals("0"))
			{
				result="User has been activated";
			}
		}
		return result;
	}
	
	private String validateStatusifRevoke(String username)
	{
		String result="";
		String currentStatus="";
		
		if(UserDAO.getStatus(username).equals("0"))
			result="User already revoked";
		else{
			currentStatus="1";
			if(UserDAO.changeStatus(username,currentStatus).equals("0"))
			{
				result="User has been revoked";
			}
		}
		return result;
	}
	
	public String validateStatuswithouViolation(String username)
	{
		
		if(UserDAO.getViolation(username).equals("1"))
		{
			return "Violated";
		}
		else
			return "No Violation";
	}
	
	
	/************* AUXILIARY FUNCTIONS *************/
	private boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max;
	}
	private boolean isTextAnInteger (String string) {
        boolean result;
		try {
            Long.parseLong(string);
            result=true;
        } 
        catch (NumberFormatException e) {
            result=false;
        }
		return result;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}