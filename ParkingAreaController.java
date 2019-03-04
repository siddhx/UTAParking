package UTAParking.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import UTAParking.data.ReservationDAO;
import UTAParking.model.Reservation;
import UTAParking.model.ReservationErrorMsgs;
import UTAParking.model.User;
import UTAParking.model.UserErrorMsgs;


public class ParkingAreaController extends HttpServlet{
	private static final long serialVersionUID = 5L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{ 
		doPost(request,response);
		}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String url = "/allReservations.jsp";
		String parkingArea = "";
		String permitType = "";
		
		//List<Reservation> reservationList = new ArrayList<Reservation>();
		if(action.equalsIgnoreCase("modifyParkingArea")){
			Reservation reservation = new Reservation();
			ReservationErrorMsgs RerrorMsgs = new ReservationErrorMsgs();
			reservation.verifyUsername(reservation, RerrorMsgs);
			session.setAttribute("Reservation", reservation);
			session.setAttribute("errorMsgs", RerrorMsgs);
			
			reservation.getParkingAreaName();
			reservation.setParkingAreaName(parkingAreaName);
			

			if(RerrorMsgs.getErrorMsgs().equals("")){
				try{
					session.removeAttribute("Reservation");
					
					session.removerAttribute("RerrorMsgs");
					List<Reservation> reservationList = new ArrayList<Reservation>();
					ReservationDAO reservereObj = new ReservationDAO();
					reservationList = reserveObj.getParkingArea(permitType);
				} catch(Exception e){
					System.out.println(e);
				}
			}else
				doGet(request,response);
			getServletContext().getRequestDispatcher(url).forward(request, response);
				
			}
		if(action.equalsIgnoreCase("addParkingArea")){
			Reservation reservation = new Reservation();
			reservation.setParkingAreaName(request.getParameter("parkingAreaName"));
			
			ReservationErrorMsgs RerrorMsgs = new ReservationErrorMsgs();
			reservation.verifyUsername(reservation, RerrorMsgs);
			session.setAttribute("Reservation", reservation);
			session.setAttribute("errorMsgs", RerrorMsgs);
			
			
			
			if(RerrorMsgs.getErrorMsgs().equals("")){
				try{
					session.removeAttribute("Reservation");
					
					session.removerAttribute("RerrorMsgs");
					List<Reservation> reservationList = new ArrayList<Reservation>();
					ReservationDAO reservereObj = new ReservationDAO();
				}catch(Exception e){
					System.out.println(e);
				}
			} else
				doGet(request,response);
		getServletContext().getRequestDispatcher(url).forward(request, response);
		}
		doGet(request,response);
		
		
		}
	

	



