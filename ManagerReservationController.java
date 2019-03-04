package arlington_parking_app.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import arlington_parking_app.model.User;
import arlington_parking_app.model.ReservationErrorMsgs;
import arlington_parking_app.data.ReservationDAO;
import arlington_parking_app.model.Reservation;
import arlington_parking_app.model.ReservationDetails;


@WebServlet("/ManagerReservationController")
public class ManagerReservationController extends HttpServlet {
	private static final long serialVersionUID = 9L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String url="/allReservations.jsp";
		
		ArrayList<ReservationDetails> reservationDetailsList = new ArrayList<ReservationDetails>();

		//List ALL reservations
		if (action.equalsIgnoreCase("viewReservations")) 
		{																
			reservationDetailsList = ReservationDAO.getAllReservations(); 
			session.setAttribute("RESERVATIONS", reservationDetailsList);	
			
			//filter by date
			if(request.getParameter("filterBtn2")!=null) {				
				String selectedStartTime = request.getParameter("startTime");
				String selectedEndTime = request.getParameter("endTime");
				
				//create reservation class only to validate times
				Reservation reservation = new Reservation();
				
				reservation.setStartTimeAsString(selectedStartTime);
				reservation.setEndTimeAsString(selectedEndTime);
				
				ReservationErrorMsgs RerrorMsgs = new ReservationErrorMsgs();
				reservation.validateViewFilterTimes(reservation, RerrorMsgs);
				session.setAttribute("Reservation",reservation);
				session.setAttribute("errorMsgs",RerrorMsgs);
				
				if (RerrorMsgs.getErrorMsg().equals("")) {	
					session.removeAttribute("errorMsgs");
					//create reservation details class to display info to jsp
					reservationDetailsList = ReservationDAO.getAllReservationsByDate( 
							reservation.getStartTimeAsString(), reservation.getEndTimeAsString()); 
					session.setAttribute("RESERVATIONS", reservationDetailsList);				
				}
				
			}
			
			//undo filters
			else if(request.getParameter("clearBtn2")!=null) {
				session.removeAttribute("Reservation");
				session.removeAttribute("errorMsgs");
			}
			
		}
		
		//delete a reservation
		else if(action.equalsIgnoreCase("deleteReservation")) {
			//tag reservation as deleted in DB
			ReservationDAO.deleteReservation(Integer.parseInt(request.getParameter("id")));
			//update list 
			reservationDetailsList = ReservationDAO.getAllReservations(); 
			session.setAttribute("RESERVATIONS", reservationDetailsList);		
		}
		
		//view details of reservation
		else if(action.equalsIgnoreCase("viewDetails")) {			
			int resId = Integer.parseInt(request.getParameter("id"));
			ReservationDetails currentReservation = ReservationDAO.getReservationById(resId);
			
			url="/reservationDetails.jsp";
			session.setAttribute("currentReservation", currentReservation);
			
			if(request.getParameter("backBtn")!=null) {
				session.removeAttribute("currentReservation");
				session.removeAttribute("errorMsgs");
				url="/allReservations.jsp";
			}
			
			
		}
	
		else if(action.equalsIgnoreCase("goHome")) {
			session.removeAttribute("Reservation");
			session.removeAttribute("currentReservation");
			session.removeAttribute("errorMsgs");	
			session.removeAttribute("RESERVATIONS");
			url="/managerHome.jsp";
		}
		
		else // redirect all other posts to get
			doGet(request,response);
		
		getServletContext().getRequestDispatcher(url).forward(request, response);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);		
	}

}

