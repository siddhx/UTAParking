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


@WebServlet("/CustomerReservationController")
public class CustomerReservationController extends HttpServlet {
	private static final long serialVersionUID = 10L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String url="/myReservations.jsp";
		
		User currentUser = (User) session.getAttribute("currentUser");
		ArrayList<ReservationDetails> myReservationDetailsList = new ArrayList<ReservationDetails>();

		//List reservations
		if (action.equalsIgnoreCase("viewReservations")) 
		{					
			//use reservationDetails model to retrieve and display on jsp
			myReservationDetailsList = ReservationDAO.getAllMyReservations(currentUser.getId()); 
			session.setAttribute("RESERVATIONS", myReservationDetailsList);	
			
			//filter by date
			if(request.getParameter("filterBtn")!=null) {				
				String selectedStartTime = request.getParameter("startTime");
				String selectedEndTime = request.getParameter("endTime");
			
				//use reservation model only to validate entered search times
				Reservation reservation = new Reservation();
				reservation.setStartTimeAsString(selectedStartTime);
				reservation.setEndTimeAsString(selectedEndTime);			
				ReservationErrorMsgs RerrorMsgs = new ReservationErrorMsgs();
				reservation.validateViewFilterTimes(reservation, RerrorMsgs);
				session.setAttribute("Reservation",reservation);
				session.setAttribute("errorMsgs",RerrorMsgs);
				
				if (RerrorMsgs.getErrorMsg().equals("")) {	
					session.removeAttribute("errorMsgs");
					myReservationDetailsList = ReservationDAO.getAllMyReservationsByDate(currentUser.getId(), 
							reservation.getStartTimeAsString(), reservation.getEndTimeAsString()); 
					session.setAttribute("RESERVATIONS", myReservationDetailsList);				
				}
				
			}
			
			//undo filters
			else if(request.getParameter("clearBtn")!=null) {
				session.removeAttribute("Reservation");
				session.removeAttribute("errorMsgs");
			}
			
		}
		
		else if(action.equalsIgnoreCase("cancelReservation")) {
			//tag reservation as cancelled in DB
			ReservationDAO.cancelReservation(Integer.parseInt(request.getParameter("id")));
			myReservationDetailsList = ReservationDAO.getAllMyReservations(currentUser.getId()); 
			session.setAttribute("RESERVATIONS", myReservationDetailsList);		
		}
		
		else if(action.equalsIgnoreCase("goHome")) {
			session.removeAttribute("Reservation");
			session.removeAttribute("RESERVATIONS");
			session.removeAttribute("errorMsgs");
			url="/customerHome.jsp";
		}
	
		else // redirect all other posts to get
			doGet(request,response);
		
		getServletContext().getRequestDispatcher(url).forward(request, response);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);		
	}

}

