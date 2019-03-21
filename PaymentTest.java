package arlington_parking_app.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import arlington_parking_app.model.Payment;
import arlington_parking_app.model.User;
import arlington_parking_app.data.UserDAO;
import arlington_parking_app.model.PaymentErrorMsgs;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class PaymentTest {

	//declare variable here
	Payment payment;
	PaymentErrorMsgs pErrorMsgs;
	User user;
	
	@Before
	public void setUp() throws Exception {
		//instantiate variable here
		payment = new Payment();
		pErrorMsgs = new PaymentErrorMsgs();
		user = new User();
	}

	@FileParameters("test/csv/Payment_Test_Cases.csv")
	@Test
	public void test(int testCaseNumber, String username, String nameOnCard, String cardNumber, 
			String cardExpiresAsString, String cardSecurityCode, int userId, String cardExpires, String errorMsg, 
			String nameOnCardError, String cardNumberError, String cardExpiresError, 
			String cardSecurityCodeError) {
		//get test user
		user = UserDAO.getUser(username);		
		//set payment
		payment.setPayment(user.getId(),nameOnCard,cardNumber,
				cardExpiresAsString,cardSecurityCode);
		//validate
		payment.validatePayment(payment, pErrorMsgs);
		
		//assert to check values in payment
		assertEquals(userId,payment.getUserId());
		
		if(payment.getCardExpires()!=null)
		  assertEquals(cardExpires,payment.getCardExpires().toString());
	    
		//asserts for error msgs
		assertEquals(errorMsg,pErrorMsgs.getErrorMsg());
	    assertEquals(nameOnCardError,pErrorMsgs.getNameOnCardError());
	    assertEquals(cardNumberError,pErrorMsgs.getCardNumberError());
	    assertEquals(cardExpiresError,pErrorMsgs.getCardExpiresError());
	    assertEquals(cardSecurityCodeError,pErrorMsgs.getCardSecurityCodeError());
	    	    
	}
}