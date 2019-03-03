package UTAParking.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import UTAParking.data.UserDAO;
import UTAParking.model.User;
import UTAParking.model.UserErrorMsgs;

/**
 * Servlet implementation class UserProfileEditforAdmin
 */
@WebServlet("/UserProfileEditforAdminController")
public class UserProfileEditforAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileEditforAdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		HttpSession session = request.getSession();		
		String url="/searchUserProfileforAdmin.jsp"; 

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
				url = "/userProfileforAdmin.jsp";
				user = UserDAO.getUserForAdmin(request.getParameter("username"));				
				session.setAttribute("User", user); //pre-populate fields 
			}			
		}
		else
			if(request.getParameter("revokeUser")!=null)
			{
				url = "/userProfileforAdmin.jsp";
				User user = new User();
				user.setUsername(request.getParameter("username"));
				UserErrorMsgs UerrorMsgs = new UserErrorMsgs();
				System.out.println("noshow"+request.getParameter("noShow"));
				System.out.println("Violation"+request.getParameter("violation"));
				
				if((request.getParameter("noShow").equals(null) && request.getParameter("violation").equals(null)) ||
						(request.getParameter("noShow").equals("") && request.getParameter("violation").equals("")))
				{	
					System.out.println("helooo");
					UerrorMsgs.setStatusError("Cannot revoke user without any violation or no-show");
					System.out.println(UerrorMsgs.getStatusError());
					//session.setAttribute("User",user);
					session.setAttribute("errorMsgs",UerrorMsgs);
				}
				else
				if((!request.getParameter("noShow").equals(null) || !request.getParameter("violation").equals(null))
						|| (request.getParameter("noShow").equals("") || request.getParameter("violation").equals("")))
				{
					System.out.println("heylooo");
					if(user.validateStatuswithouViolation(request.getParameter("username")).equalsIgnoreCase("Violated"))
					user.verifyStatusifRevoke(user, UerrorMsgs);
					user = UserDAO.getUserForAdmin(request.getParameter("username"));
					session.setAttribute("User",user);
					session.setAttribute("errorMsgs",UerrorMsgs);	
					
				}
				
				
			
			}
		 
			else
				if(request.getParameter("activateUser")!=null)
				{
					url = "/userProfileforAdmin.jsp";
					User user = new User();
					user.setUsername(request.getParameter("username"));
					UserErrorMsgs UerrorMsgs = new UserErrorMsgs();
					
					user.verifyStatusifActive(user, UerrorMsgs);
					user = UserDAO.getUserForAdmin(request.getParameter("username"));
					session.setAttribute("User",user);
					session.setAttribute("errorMsgs",UerrorMsgs);	
					
				}
		
				else
					if(request.getParameter("changeUserRole")!=null)
					{
						System.out.println("inside change role");
						url = "/userProfileforAdmin.jsp";
						User user = new User();
						user.setUsername(request.getParameter("username"));
						user.setRole(request.getParameter("role"));
						UserErrorMsgs UerrorMsgs = new UserErrorMsgs();
						//user.validateRole(user, UerrorMsgs);//request.getParameter("role"), request.getParameter("username"));
						if(request.getParameter("role")==(null) || request.getParameter("role").equals("") )
						{
							UerrorMsgs.setRoleError("Please select a role to change");
							session.setAttribute("errorMsgs",UerrorMsgs);
							user = UserDAO.getUserForAdmin(request.getParameter("username"));
							session.setAttribute("User",user);
						}
						else
						if(UserDAO.getRole(request.getParameter("username")).equals(request.getParameter("role")))
						{
							System.out.println("inside if again");
							UerrorMsgs.setRoleError("Cannot change to same role");
							System.out.println("role error"+UerrorMsgs.getRoleError());
							//session.setAttribute("User",user);
							session.setAttribute("errorMsgs",UerrorMsgs);
						}
						else
						{
							String result="";
							result=UserDAO.changeRole(request.getParameter("username"),request.getParameter("role"));
							UerrorMsgs.setRoleError(result);
							session.setAttribute("errorMsgs",UerrorMsgs);
							user = UserDAO.getUserForAdmin(request.getParameter("username"));
							session.setAttribute("User",user);
						}
						//user = UserDAO.getUserForAdmin(request.getParameter("username"));
						//session.setAttribute("User",user);
						
						
					}
		else if (action.equalsIgnoreCase("goHome")) 
		{
			session.removeAttribute("User");
			session.removeAttribute("errorMsgs");
			session.removeAttribute("USERS");
			session.removeAttribute("successMsg");
			url = "/AdminHome.jsp"; //redirect to home page
		}
		
	
			
		getServletContext().getRequestDispatcher(url).forward(request, response);
		
	}

}
