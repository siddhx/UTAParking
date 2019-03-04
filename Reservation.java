package UTAParking.model;

import java.io.Serializable;

import UTAParking.data.ParkingDAO;
import UTAParking.data.ReservationDAO;
import UTAParking.data.UserDAO;

public class Reservation implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String parkingType;
	private String parkingAreaName;
	private int capacity;
	private int floor;
	private float cart;
	private float camera;
	private float history;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getParkingType() {
		return parkingType;
	}
	public void setParkingType(String parkingType) {
		this.parkingType = parkingType;
	}
	public String getParkingAreaName() {
		return parkingAreaName;
	}
	public void setParkingAreaName(String parkingAreaName) {
		this.parkingAreaName = parkingAreaName;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public float getCart() {
		return cart;
	}
	public void setCart(float cart) {
		this.cart = cart;
	}
	public float getCamera() {
		return camera;
	}
	public void setCamera(float camera) {
		this.camera = camera;
	}
	public float getHistory() {
		return history;
	}
	public void setHistory(float history) {
		this.history = history;
	}
	
	/* ******************* VALIDATIONS ******************/
	public void validateReservation(Reservation reservation, ReservationErrorMsgs errorMsgs, String action)
	{
		switch(action){
		case "ParkingType":
			errorMsgs.setParkingTypeError(validateParkingType(reservation.getParkingType()));
		case "ParkingAreaName":
			errorMsgs.setParkingAreaNameError(validateParkingAreaNameError(reservation.getParkingAreaName()));
		}
		
	}
	private String validateParkingAreaNameError(String areaName) {
		// TODO Auto-generated method stub
		String result = "";
		String parkingAreaNameDB;
		String permitTypeDB = "";
		if(!parkingAreaName.equalsIgnoreCase("West Garage") && !parkingAreaName.equalsIgnoreCase("Maverick") && !parkingAreaName.equalsIgnoreCase("Davis") && !parkingAreaName.equalsIgnoreCase("Nedderman")){
			result = "Parking area name does not exist";
		}
		parkingAreaNameDB = ReservationDAO.getParkingArea(parkingType);
		if(!parkingAreaNameDB.equalsIgnoreCase(parkingAreaNameDB)){
			result = "this area does not exist";
		}
		return result;
	}
	public String verifyParkingType(Reservation reservation, ReservationErrorMsgs RerrorMsgs, String permitType) {
		// TODO Auto-generated method stub
		String result="";	
		String permitTypeinDB="";
		if (!parkingType.equalsIgnoreCase("access")  && !parkingType.equalsIgnoreCase("basic") &&
				!parkingType.equalsIgnoreCase("midrange")&& !parkingType.equalsIgnoreCase("premium"))
			result= "Permit Type does not exist";
		else
		{
			
			permitTypeinDB=ReservationDAO.getPermitType(username);
			if (!permitTypeinDB.equalsIgnoreCase(parkingType))
			{
				if(permitTypeinDB.equalsIgnoreCase("basic") && (parkingType.equalsIgnoreCase("midrange") || 
						parkingType.equalsIgnoreCase("premium") ||	parkingType.equalsIgnoreCase("access"))){
					result = "You do not have access to Midrange,Premium and Access Parking Permits";
				}
				else if(permitTypeinDB.equalsIgnoreCase("midrange") && (parkingType.equalsIgnoreCase("premium")
						||	parkingType.equalsIgnoreCase("access"))){
					result = " You do not have access to Premium & Access Parking Permits";
				}
				else if(permitTypeinDB.equalsIgnoreCase("premium") && parkingType.equalsIgnoreCase("access")){
					result = "You do not have access to 'Access' Parking Permits";
				}
				else if(permitTypeinDB.equalsIgnoreCase("access") && (!parkingType.equalsIgnoreCase("access"))){
					result = " You do no have access to Premium, Midrange or Basic Parking Permits";
				}	
			}
		}
		
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
	private boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max;
	}
    public void verifyUsername (Reservation reservation, ReservationErrorMsgs errorMsgs) {	
		errorMsgs.setUsernameError(validateUsername(reservation.getUsername()));
		errorMsgs.setErrorMsg();
	}    

}
