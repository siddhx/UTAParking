package arlington_parking_app.model;
public class PaymentErrorMsgs {
	private String errorMsg;
	private String nameOnCardError;
	private String cardNumberError;
	private String cardExpiresError;
	private String cardSecurityCodeError;
	
	public PaymentErrorMsgs () {
		this.errorMsg="";
	}	
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg() {
		if (!nameOnCardError.equals("") || !cardNumberError.equals("") || !cardExpiresError.equals("") || !cardSecurityCodeError.equals(""))
		  errorMsg="Please correct the following errors";
	}
	public String getNameOnCardError() {
		return nameOnCardError;
	}
	public void setNameOnCardError(String nameOnCardError) {
		this.nameOnCardError = nameOnCardError;
	}
	public String getCardNumberError() {
		return cardNumberError;
	}
	public void setCardNumberError(String cardNumberError) {
		this.cardNumberError = cardNumberError;
	}
	public String getCardExpiresError() {
		return cardExpiresError;
	}
	public void setCardExpiresError(String cardExpiresError) {
		this.cardExpiresError = cardExpiresError;
	}
	public String getCardSecurityCodeError() {
		return cardSecurityCodeError;
	}
	public void setCardSecurityCodeError(String cardSecurityCodeError) {
		this.cardSecurityCodeError = cardSecurityCodeError;
	}
}