package arlington_parking_app.model;

/* this class is used simply for jsp-displaying purposes. 
 * not the true reservation model 
 * does not contain validations
 */

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ReservationDetails implements Serializable{

	private static final long serialVersionUID = 6L;
	
	private int id; //reservation confirmation number
	private String username;
	private String parkingareaName;
	private int capacity;
	private int floor;
	private String startTime;
	private String endTime;
	private int hasCart;
	private int hasCamera;
	private int hasHistory;
	private double totalPrice;
	
	private String options="";

	public void setReservationDetails (int id, String username, String parkingareaName, int capacity, int floor,int hasCart, int hasCamera, int hasHistory,
			String startTime, String endTime, double totalPrice) {
		
		this.setId(id);
		this.setUsername(username);
		this.setParkingareaName(parkingareaName);
		this.setCapacity(capacity);		
		this.setStartTime(startTime.replace('T', ' '));		
		this.setEndTime(endTime.replace('T', ' '));
		this.setTotalPrice(totalPrice);
		this.setFloor(floor);
		this.setHasCart(hasCart);
		this.setHasCamera(hasCamera);
		this.setHasHistory(hasHistory);
		if(hasCart==1)
			setOptions(getOptions() + "Cart");
		if(hasCamera==1)
			setOptions(getOptions() + "Camera");
		if(hasHistory==1)
			setOptions(getOptions() + "History");
	}		
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	public int getCapacity(){
		return capacity;
	}

	public void setCapacity(int capacity){
		this.capacity=capacity;
	}
	public String getParkingareaName(){
		return parkingareaName;
	}

	public void setParkingareaName(String parkingareaName){
		this.parkingareaName=parkingareaName;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public void setUsername(String username){
		this.username=username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setStartTime(String startTime) {
		 this.startTime=startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public void setEndTime(String endTime) {
		 this.endTime=endTime;
	}


	public int getHasCart() {
		return hasCart;
	}


	public void setHasCart(int hasCart) {
		this.hasCart = hasCart;
	}


	public int getHasCamera() {
		return hasCamera;
	}


	public void setHasCamera(int hasCamera) {
		this.hasCamera = hasCamera;
	}


	public int getHasHistory() {
		return hasHistory;
	}


	public void setHasHistory(int hasHistory) {
		this.hasHistory = hasHistory;
	}


	public String getOptions() {
		return options;
	}


	public void setOptions(String options) {
		this.options = options;
	}


	public int getFloor() {
		return floor;
	}


	public void setFloor(int floor) {
		this.floor = floor;
	}
	

	

}
