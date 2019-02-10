package UTAParking.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import UTAParking.Data.UserDAO;
import UTAParking.Model.User;
import UTAParking.Model.UserErrorMsgs;
/**
 * Servlet implementation class registerController
 */
@WebServlet(description = "used to register all users, admin and parking manager", urlPatterns = { "/registerController" })
public class registerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerController() {
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
		
		String url = "/register.jsp";
		
		User user = new User();
		user.setUser(request.getParameter("first_name"), request.getParameter("last_name"), 
				request.getParameter("username"), request.getParameter("password"), 
				request.getParameter("email"), request.getParameter("UTA_Id"), 
				request.getParameter("phone"), request.getParameter("street_address"),
				request.getParameter("role"), request.getParameter("city"),
				request.getParameter("state"),request.getParameter("zipcode"),
				request.getParameter("Parking_Permit_Type"));
				
		UserErrorMsgs UerrorMsgs = new UserErrorMsgs();
		user.validateUser(user, UerrorMsgs);
		session.setAttribute("User",user);
		session.setAttribute("errorMsgs",UerrorMsgs);
		
		if (UerrorMsgs.getErrorMsg().equals("")) {
			UserDAO.registerUser(user); //save employee if no errors
			session.removeAttribute("User");
			url = "/login.jsp"; //if successful, redirect to login page
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);		
	}

}
