package arlington_parking_app.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import arlington_parking_app.data.UserDAO;
import arlington_parking_app.model.*;

@WebServlet("/EditOwnProfileController")
public class EditOwnProfileController extends HttpServlet  {
private static final long serialVersionUID = 4L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");

		if (action.equals("displayProfile")) {
			String url = "/myProfile.jsp"; 
			//get user object from current session
			User currentUser = (User) session.getAttribute("currentUser");	
			session.setAttribute("User", currentUser); //pre-populate fields 				
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
		else // redirect all other gets to post
			doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		HttpSession session = request.getSession();				
		String url = "/myProfile.jsp";
		User currentUser = (User) session.getAttribute("currentUser");
		
		if(action.equalsIgnoreCase("editProfile"))
		{
			
			if (request.getParameter("saveBtn")!=null) 
			{
				session.removeAttribute("successMsg"); //from previous saved changes
				User user = new User();
				//set user attributes
				user.setFirstName(request.getParameter("firstName"));
				user.setLastName(request.getParameter("lastName"));
				user.setUsername(request.getParameter("username"));
				user.setPassword(request.getParameter("password"));
				user.setEmail(request.getParameter("email"));
				user.setUtaId(request.getParameter("utaId"));
				user.setRole(request.getParameter("role"));
				user.setIsRevoked(currentUser.getIsRevoked());
				
				UserErrorMsgs UerrorMsgs = new UserErrorMsgs();
				user.validateUser(user, UerrorMsgs, action);
				session.setAttribute("User",user);
				session.setAttribute("errorMsgs",UerrorMsgs);
				
				if (UerrorMsgs.getErrorMsg().equals("")) 
				{				
					UserDAO.updateUser(user); 
					session.setAttribute("currentUser", user);
					session.removeAttribute("errorMsgs");					
					//if successful, stay on page and show confirmation msg
					session.setAttribute("successMsg", "Changes successfully saved.");
				}
			}
			
			else if (request.getParameter("cancelBtn")!=null)  { //cancel button pressed, dont update
				session.removeAttribute("User");
				session.removeAttribute("errorMsgs");
				session.removeAttribute("successMsg");
				//redirect to appropriate home page based on role
				String role = currentUser.getRole();
				if(role.equals("User"))
				  url = "/userHome.jsp"; 
				else if(role.equals("Manager"))
				  url = "/managerHome.jsp";
				else
				  url = "/adminHome.jsp";							
			}		
		}
		
		else if(action.equalsIgnoreCase("goHome")) {
			session.removeAttribute("User");
			session.removeAttribute("errorMsgs");
			session.removeAttribute("successMsg");
			//redirect to appropriate home page based on role
			String role = currentUser.getRole();
			if(role.equals("User"))
			  url = "/userhome.jsp"; 
			else if(role.equals("Manager"))
			  url = "/managerHome.jsp";
			else
			  url = "/adminHome.jsp";
		}
		else // redirect all other posts to get
			doGet(request,response);
			
		getServletContext().getRequestDispatcher(url).forward(request, response);
			
	}

}
