package arlington_parking_app.model;

public class ReservationErrorMsgs {

	private String errorMsg;
	private String startTimeError;
	private String endTimeError;
	
	public ReservationErrorMsgs () {
		this.errorMsg="";
	}	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg() {
		if (!startTimeError.equals("") || !endTimeError.equals(""))
		    errorMsg="Please correct the following errors";
	}
	public String getStartTimeError() {
		return startTimeError;
	}
	public void setStartTimeError(String startTimeError) {
		this.startTimeError = startTimeError;
	}
	public String getEndTimeError() {
		return endTimeError;
	}
	public void setEndTimeError(String endTimeError) {
		this.endTimeError = endTimeError;
	}
}