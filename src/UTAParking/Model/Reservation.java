package UTAParking.model;

import java.io.Serializable;

public class Reservation implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
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
	public void validateReservation()
	{
		
	}
}
