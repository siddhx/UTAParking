package arlington_parking_app.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import arlington_parking_app.data.PaymentDAO;
import arlington_parking_app.data.ReservationDAO;
import arlington_parking_app.model.*;

@WebServlet("/PaymentController")
public class PaymentController extends HttpServlet {

	private static final long serialVersionUID = 12L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String url = "/payForRental.jsp";
		
		User user = new User(); 
		
		if (action.equalsIgnoreCase("submitPayment")) 
		{
			if(request.getParameter("payBtn")!=null)
			{
				user = (User) session.getAttribute("currentUser");
				//validate payment
				Payment payment = new Payment();
				payment.setPayment(user.getId(), request.getParameter("nameOnCard"), request.getParameter("cardNumber"), 
						request.getParameter("cardExpires"), request.getParameter("cardSecurityCode"));
				PaymentErrorMsgs PerrorMsgs = new PaymentErrorMsgs();
				payment.validatePayment(payment, PerrorMsgs);
				session.setAttribute("Payment",payment);
				session.setAttribute("errorMsgs",PerrorMsgs);
				
				if (PerrorMsgs.getErrorMsg().equals("")) {			
					
					//insert payment and retrieve auto-gen primary key
					int paymentId = PaymentDAO.insertPayment(payment);				
 
					//save reservation info
					Reservation reservation = (Reservation) session.getAttribute("Reservation");
					reservation.setPaymentId(paymentId);
					reservation.setIsCanceled(0);
					reservation.setIsDeleted(0);
					int resId = ReservationDAO.insertReservation(reservation);
					
					//remove session attributes
					reservation.setId(resId);
					
					session.setAttribute("Reservation", reservation);

					url = "/confirmation.jsp"; //redirect to confirmation page
				}
			}
			
			else if(request.getParameter("cancelPayBtn")!=null) {
				session.removeAttribute("Payment");
				session.removeAttribute("errorMsgs");		
				url = "/requestRental.jsp"; //redirect to rental search page
			}
			
		}
		
		else if (action.equalsIgnoreCase("goHome")) 
		{
			session.removeAttribute("Reservation");
			session.removeAttribute("Car");
			session.removeAttribute("Payment");
			session.removeAttribute("errorMsgs");
			session.removeAttribute("RESERVATIONS");
			session.removeAttribute("CARS");
			session.removeAttribute("userCapacity");
			
			url = "/customerHome.jsp"; //redirect to home page
		}
		
		else // redirect all other posts to get
			doGet(request,response);
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
			
	}
}