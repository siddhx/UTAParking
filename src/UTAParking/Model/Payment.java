package arlington_parking_app.model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Payment {
	private int userId;
	private String nameOnCard;
	private String cardNumber;
	private LocalDate cardExpires;
	private String cardSecurityCode;	
	private String cardExpiresAsString;

	public void setPayment(int userId, String nameOnCard, String cardNumber, 
			String cardExpiresAsString, String cardSecurityCode) {
		this.setUserId(userId);
		this.setNameOnCard(nameOnCard);
		this.setCardNumber(cardNumber);
		this.setCardExpiresAsString(cardExpiresAsString);
		this.setCardSecurityCode(cardSecurityCode);
	}

	public int getUserId(){
		return userId;
	}
	public void setUserId(int userId){
		this.userId=userId;
	}
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardSecurityCode() {
		return cardSecurityCode;
	}
	public void setCardSecurityCode(String cardSecurityCode) {
		this.cardSecurityCode = cardSecurityCode;
	}
	public String getCardExpiresAsString() {
		return cardExpiresAsString;
	}
	public void setCardExpiresAsString(String cardExpiresAsString) {
		this.cardExpiresAsString = cardExpiresAsString;
	}	
	public LocalDate getCardExpires() {
		return cardExpires;
	}
/************ VALIDATIONS *************/
	public void validatePayment (Payment payment, PaymentErrorMsgs errorMsgs) {	
		errorMsgs.setNameOnCardError(validateNameOnCard(payment.getNameOnCard()));
		errorMsgs.setCardNumberError(validateCardNumber(payment.getCardNumber()));
		errorMsgs.setCardExpiresError(validateCardExpires(payment.getCardExpiresAsString()));
		errorMsgs.setCardSecurityCodeError(validateCardSecurityCode(payment.getCardSecurityCode()));
		errorMsgs.setErrorMsg();
	}
	private String validateNameOnCard(String name) { 
		String result="";
		name = name.trim(); //trim out trailing and leading spaces	
		if (!stringSize(name,2,50))
			result= "Your name must between 2 and 50 characters";
		else
			if (!name.matches("^[a-zA-Z ]+$"))
				result="Your name must only contain alphabets and spaces";
		return result;
	}	
	private String validateCardNumber(String number) {
		number = number.trim();
		String result="";
		if (number.length()!=16)
			result= "Your Card Number must be exactly 16 digits long";
		else
			if (!isTextAnInteger(number))
				result="Your Card Number must be a number";
		return result;
	}
	private String validateCardExpires(String expiry) {
		expiry = expiry.trim();
		String result="";
		if (expiry.length()==0)
			result= "Card Expiration Date cannot be blank";
		else
		{
			if (!validFormat(expiry))
				result="Invalid Entry : Card Number must be in the format yyyy-MM";
			
			else //date format valid
				if (cardHasExpired(this.cardExpires))
				    result="This card has already expired!";			
		}		
		return result;
	}
	private String validateCardSecurityCode(String cvv) {
		cvv = cvv.trim();
		String result="";
		if (cvv.length()!=3)
			result= "Your Card Security Code must be exactly 3 digits long";
		else
			if (!isTextAnInteger(cvv))
				result="Your Card Security Code must be a number";
		return result;
	}	
	/************* AUXILIARY FUNCTIONS *************/
	private boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max;
	}
	private boolean isTextAnInteger (String string) {
        boolean result;
		try{
            Long.parseLong(string);
            result=true;
        } 
        catch (NumberFormatException e) {
            result=false;
        }
		return result;
	}	
	private boolean validFormat(String expiry) {
		boolean result=true;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM");
	    YearMonth yearMonth;
		try {
			yearMonth = YearMonth.parse(expiry, formatter);
			this.cardExpires = yearMonth.atEndOfMonth();
		} 
		catch(Exception e) {
			result=false;
		}
		return result;
	}	
	private boolean cardHasExpired(LocalDate expiry) {
		boolean expired=false;	
		LocalDate today = LocalDate.now();
		if(expiry.isBefore(today))
			expired=true;		
		return expired;
	}
}
