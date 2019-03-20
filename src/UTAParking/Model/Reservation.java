package arlington_parking_app.model;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Reservation implements Serializable{
	private static final long serialVersionUID = 7L;
	private int id;
	private int parkingId;
	private int paymentId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private int hasCart;
	private int hasCamera;
	private int hasHistory;
	private int isCanceled;
	private int isDeleted;
	private double totalPrice;	
	private String startTimeAsString;
	private String endTimeAsString;	
	private String options;
	

	//only used for retrieving valid reservations directly from database
	public void setReservation (String parkingId, String paymentId, String startTime, 
			String endTime, String isCanceled, String isDeleted, String hasHistory, String hasCart, String hasCamera, double totalPrice) {		
		this.setParkingId(Integer.parseInt(parkingId));
		this.setPaymentId(Integer.parseInt(paymentId));
		this.setStartTime(convertStringToDate(startTime)); //will always be valid (comes from db)
		this.setEndTime(convertStringToDate(endTime));
		this.setIsCanceled(Integer.parseInt(isCanceled));
		this.setHasCart(Integer.parseInt(hasCart));
		this.setHasCamera(Integer.parseInt(hasCamera));
		this.setHasHistory(Integer.parseInt(hasHistory));
		this.setIsDeleted(Integer.parseInt(isDeleted));
		this.setTotalPrice(totalPrice);
		
		
	}		
	private LocalDateTime convertStringToDate(String string) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm", Locale.US).withResolverStyle(ResolverStyle.STRICT);
		return LocalDateTime.parse(string, formatter);
	}
	
	public double calculateTotalPrice (Parking parking) {
		double price = 0;				

		if(hasCart==1) {
			price+=15.95;
		}
		if(hasCamera==1){
			price+=2.95;
		}
		if(hasHistory==1){
			price+=1.95;
		}

        
				
	
		//truncate to 2 dp
		price = Math.floor(price * 100) / 100;				
		return price;	
	}
	
	//with add-ons,tax,discount & below-25 fine
	public double calculateFinalPrice (Parking parking) {
		
		double finalPrice = this.totalPrice;	
	
		//add 8.25% tax
		double tax = (0.0825 * finalPrice);
		finalPrice += tax;		
	
		finalPrice = Math.floor(finalPrice * 100) / 100;	
		return finalPrice;	
	}	
	 private int hoursBetween(LocalDateTime start, LocalDateTime end) {
		 double secondsBetween = ChronoUnit.SECONDS.between(start, end);
		 double hoursBetween = secondsBetween/3600.00; //IF EVEN 1 SEC MORE THAN 24 HOURS, count as 2 days
		 return (int) Math.ceil(hoursBetween);
	 }	 
	 
	

		
	public void setStartTimeAsString(String startTime) {
		this.startTimeAsString=startTime;
	}
	public String getStartTimeAsString() {
		return startTimeAsString;
	}
	public void setEndTimeAsString(String endTime) {
		this.endTimeAsString=endTime;
	}
	public String getEndTimeAsString() {
		return endTimeAsString;
	}	
	public double getTotalPrice() {
		return totalPrice;
	}	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}	
	public int getId() {
		return id;
	}	
	public void setId(int id) {
		this.id = id;
	}
	public int getParkingId(){
		return parkingId;
	}
	public void setParkingId(int parkingId){
		this.parkingId=parkingId;
	}
	public int getPaymentId(){
		return paymentId;
	}
	public void setPaymentId(int paymentId){
		this.paymentId=paymentId;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}	
	public void setStartTime(LocalDateTime startTime) {
		 this.startTime=startTime;
	}	
	public LocalDateTime getEndTime() {
		return endTime;
	}	
	public void setEndTime(LocalDateTime endTime) {
		 this.endTime=endTime;
	}	
	public int getIsCanceled() {
		return isCanceled;
	}	
	public void setIsCanceled(int isCanceled){
		this.isCanceled=isCanceled;
	}	
	public int getIsDeleted() {
		return isDeleted;
	}	
	public void setIsDeleted(int isDeleted){
		this.isDeleted=isDeleted;
	}	
	public void setStartAndEndTimes(String startTime, String endTime) {
		this.startTimeAsString = startTime;
		this.endTimeAsString = endTime;			
		this.startTime = convertStringToDate(startTime);
		this.endTime = convertStringToDate(endTime);
	}	
	//called for request rental time validation
    public void validateRequestTimes(Reservation reservation, ReservationErrorMsgs errorMsgs) {		
		errorMsgs.setStartTimeError(validateRequestStartTime(reservation.getStartTimeAsString()));
		errorMsgs.setEndTimeError(validateRequestEndTime(reservation.getEndTimeAsString()));
		errorMsgs.setErrorMsg();
	}    
	//called for customer & manager's view reservations filter time validation   
    public void validateViewFilterTimes(Reservation reservation, ReservationErrorMsgs errorMsgs) {		
		errorMsgs.setStartTimeError(validateViewStartTime(reservation.getStartTimeAsString()));
		errorMsgs.setEndTimeError(validateViewEndTime(reservation.getEndTimeAsString()));
		errorMsgs.setErrorMsg();
	}    
    private boolean startTimeValid = false;   
	private String validateViewStartTime(String dateAsString) {
		dateAsString = dateAsString.trim();
		String result="";
		if (dateAsString.length()==0)
			result= "Start Date-Time cannot be blank";
		else{
			try {
				this.startTime = convertStringToDate(dateAsString);			
				startTimeValid = true;//time is valid, set flag 
			}
			catch(Exception e) {
				result="Invalid Time or Format : Format must be yyyy-MM-dd HH:mm";
			}
		}			
		return result;
	}

	private String validateViewEndTime(String dateAsString) {
		dateAsString = dateAsString.trim();
		String result="";
		if (dateAsString.length()==0)
			result= "End Date-Time cannot be blank";
		else{
			try {
				this.endTime = convertStringToDate(dateAsString);
				//if start time is non-empty & valid, compare
				if(startTimeValid)
					if(endTime.isBefore(startTime) || endTime.isEqual(startTime)) 
						result="End Date-Time must be after Start Date-Time";					
			}
			catch (Exception e){
				result="Invalid Time or Format : Format must be yyyy-MM-dd HH:mm";
			}
		}				
		return result;
	}	
    
	//define store hours
	private LocalTime satOpen = LocalTime.parse("08:00",DateTimeFormatter.ofPattern("HH:mm")); //sat 8am - 5pm
	private LocalTime satsunClose = LocalTime.parse("17:00",DateTimeFormatter.ofPattern("HH:mm"));
	private LocalTime sunOpen= LocalTime.parse("12:00",DateTimeFormatter.ofPattern("HH:mm"));//sun 12pm - 5pm
	private LocalTime weekOpen = LocalTime.parse("08:00",DateTimeFormatter.ofPattern("HH:mm")); //m-f 8am-8pm
	private LocalTime weekClose = LocalTime.parse("20:00",DateTimeFormatter.ofPattern("HH:mm"));
		
	private String validateRequestStartTime(String dateAsString) {
		dateAsString = dateAsString.trim();
		String result="";
		if (dateAsString.length()==0)
			result= "CheckOut Date-Time cannot be blank";
		else{
			try {
				this.startTime = convertStringToDate(dateAsString);
				startTimeValid = true; //time is valid, set flag
				
				//check if before today
				if(startTime.isBefore(LocalDateTime.now()))
					result="CheckOut Date-Time cannot be in the past";
				else 
				{
					//get day of week
					String day = startTime.toLocalDate().getDayOfWeek().name();
					//get time hh:mm
					LocalTime time = startTime.toLocalTime();

					if(day.equals("SUNDAY")) 
					{
						if(time.isAfter(satsunClose) || time.isBefore(sunOpen)) //outside store hours
							result="CheckOut Time must be between 12:00 to 17:00 on Sundays";
					}
					else if(day.equals("SATURDAY")) 
					{
						if(time.isAfter(satsunClose) || time.isBefore(satOpen)) //outside store hours
							result="CheckOut Time must be between 08:00 to 17:00 on Saturdays";
					}
					else 
					{
						if(time.isAfter(weekClose) || time.isBefore(weekOpen)) //outside store hours
							result="CheckOut Time must be between 08:00 to 20:00 on weekdays";
					}	
				}
	   
			}
			catch(Exception e) {
				result="Invalid Time or Format : Format must be yyyy-MM-dd HH:mm";
			}
		}			
		return result;
	}

	private String validateRequestEndTime(String dateAsString) {
		dateAsString = dateAsString.trim();
		String result="";
		if (dateAsString.length()==0)
			result= "Return Date-Time cannot be blank";
		else
		{
			try {
				this.endTime = convertStringToDate(dateAsString);				
				//check if before today
				if(endTime.isBefore(LocalDateTime.now()))
					result="Return Date-Time cannot be in the past";				
				else 
				{
					//check if conform to store hours
					String day = endTime.toLocalDate().getDayOfWeek().name();	//get day of week
					LocalTime time = endTime.toLocalTime(); //get time hh:mm
	
					if(day.equals("SUNDAY")) 
					{
						if(time.isAfter(satsunClose) || time.isBefore(sunOpen)) //outside store hours
							result="Return Time must be between 12:00 to 17:00 on Sundays";
					}
					else if(day.equals("SATURDAY")) 
					{
						if(time.isAfter(satsunClose) || time.isBefore(satOpen)) //outside store hours
							result="Return Time must be between 08:00 to 17:00 on Saturdays";
					}
					else 
					{
						if(time.isAfter(weekClose) || time.isBefore(weekOpen)) //outside store hours
							result="Return Time must be between 08:00 to 20:00 on weekdays";
					}			
		
				}
				//if start time is non-empty & valid, compare
				if(startTimeValid)
				{
					if(endTime.isBefore(startTime) || endTime.isEqual(startTime)) 
						result="Return Date-Time must be after CheckOut Date-Time";							
				}
			}
			catch (Exception e){
				result="Invalid Time or Format : Format must be yyyy-MM-dd HH:mm";
			}
		}				
		return result;
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
		options="";	
		if(hasCart==1)
			options += "Cart";
		if(hasCamera==1)
			options += "Camera";
		if(hasHistory==1)
			options += "History";		
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}		
}
