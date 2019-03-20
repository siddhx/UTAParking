package arlington_parking_app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import arlington_parking_app.model.ReservationErrorMsgs;
import arlington_parking_app.model.ParkingErrorMsgs;
import arlington_parking_app.data.ReservationDAO;
import arlington_parking_app.data.ParkingDAO;
import arlington_parking_app.data.UserDAO;
import arlington_parking_app.model.ReservationDetails;
import arlington_parking_app.model.Reservation;
import arlington_parking_app.model.Parking;
import arlington_parking_app.model.User;


@WebServlet("/RequestReservationController")
public class RequestReservationController extends HttpServlet {
	private static final long serialVersionUID = 11L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String url="/requestParking.jsp";   
		
		ArrayList<Parking> parkingList = new ArrayList<Parking>(); 
		
		//List available parks
		if (action.equalsIgnoreCase("requestParking"))  
		{		
			User user = (User) session.getAttribute("currentUser");
			if (user.getIsRevoked() == 1)
				url="/revokedPage.jsp"; }
			if(request.getParameter("searchBtn")!=null) {
				
				//remove results from previous searches
				session.removeAttribute("Reservation");
				session.removeAttribute("Parking");
				session.removeAttribute("RESERVATIONS");
				session.removeAttribute("PARKINGS");
				
				String selectedStartTime = request.getParameter("startTime");
				String selectedEndTime = request.getParameter("endTime");
				String capacity = request.getParameter("capacity");
				
				//validate start/end time
				Reservation reservationTemp = new Reservation();
				reservationTemp.setStartTimeAsString(selectedStartTime);
				reservationTemp.setEndTimeAsString(selectedEndTime);	
				
				ReservationErrorMsgs RerrorMsgs = new ReservationErrorMsgs();
				reservationTemp.validateRequestTimes(reservationTemp, RerrorMsgs);
				session.setAttribute("Reservation",reservationTemp);
				session.setAttribute("errorMsgs",RerrorMsgs);
				
				//validate capacity entered 
				Parking parkingTemp = new Parking();
				parkingTemp.setCapacityAsString(capacity);
				ParkingErrorMsgs PerrorMsgs = new ParkingErrorMsgs();
				parkingTemp.validateCapacity(parkingTemp, PerrorMsgs);
				session.setAttribute("userCapacity",capacity);
				session.setAttribute("PerrorMsgs",PerrorMsgs);
				
								
				if (RerrorMsgs.getErrorMsg().equals("") && PerrorMsgs.getErrorMsg().equals("")) {	
					session.removeAttribute("errorMsgs");
					parkingList = ParkingDAO.getAvailableParkingCustomer(Integer.parseInt(capacity),
							reservationTemp.getStartTimeAsString(), reservationTemp.getEndTimeAsString()); 
					
					ArrayList<ReservationDetails> reservList = new ArrayList<ReservationDetails>();
					
					
					for(Parking parking : parkingList) {
						ReservationDetails reservationDetails = new ReservationDetails();
						reservationDetails.setStartTime(selectedStartTime);
						reservationDetails.setEndTime(selectedEndTime);	
						reservationDetails.setParkingareaName(parking.getParkingarea_name());
						reservationDetails.setCapacity(Integer.parseInt(capacity));
						
						double totalPrice = reservationTemp.calculateTotalPrice(parking);
						reservationDetails.setTotalPrice(totalPrice);	
						
						reservList.add(reservationDetails);
					}
					
					session.setAttribute("RESERVATIONS", reservList);
					session.setAttribute("PARKINGS", parkingList);
				}								
			}				
			else if(request.getParameter("clearBtn")!=null) {
				session.removeAttribute("errorMsgs");
				session.removeAttribute("PerrorMsgs");
				session.removeAttribute("Reservation");
				session.removeAttribute("Parking");
				session.removeAttribute("RESERVATIONS");
				session.removeAttribute("PARKINGS");
				session.removeAttribute("userCapacity");
			}			
		
		
	else if (action.equalsIgnoreCase("reserveParking")) 
	{   	
		    url="/addOptions.jsp";					    
		    
			int parkingId = Integer.parseInt(request.getParameter("parkingId"));
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
			//get car from car id
			Parking parking = ParkingDAO.getParkingById(parkingId);
			
			//construct partial reservation (to send to next page)
			Reservation reservation = new Reservation();
			reservation.setParkingId(parkingId);
			reservation.setStartAndEndTimes(startTime, endTime);
		
			//set total price
			reservation.setTotalPrice(totalPrice);
			
			//pass to session
			session.setAttribute("Parking", parking);
			session.setAttribute("Reservation", reservation);					

	}
	
	else if (action.equalsIgnoreCase("addOptions")) 
	{
		if(request.getParameter("continueBtn")!=null) {
			url="/payForParking.jsp";					
			//get car
			Parking parking = (Parking) session.getAttribute("Parking");
			//get reservation
			Reservation reservation = (Reservation) session.getAttribute("Reservation");		
			
			//get & set extras added
			String[] options = request.getParameterValues("options");			
			
			  if(options != null)
			  {
				for(String option : options) 
				{
					if(option.equalsIgnoreCase("cart"))
						reservation.setHasCart(1);
					if(option.equalsIgnoreCase("camera"))
						reservation.setHasCamera(1);			
					if(option.equalsIgnoreCase("history"))
						reservation.setHasHistory(1);
				}
			  }	
			
		
			
							
			double finalPrice = reservation.calculateFinalPrice(parking);
			reservation.setTotalPrice(finalPrice);
			
			session.setAttribute("Parking",parking);
			session.setAttribute("Reservation", reservation);
		}				
		
		else if(request.getParameter("backBtn")!=null) {
			session.removeAttribute("Payment");
			session.removeAttribute("errorMsgs");
			session.removeAttribute("CerrorMsgs");	
			url = "/requestParking.jsp"; //redirect to request rental list
		}
	}
		
		
		else if (action.equalsIgnoreCase("goHome")) 
		{
			session.removeAttribute("Reservation");
			session.removeAttribute("Parking");
			session.removeAttribute("Payment");
			session.removeAttribute("errorMsgs");
			session.removeAttribute("PerrorMsgs");
			session.removeAttribute("RESERVATIONS");
			session.removeAttribute("PARKINGS");
			session.removeAttribute("userCapacity");
			
			url = "/userHome.jsp"; //redirect to home page
		}
			
		//else // redirect all other posts to get
			//doGet(request,response);
		
		getServletContext().getRequestDispatcher(url).forward(request, response);

	}

	//protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	doPost(request, response);		
	//}

}

