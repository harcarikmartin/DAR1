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
			if((new UserServices().getUserID(request.getParameter("userName")) == 0)) {
				// user does not exist
				request.setAttribute("error", 5);
			} else if(!new UserServices().isUserApproved(request.getParameter("userName"))) {
				//user is not approved yet
				request.setAttribute("error", 6);
			} else if(new UserServices().isPasswordCorrect(request.getParameter("userName"), request.getParameter("userPassword")) && 
					new UserServices().isUserApproved(request.getParameter("userName"))) {
				//do login case
				doLogin(request);
			} else if(!(new UserServices().isPasswordCorrect(request.getParameter("userName"), request.getParameter("userPassword")))){
				//bad login details case
				incorrectPassword(request);
			}
		} else if ("register".equals(action)) {
			request.setAttribute("registerForm", 1);
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
			// show user's profile
			request.setAttribute("listProfile", 1);
		} else if("approve".equals(action)) {
			// show list of users, which are 'pending'
			if(!new UserServices().getPendingUsers().isEmpty()) {
				request.setAttribute("listUsersForApproval", 1);
				request.setAttribute("pendingUsers", new UserServices().getPendingUsers());
			} else {
				request.setAttribute("listUsersForApproval", 2);
			}
		} else if("approveUser".equals(action)) { 
			//approve user
			new UserServices().approveUser(request.getParameter("userForApproval"));
			if(!new UserServices().getPendingUsers().isEmpty()) {
				request.setAttribute("listUsersForApproval", 1);
				request.setAttribute("pendingUsers", new UserServices().getPendingUsers());
			}
		} else if("dropUser".equals(action)) { 
			//drop user
			new UserServices().dropUser(request.getParameter("userForApproval"));
			request.setAttribute("listUsersForApproval", 1);
			request.setAttribute("pendingUsers", new UserServices().getPendingUsers());
		} else if("changePassword".equals(action)){ 
			// show form for changing the password 
			request.setAttribute("changePassword", 1);
		} else if("changeMyPassword".equals(action)){
			// changing user's password
			if(!(request.getParameter("userPasswordOld").equals(user.getUserPassword()))) {
				//old password wrong
				wrongOldPass(request);
			} else if(! (request.getParameter("userPasswordNew")).equals(request.getParameter("userPasswordNewCheck"))) {
				//passwords do not match case
				matchPasswordsChange(request);
			} else if (request.getParameter("userPasswordNew").length() < 8) {
				//password too short case
				lenghtenPasswordChange(request);
			} else {
				// change user's password in DB
				changePassword(request);
			}
		} else if("logout".equals(action)) {
			//logout case
			logout(request);
		} else if("showMyTopics".equals(action)) {
			// list topics that user subscribed for
			request.setAttribute("listTopics", 1);	
			request.setAttribute("topics", new TopicServices().printTopics());
			request.setAttribute("userTopics", new UsersTopicsServices().getUsersTopics());
		} else if("updateTopic".equals(action)) {
			// show form for updating the topic
			request.setAttribute("topicUpdating", new TopicServices().setPresentTopic(request.getParameter("topicToUpdate")));
			request.setAttribute("topicToUpdate", 1);
		} else if("updateTheTopic".equals(action)) {
			// update of topic, rename, chenge of visibility
			// subscriptions for topic are removed from subscriptions table after changing its state from private to public
			if(request.getParameter("original").equals(request.getParameter("editTopic")) && 
					!(new TopicServices().setPresentTopic(request.getParameter("original")).getVisibility().equals(request.getParameter("visibility1"))) || 
					!request.getParameter("original").equals(request.getParameter("editTopic")) && 
					new TopicServices().setPresentTopic(request.getParameter("editTopic")) == null) {
				updateTopicSubscriptions(request);
				new TopicServices().updateTopic(request.getParameter("original"), request.getParameter("editTopic"), request.getParameter("visibility1"));
				request.setAttribute("listTopics", 1);
			} else if(new TopicServices().setPresentTopic(request.getParameter("editTopic")) != null) {
				// topic already exists
				request.setAttribute("topicToUpdate", 1);
				request.setAttribute("existingTopic", 1);
			}
		} else if("removeTopic".equals(action)) {
			// topic removal
			new TopicServices().removeTopic(request.getParameter("topicToRemove"));
			request.setAttribute("listTopics", 1);
		} else if("addTopic".equals(action)) {
			// showing form for adding topic
			request.setAttribute("topicAdding", 1);
		} else if("addTheTopic".equals(action)) {
			// adding topic to database
			if(new TopicServices().setPresentTopic(request.getParameter("addTheTopic")) == null) {
				Topic topic = new Topic();
				topic.setCreator(new UserServices().setPresentUser(user.getUserName()));
				topic.setTopic(request.getParameter("addTheTopic"));
				topic.setVisibility(request.getParameter("visibility"));
				new TopicServices().addTopicToDatabase(topic);
				request.setAttribute("listTopics", 1);
			} else if(new TopicServices().setPresentTopic(request.getParameter("addTheTopic")) != null) {
				// topic already exists
				request.setAttribute("topicAdding", 1);
				request.setAttribute("existingTopic", 1);
			}
		} else if ("changeTopics".equals(action)) {
			// change in user's subscriptions
			if(request.getParameterValues("topic") != null) {
				addUserSubscriptions(request);
			} else {
				updateUserSubscriptions(request);
			}
		} else if("openTopic".equals(action)) {
			// open the topic
			request.setAttribute("showTaskServlet", 1);
			request.getRequestDispatcher("/Forum/Task").forward(request, response);
		} else if("generate".equals(action)) {	
			// development action
			admin.setUserName("jozko");
			admin.setUserPassword("Jozko123+");
			admin.setRole("admin");
			admin.setStatus("confirmed");
			String dateString = "2016-08-18";
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = df.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			admin.setBirthDate(date);
			new UserServices().addUser(admin);
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
		request.setAttribute("changePassword", 1);
		request.setAttribute("error", 2);
		request.setAttribute("passChanged", 0);
	}
	
	private void wrongOldPass(HttpServletRequest request) {
		request.setAttribute("changePassword", 1);
		request.setAttribute("error", 7);
		request.setAttribute("passChanged", 0);
		
	}
	
	private void matchPasswordsChange(HttpServletRequest request) {
		request.setAttribute("changePassword", 1);
		request.setAttribute("error", 8);
		request.setAttribute("passChanged", 0);
		
	}

	private void changePassword(HttpServletRequest request) {
		new UserServices().changePassword(user.getUserName(), request.getParameter("userPasswordNew"));
		request.setAttribute("changePassword", 1);
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
		request.setAttribute("succesRegister", 1);
	}

	private void incorrectPassword(HttpServletRequest request) {
		request.setAttribute("error", 5);
	}
	
	private void addUserSubscriptions(HttpServletRequest request) {
		updateUserSubscriptions(request);
		String[] topicsId = request.getParameterValues("topic");
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
		for(Topic topic : new TopicServices().printTopics()) {
			new TopicServices().removeSubscriber(topic, user);	
		}
	}
	
	private void updateTopicSubscriptions(HttpServletRequest request) {
		Topic topic = new TopicServices().setPresentTopic(request.getParameter("original"));
		if(topic.getVisibility().equals("private") && request.getParameter("visibility1").equals("public")) {
			List<User> list = topic.getUsers();
			list.clear();
			topic.setUsers(list);
		}
	}

	private void forwardToList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("topics", new TopicServices().printTopics());
		request.setAttribute("userTopics", new UsersTopicsServices().getUsersTopics());
		request.getRequestDispatcher("/WEB-INF/JSP/Forum.jsp").forward(request, response);
	}
}