package arlington_parking_app.controller;
 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import arlington_parking_app.data.UserDAO;
import arlington_parking_app.model.*;

@WebServlet("/EditAnotherUserController")
public class EditAnotherUserController extends HttpServlet {

	private static final long serialVersionUID = 5L; 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		HttpSession session = request.getSession();		
		String url="/searchUserProfile.jsp"; 

		//search for user profile and display it
		if (action.equalsIgnoreCase("displayUserProfile")) 
		{			
			User user = new User();
			user.setUsername(request.getParameter("username"));
			UserErrorMsgs UerrorMsgs = new UserErrorMsgs();
			user.verifyUsername(user, UerrorMsgs);
			session.setAttribute("User",user);
			session.setAttribute("errorMsgs",UerrorMsgs);	
			
			if (UerrorMsgs.getErrorMsg().equals("")) { //username exists		
				//redirect to user profile
				url = "/userProfile.jsp";
				user = UserDAO.getUser(request.getParameter("username"));				
				session.setAttribute("User", user); //pre-populate fields 
			}			
		}	 
		 

		else if (action.equalsIgnoreCase("goHome")) 
		{
			session.removeAttribute("User");
			session.removeAttribute("errorMsgs");
			session.removeAttribute("USERS");
			session.removeAttribute("successMsg");
			url = "/adminHome.jsp"; //redirect to home page
		}
		
	
			
		getServletContext().getRequestDispatcher(url).forward(request, response);
		
   }
}
