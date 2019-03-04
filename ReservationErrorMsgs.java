package UTAParking.model;

public class ReservationErrorMsgs 
{
	private String generalErrorMsg;
	private int id;
	private String usernameError;
	private String parkingTypeError;
	private String parkingAreaNameError;
	private int capacity;
	private int floor;
	private float cart;
	private float camera;
	private float history;
	
 public String getParkingTypeError(){
	 return parkingTypeError;
 }
 public void setParkingTypeError(String parkingTypeError){
	 this.parkingTypeError = parkingTypeError;
 }
 
 public String getParkingAreaNameError(){
	 return parkingAreaNameError;
 }
 
 public void setParkingAreaNameError(String parkingAreaNameError){
	 this.parkingAreaNameError = parkingAreaNameError;
 }
 
 public ReservationErrorMsgs(){
	 this.generalErrorMsg = "";
	 this.parkingAreaNameError = "";
	 this.parkingTypeError = "";
		 
 }
 
 public void setErrorMsg(){
	 if(!parkingAreaNameError.equals("")||!parkingTypeError.equals("")){
		 generalErrorMsg="Please correct the errors";
	 }
 }
 public String getUsernameError() {return usernameError;}
	public void setUsernameError(String usernameError) {this.usernameError = usernameError;}	
 public String getErrorMsgs(){return generalErrorMsg;}
 
 
}



