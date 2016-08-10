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

import sk.tsystems.forum.entities.Topic;
import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.services.TopicServices;
import sk.tsystems.forum.services.UserServices;
import sk.tsystems.forum.services.UsersTopicsServices;

/**
 * Main Forum servlet, responible for deciding based on node requests 
 */
@WebServlet("/Forum")
public class ForumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	List<User> list = new ArrayList<>();
    HttpSession session;   
    User admin = new User();
	List<Topic> topics = new ArrayList<>();
	
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
			if((new UserServices().getUserID(request.getParameter("userName")) == 0) || 
					!(new UserServices().isUserApproved(request.getParameter("userName")))) {
				//bad login details case
				incorrectPassword(request);
			} else if(new UserServices().isPasswordCorrect(request.getParameter("userName"), request.getParameter("userPassword")) && 
					new UserServices().isUserApproved(request.getParameter("userName"))) {
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
		} else if("showProfile".equals(action)) {
			request.setAttribute("listProfile", 1);
		} else if("approve".equals(action)) {
			request.setAttribute("listUsersForApproval", 1);
			request.setAttribute("pendingUsers", new UserServices().getPendingUsers());
		} else if("approveUser".equals(action)) { 
			//approve user
			new UserServices().approveUser(request.getParameter("userForApproval"));
			request.setAttribute("listUsersForApproval", 1);
			request.setAttribute("pendingUsers", new UserServices().getPendingUsers());
		} else if("dropUser".equals(action)) { 
			//drop user
			new UserServices().dropUser(request.getParameter("userForApproval"));
			request.setAttribute("listUsersForApproval", 1);
			request.setAttribute("pendingUsers", new UserServices().getPendingUsers());
		} else if("changePassword".equals(action)){ 
			request.setAttribute("changePassword", 1);
		} else if("changeMyPassword".equals(action)){
			if(!(request.getParameter("userPasswordOld").equals(user.getUserPassword()))) {
				//old password wrong
				matchPasswordsChange(request);
				request.setAttribute("listProfile", 1);
			} else if(! (request.getParameter("userPasswordNew")).equals(request.getParameter("userPasswordNewCheck"))) {
				//passwords do not match case
				matchPasswordsChange(request);
				request.setAttribute("listProfile", 1);
			} else if (request.getParameter("userPasswordNew").length() < 8) {
				//password too short case
				lenghtenPasswordChange(request);
				request.setAttribute("listProfile", 1);
			} else {
				changePassword(request);
			}
		} else if("logout".equals(action)) {
			//logout case
			logout(request);
		} else if("showMyTopics".equals(action)) {
			request.setAttribute("listTopics", 1);	
			request.setAttribute("topics", new TopicServices().printTopics());
			request.setAttribute("userTopics", new UsersTopicsServices().getUsersTopics());
		} else if("updateTopic".equals(action)) {
			// ak je zmena stavu z private na public treba dany topic odstranit z prepojovacej tabulky
			request.setAttribute("topicUpdating", new TopicServices().setPresentTopic(request.getParameter("topicToUpdate")));
		} else if("updateTheTopic".equals(action)) {
			new TopicServices().updateTopic(request.getParameter("original"), request.getParameter("editTopic"), request.getParameter("visibility1"));
			request.setAttribute("listTopics", 1);
		} else if("removeTopic".equals(action)) {
			new TopicServices().removeTopic(request.getParameter("topicToRemove"));
			request.setAttribute("listTopics", 1);
		} else if("addTopic".equals(action)) {
			if(new TopicServices().setPresentTopic(request.getParameter("addTopic")) == null) {
				Topic topic = new Topic();
				topic.setCreator(new UserServices().setPresentUser(user.getUserName()));
				topic.setTopic(request.getParameter("addTopic"));
				topic.setVisibility(request.getParameter("visibility"));
				new TopicServices().addTopicToDatabase(topic);
				request.setAttribute("listTopics", 1);
			} else {
				request.setAttribute("existingTopic", 1);
			}
		} else if ("register".equals(action)) {
			request.setAttribute("registerForm", 1);
		} else if ("changeTopics".equals(action)) {
			if(request.getParameterValues("topic") != null) {
				addUserSubscriptions(request);
			} else {
				updateUserSubscriptions(request);
			}
			
		} else if("generate".equals(action)) {	
			Topic topic1 = new Topic();
			topic1.setTopic("prvy topic");
			topic1.setCreator(admin);
			topic1.setVisibility("private");
			Topic topic2 = new Topic();
			topic2.setTopic("prvy topic");
			topic2.setCreator(admin);
			topic2.setVisibility("private");
			Topic topic3 = new Topic();
			topic3.setTopic("prvy topic");
			topic3.setCreator(admin);
			topic3.setVisibility("public");
			Topic topic4 = new Topic();
			topic4.setTopic("prvy topic");
			topic4.setCreator(admin);
			topic4.setVisibility("public");
			
			new TopicServices().addTopicToDatabase(topic1);
			new TopicServices().addTopicToDatabase(topic2);
			new TopicServices().addTopicToDatabase(topic3);
			new TopicServices().addTopicToDatabase(topic4);
			
			topics.add(topic1);
			topics.add(topic2);
			topics.add(topic3);
			topics.add(topic4);
			
			admin.setUserName("jozko");
			admin.setUserPassword("jozko");
			admin.setRole("admin");
			admin.setStatus("confirmed");
			user1.setUserName("janko");
			user1.setUserPassword("janko");
			user1.setRole("user");
			user1.setStatus("pending");
			new UserServices().addUser(admin);
			new UserServices().addUser(user1);
			list.add(admin);
			list.add(user1);
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

	
	private void lenghtenPasswordChange(HttpServletRequest request) {
		request.setAttribute("passChanged", 0);
	}

	private void matchPasswordsChange(HttpServletRequest request) {
		request.setAttribute("passChanged", 0);
		
	}

	private void changePassword(HttpServletRequest request) {
		new UserServices().changePassword(user.getUserName(), request.getParameter("userPasswordNew"));
		request.setAttribute("listProfile", 1);
		request.setAttribute("passChanged", 1);
	}

	private void logout(HttpServletRequest request) {
		session.setAttribute("user", null);
	}

	private void existingUser(HttpServletRequest request) {
		request.setAttribute("error", 3);
		request.setAttribute("registerForm", 1);
		request.setAttribute("regWrong", "registerFcn()");
	}

	private void lenghtenPassword(HttpServletRequest request) {
		request.setAttribute("error", 2);
		request.setAttribute("registerForm", 1);
		request.setAttribute("regWrong", "registerFcn()");
	}

	private void matchPasswords(HttpServletRequest request) {
		request.setAttribute("error", 1);
		request.setAttribute("registerForm", 1);
		request.setAttribute("regWrong", "registerFcn()");
	}

	private void doLogin(HttpServletRequest request) {
		user = new UserServices().setPresentUser(request.getParameter("userName"));
		session = request.getSession();
		session.setAttribute("user", user);
	}
	
	private void doRegister(HttpServletRequest request) {
		user = null;
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
	}

	private void incorrectPassword(HttpServletRequest request) {
		request.setAttribute("error", 5);
	}
	
	private void addUserSubscriptions(HttpServletRequest request) {
		updateUserSubscriptions(request);
		String[] topicsId = request.getParameterValues("topic");
		List<User> actualUser = new ArrayList<>();
		actualUser.add(user);
		
		for( int i = 0; i <= topicsId.length - 1; i++)
		{
			for(Topic topic:new TopicServices().printTopics()){
				if(topic.getTopicID()==Integer.parseInt(topicsId[i])){
					new TopicServices().setSubscriber(topic,user);	
			 }	
				
		  }
	    }
	}
	
	private void updateUserSubscriptions(HttpServletRequest request) {
		for(Topic topic:new TopicServices().printTopics()){
				new TopicServices().removeSubscriber(topic, user);	
	  }
		
	}

	private void forwardToList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("topics", new TopicServices().printTopics());
		request.setAttribute("userTopics", new UsersTopicsServices().getUsersTopics());
		
		request.getRequestDispatcher("/WEB-INF/JSP/Forum.jsp").forward(request, response);
	}
}