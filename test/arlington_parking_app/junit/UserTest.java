package arlington_parking_app.junit;

import static org.junit.Assert.*;

import org.junit.Test;

	import static org.junit.Assert.*;
	import org.junit.Before;
	import org.junit.Test;
	import org.junit.runner.RunWith;
	import arlington_parking_app.model.User;
	import arlington_parking_app.model.UserErrorMsgs;
	import junitparams.FileParameters;
	import junitparams.JUnitParamsRunner;

	@RunWith(JUnitParamsRunner.class)
	public class UserTest {

		User user;
		UserErrorMsgs uErrorMsgs;
		
		@Before
		public void setUp() throws Exception {
			user = new User();
			uErrorMsgs = new UserErrorMsgs();
		}

		@FileParameters("test/csv/User_Test_Cases.csv")
		@Test
		public void testRegister(int testCaseNumber, int id, String firstName, String lastName, 
				String utaId, String phone, String parkingpermitype, String role, String city, String address, String state, String zipcode,String username, String password, String email, int isRevoked, String action,  String errorMsg, String firstNameError, String lastNameError, 
				String utaIdError, String usernameError, String passwordError, 
				String emailError, String phoneError, String zipcodeError, String stateError, String parkingpermitypeError, String addressError, String cityError) {		
            user.setId(id);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setUtaId(utaId);
			user.setPhone(phone); 
			user.setParkingpermitype(parkingpermitype); 
			user.setRole(role);
			user.setCity(city);
			user.setAddress(address);
			user.setState(state);
			user.setZipcode(zipcode);
			user.setIsRevoked(isRevoked);
			user.validateUser(user, uErrorMsgs, action);	
			
			assertEquals(errorMsg,uErrorMsgs.getErrorMsg());
			assertEquals(firstNameError,uErrorMsgs.getFirstNameError());
			assertEquals(lastNameError,uErrorMsgs.getLastNameError());
			assertEquals(utaIdError,uErrorMsgs.getUtaIdError());
			assertEquals(usernameError,uErrorMsgs.getUsernameError());
			assertEquals(passwordError,uErrorMsgs.getPasswordError());
			assertEquals(emailError,uErrorMsgs.getEmailError());
			assertEquals(phoneError,uErrorMsgs.getPhoneError());
			assertEquals(zipcodeError,uErrorMsgs.getZipcodeError());
			assertEquals(phoneError,uErrorMsgs.getPhoneError());
			assertEquals(stateError,uErrorMsgs.getStateError());
			assertEquals(addressError,uErrorMsgs.getAddressError());
			assertEquals(cityError,uErrorMsgs.getCityError());
			assertEquals(isRevoked,user.getIsRevoked());
			assertEquals(id,user.getId());
			assertEquals(role,user.getRole());
		}
		
		@FileParameters("test/csv/User_Test_Cases_Login.csv")
		@Test
		public void testLogin(int testCaseNumber, String username, String password, 
				String errorMsg, String usernameError, String passwordError) {	
			user.setUsername(username);
			user.setPassword(password);	
			
			user.verifyPassword(user, uErrorMsgs);
			user.verifyUsername(user, uErrorMsgs);	
			
			assertEquals(errorMsg,uErrorMsgs.getErrorMsg());
			assertEquals(usernameError,uErrorMsgs.getUsernameError());
			assertEquals(passwordError,uErrorMsgs.getPasswordError());		
		}
	}



