package UTAParking.controller;

import UTAParking.data.ReservationDAO;
import UTAParking.model.Reservation;
import UTAParking.model.ReservationErrorMsgs;

@WebServlet("/ViewReservationController")
public class ViewReservationController extends HttpServelet {
	private static final long serialVersionUID = 5L;
	
	protected void doGet(HttpServletRequest request, HttpServeletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServlet response) throws ServletException, IOException{
		String action = request.getParameter("action");
		HttpSession session =request.getSession();
		String url = "/requestParking.jsp";
		
		if(action.equalsIgnoreCase("requestParking")){
			Reservation reservation = new Reservation();
			reservation.setId(request.getParameter("username"));
			ReservationErrorMsgs RerrorMsgs = new ReservationErrorMsgs();
			reservation.verifyUsername(reservation, RerrorMsgs);
			session.setAttribute("Reservation", reservation);
			session.setAttribute("errorMsgs", RerrorMsgs);
			
			if(RerrorMsgs.getErrorMsgs().equals("")){
				url = "/reservation.jsp";
				reservation = ReservationDAO.getReservation(request.getParameter("id"));
				session.setAttribute("Reservation", reservation);
				
			}
			
		}
		else if(action.equalsIgnoreCase("goHome")){
			session.removeAttribute("Reservation");
			session.removeAttribute("RerrorMsgs");
			
			url = "/userHome.jsp";
		}
		
		getServletContext().getRequestDispatcher(url).forward(request.response);
	}

}
