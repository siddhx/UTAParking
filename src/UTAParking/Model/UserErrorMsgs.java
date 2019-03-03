package UTAParking.model;

public class UserErrorMsgs {
	private String errorMsg;
	private String firstNameError;
	private String lastNameError;
	private String usernameError;
	private String utaIdError;
	private String passwordError;
	private String emailError;
	private String phoneError;
	private String zipcodeError;
	private String stateError;
	private String addressError;
	private String parkingpermitypeError;
	private String cityError;
	private String noShowStatusError;
	private String violationStatusError;
	private String statusError;
	private String roleError;
	
	public String getRoleError() {
		System.out.println("inside role error");
		System.out.println(roleError);
		return roleError;
	}
	public void setRoleError(String roleError) {
		this.roleError = roleError;
	}
	public String getNoShowStatusError() {
		return noShowStatusError;
	}
	public void setNoShowStatusError(String noShowStatusError) {
		this.noShowStatusError = noShowStatusError;
	}
	public String getViolationStatusError() {
		return violationStatusError;
	}
	public void setViolationStatusError(String violationStatusError) {
		this.violationStatusError = violationStatusError;
	}
	public String getStatusError() {
		return statusError;
	}
	public void setStatusError(String statusError) {
		this.statusError = statusError;
	}
	public UserErrorMsgs () {
		this.errorMsg="";
		this.firstNameError="";
		this.lastNameError="";
		this.usernameError="";
		this.utaIdError="";
		this.emailError="";
		this.setZipcodeError("");
		this.setStateError("");
		this.setAddressError("");
		this.setParkingpermitypeError("");
		this.setCityError("");
		this.setPhoneError("");
		this.passwordError="";
		this.statusError="";
		this.violationStatusError="";
		this.noShowStatusError="";
		this.roleError="";
	}
	public void setErrorMsg() {
		if (!firstNameError.equals("") || !lastNameError.equals("") || !usernameError.equals("") 
		    || !utaIdError.equals("") || !emailError.equals("")  || !passwordError.equals("") || 
		    !phoneError.equals("")|| !zipcodeError.equals("")|| !stateError.equals("")||
		    !addressError.equals("") || !parkingpermitypeError.equals("")||!cityError.equals(""))
			
		  errorMsg="Please correct the following errors";
	}
	public String getErrorMsg() {return errorMsg;}
	public String getFirstNameError() {return firstNameError;}
	public void setFirstNameError(String firstNameError) {this.firstNameError = firstNameError;}
	public String getLastNameError() {return lastNameError;}
	public void setLastNameError(String lastNameError) {this.lastNameError = lastNameError;}	
	public String getUsernameError() {return usernameError;}
	public void setUsernameError(String usernameError) {this.usernameError = usernameError;}	
	public String getPasswordError() {return passwordError;}
	public void setPasswordError(String passwordError) {this.passwordError = passwordError;}	
	public String getUtaIdError() {return utaIdError;}
	public void setUtaIdError(String utaIdError) {this.utaIdError = utaIdError;}	
	public String getEmailError() {return emailError;}
	public void setEmailError(String emailError) {this.emailError = emailError;}
	public String getPhoneError() {
		return phoneError;
	}
	public void setPhoneError(String phoneError) {
		this.phoneError = phoneError;
	}
	public String getZipcodeError() {
		return zipcodeError;
	}
	public void setZipcodeError(String zipcodeError) {
		this.zipcodeError = zipcodeError;
	}
	public String getStateError() {
		return stateError;
	}
	public void setStateError(String stateError) {
		this.stateError = stateError;
	}
	public String getAddressError() {
		return addressError;
	}
	public void setAddressError(String addressError) {
		this.addressError = addressError;
	}
	public String getParkingpermitypeError() {
		return parkingpermitypeError;
	}
	public void setParkingpermitypeError(String parkingpermitypeError) {
		this.parkingpermitypeError = parkingpermitypeError;
	}
	public String getCityError() {
		return cityError;
	}
	public void setCityError(String cityError) {
		this.cityError = cityError;
	}

}