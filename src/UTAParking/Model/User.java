package UTAParking.Model;
import UTAParking.Data.UserDAO;

public class User {
	private String username="";
	private String password="";
	private String last_name="";
	private String first_name="";
	private String role="";
	private String UTA_Id="";
	private String phone="";
	private String email="";
	private String street_address="";
	private String city="";
	private String state="";
	private String zipcode="";
	private String Parking_Permit_Type="";
	
	public void setUser(String username, String password, String last_name, String first_name, String role, String UTA_Id, 
			String phone, String email, String street_address, String city, String state, String zipcode, String Parking_Permit_Type)
	{
		this.username = username;
		this.password = password;
		this.last_name = last_name;
		this.first_name = first_name;
		this.role = role;
		this.UTA_Id = UTA_Id;
		this.phone = phone;
		this.email = email;
		this.street_address = street_address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.Parking_Permit_Type = Parking_Permit_Type;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUTA_Id() {
		return UTA_Id;
	}
	public void setUTA_Id(String uTA_Id) {
		UTA_Id = uTA_Id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStreet_address() {
		return street_address;
	}
	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getParking_Permit_Type() {
		return Parking_Permit_Type;
	}
	public void setParking_Permit_Type(String parking_Permit_Type) {
		Parking_Permit_Type = parking_Permit_Type;
	}
	
//	Validations
	
	public void validateUser (User user, UserErrorMsgs errorMsgs) {
		
		errorMsgs.setFirstNameError(validateFirstName(user.getFirst_name()));
		errorMsgs.setLastNameError(validateLastName(user.getLast_name()));
		errorMsgs.setUsernameError(validateUsername(user.getUsername()));
		errorMsgs.setPasswordError(validatePassword(user.getPassword()));
		errorMsgs.setEmailError(validateEmail(user.getEmail()));
//		errorMsgs.setUtaIdError(validateUtaId(user.getUtaId()));
//		errorMsgs.setAgeError(validateAge(user.getAgeAsString()));

		errorMsgs.setErrorMsg();
	}
	
	public String validateFirstName (String first_name)
	{
		String result="";
		first_name = first_name.trim(); //trim out trailing and leading spaces
		
		if (!stringSize(first_name,3,50))
			result= "Your First Name must between 3 and 50 characters";
		else
			if (!first_name.matches("^[a-zA-Z]+$"))
				result="Your First Name must only contain alphabets";
		
		return result;
	}
	
	private String validateLastName(String last_name) {
		String result="";
	last_name = last_name.trim(); //trim out trailing and leading spaces
	
	if (!stringSize(last_name,3,50))
		result= "Your Last Name must between 3 and 50 characters";
	else
		if (!last_name.matches("^[a-zA-Z]+$"))
			result="Your Last Name must only contain alphabets";

		return result;
	}
	
	private String validateUsername(String username) {
		username = username.trim();
		String result="";
		if (!stringSize(username,3,15))
			result= "Your username must between 3 and 15 digits";
		else
			if (!username.matches("^[a-zA-Z0-9]+$"))
				result="Your username must only contain alphabets and numbers";
			else
				if (!UserDAO.isUnique(username))
					result="username already in database";
		return result;				
	}
	
	private String validatePassword(String password) {
		password = password.trim();
		String result="";
		if (!stringSize(password,8,15))
			result= "Your password must between 8 and 15 characters";
		else 
			if(!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@._#$%^&+=]).{8,}$"))
				result= "Your password must contain at least 1 upper case letter, at least 1 lower case letter and at least 1 special character(@#$%^&+=._)";
				
		//explanation of regex:
		/*      ^                 # start-of-string
				(?=.*[0-9])       # a digit must occur at least once
				(?=.*[a-z])       # a lower case letter must occur at least once
				(?=.*[A-Z])       # an upper case letter must occur at least once
				(?=.*[@#$%^&+=])  # a special character must occur at least once
				.{8,}             # anything, at least eight places though
				$                 # end-of-string/
		*/
		return result;
	}
	
	private String validateEmail(String email) {
		email = email.trim();
		String result="";
		if (!stringSize(email,8,45))
			result= "Your email must between 8 and 45 characters";
		else 
		   if(!email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+(.com|.gov|.net|.org|.edu|.mil)$"))
			 result= "Your email must contain @ and any one of the following extensions {.com,.gov,.net,.org,.edu,.mil}"; 
			  
		return result;
	}
	
//	helper functions
	private boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max;
	}
	private boolean isTextAnInteger(String string) {
        boolean result;
		try
        {
            Long.parseLong(string);
            result=true;
        } 
        catch (NumberFormatException e) 
        {
            result=false;
        }
		return result;
	}	
	
}
