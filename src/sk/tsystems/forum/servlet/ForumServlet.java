package sk.tsystems.forum.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.services.jpahelper.UserServices;
/**
 * Main Forum servlet, responible for deciding based on node requests 
 */
@WebServlet("/Forum")
public class ForumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	List<User> list = new ArrayList<>();
    HttpSession session;   
    User admin = new User();
	
	
	User user = new User();
	User user1 = new User();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForumServlet() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if ("login".equals(action) && "userName" != null && "userPassword" != null) {
			if((new UserServices().getUserID(request.getParameter("userName")) == 0)) {
				//bad login details case
				incorrectPassword(request);
			} else if(new UserServices().isPasswordCorrect(request.getParameter("userName"), request.getParameter("userPassword"))){
				//do login case
				doLogin(request);
			} else if(!(new UserServices().isPasswordCorrect(request.getParameter("userName"), request.getParameter("userPassword")))){
				//bad login details case
				incorrectPassword(request);
			}
		} else  if("registration".equals(action) && "userName" != null && "userPassword" != null && "userPasswordCheck" != null && "birthdate" != null) {
			if(! (request.getParameter("userPassword")).equals(request.getParameter("userPasswordCheck"))) {
				//passwords do not match case
				matchPasswords(request);
			} else if (request.getParameter("userPassword").length() < 8) {
				//password too short case
				lenghtenPassword(request);
			} else if (new UserServices().getUserID(request.getParameter("userName")) != 0) {
				//username already exist case
				existingUser(request);
			} else {
				//registration accepted case
				doRegister(request);
			}
		} else if("profile".equals(action)) {
			request.setAttribute("listProfile", 1);
		} else if("approve".equals(action)) {
			request.setAttribute("listUsersForApproval", 1);
			admin.setUserName("jozko");
			admin.setUserPassword("jozko");
			admin.setRole("admin");
			user1.setUserName("janko");
			user1.setUserPassword("janko");
			user1.setRole("user");
			list.add(admin);
			list.add(user1);
			request.setAttribute("pendingUsers", list);
		} else if("logout".equals(action)) {
			//logout case
			logout(request);
		}
		//forwarding response back to node
		forwardToList(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void logout(HttpServletRequest request) {
		session.setAttribute("user", null);
	}

	private void existingUser(HttpServletRequest request) {
		request.setAttribute("error", 3);
		request.setAttribute("regWrong", "registerFcn()");
	}

	private void lenghtenPassword(HttpServletRequest request) {
		request.setAttribute("error", 2);
		request.setAttribute("regWrong", "registerFcn()");
	}

	private void matchPasswords(HttpServletRequest request) {
		request.setAttribute("error", 1);
		request.setAttribute("regWrong", "registerFcn()");
	}

	private void doLogin(HttpServletRequest request) {
		user = new UserServices().setPresentUser(request.getParameter("userName"), request.getParameter("userPassword"));
		session = request.getSession();
		session.setAttribute("user", user);
	}
	
	private void doRegister(HttpServletRequest request) {
		String dateString = request.getParameter("birthdate");
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date date = null;
	    try {
	        date = df.parse(dateString);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
		user = new UserServices().registerUser(request.getParameter("userName"), request.getParameter("userPassword"), 
				date, request.getParameter("role"), request.getParameter("status"));
		session = request.getSession();
		session.setAttribute("user", user);
	}

	private void incorrectPassword(HttpServletRequest request) {
		request.setAttribute("error", 5);
	}
	
	private void forwardToList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		request.getRequestDispatcher("/WEB-INF/JSP/Forum.jsp").forward(request, response);
	}
}
