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

@WebServlet("/SearhForParkingSpotController")
public class RequestReservationController extends HttpServlet {
	private static final long serialVersionUID = 5L; 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		String url = "";
		String permitType = "";
		String startTime = "";
	    String duration = "";
		String cart ="";
		String camera = "";
		String history = "";
		//String username,pnum,snum;
		int id;
		/*if(session!=null){
			username = (String)session.getAttribute("username");
			String park = request.getParameter("parking");
			if(park.equals("park")){
				
				snum= (String) session.getAttribute("park");
				pnum=(String) session.getAttribute("park_num");
				response.setContentType("text/html");
			}
		}*/
		
		if(action.equalsIgnoreCase("goHome")){
			response.sendRedirect("userHome.jsp");
		}
		
		else if(action.equalsIgnoreCase("requestReservation")){
			User user = new User();
			UserErrorMsgs UerrorMsgs = new UserErrorMsgs();
			Reservation reservation = new Reservation();
			//user.setUsername(request.getParameter("username"));
			ReservationErrorMsgs RerrorMsgs = new ReservationErrorMsgs();
			reservation.getId(request.getParameter("id"));
			
			permitType = request.getParameter("permittype");
			startTime = request.getParameter("starttime");
			duration = request.getParameter("duration");
			
			reservation.setParkingAreaName(permitType);
			reservation.verifyParkingType(reservation,RerrorMsgs,permitType);
			//user.verifyParkingpermitype(user, UerrorMsgs, permitType);
			
			session.setAttribute("Reservation", reservation);
			session.setAttribute("RerrorMsgs", RerrorMsgs);
			
			if(RerrorMsgs.getErrorMsgs().equals("")) {// permit type matches
				try{
					session.removeAttribute("Reservation");
					
					session.removeAttribute("RerrorMsgs");
					List<Reservation> reservationList = new ArrayList<Reservation>();
					ReservationDAO reserveObj = new ReservationDAO();
					System.out.println(" Inside permit type matches");
					reservationList = reserveObj.getParkingArea(permitType);
					request.setAttribute("reservationList", reservationList);
					
					url = "/reservationrequestResult.jsp";
				}catch(Exception e){
					System.out.println(e);
				}
			}else 
				doGet(request,response);
			
			getServletContext().getRequestDispatcher(url).forward(request, response);
				
			
		}
		doGet(request,response);
	}

}
