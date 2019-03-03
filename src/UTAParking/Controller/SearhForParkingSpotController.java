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

import UTAParking.data.*;
import UTAParking.model.*;

/**
 * Servlet implementation class SearhForParkingSpotController
 */
@WebServlet("/SearhForParkingSpotController")
public class SearhForParkingSpotController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearhForParkingSpotController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String permitTypeEntered="";
		String url = "";
		
		if(action.equalsIgnoreCase("goHome"))
		{
			//if(request.getParameter("goHome")!=null)
			//{
				response.sendRedirect("userHome.jsp");
		}
			else if(action.equalsIgnoreCase("SearchPermit"))
			{
				 permitTypeEntered = request.getParameter("permittype");
				//String startTimeEntered = request.getParameter("startTime");
				//String durationEntered = request.getParameter("duration");
				request.getSession().getAttribute("currentUser");
				System.out.println("permit Type entered : "+permitTypeEntered);
				User user = new User();
				UserErrorMsgs UerrorMsgs = new UserErrorMsgs();	
				user.setParkingpermitype(permitTypeEntered);
				user.verifyParkingpermitype(user,UerrorMsgs,permitTypeEntered);
				session.setAttribute("User", user);
				session.setAttribute("errorMsgs", UerrorMsgs);
				
				
				if (UerrorMsgs.getErrorMsg().equals("")) { //permit type matches		
					try{
						session.removeAttribute("User");
					
					session.removeAttribute("errorMsgs");
					List<Parking> parkingList = new ArrayList<Parking>();
					ParkingDAO ParkingObj = new ParkingDAO();
					System.out.println("Inside permit type matches");    //debug
					parkingList= ParkingObj.listParking(permitTypeEntered);
					request.setAttribute("parkingList",parkingList);
					//redirect to user profile
					url = "/parkingSearchResult.jsp";
					//parkingList = ParkingDAO
					//response.sendRedirect("userHome.jsp");
					//ParkingObj.
					//user = UserDAO.getUser(request.getParameter("username"));				
					//session.setAttribute("User", user); //pre-populate fields 
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
				else// redirect all other posts to get
					 
						doGet(request,response);
					
					getServletContext().getRequestDispatcher(url).forward(request, response);
				
		}
		doGet(request, response);
	}

}
