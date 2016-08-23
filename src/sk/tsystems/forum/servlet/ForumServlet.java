package sk.tsystems.forum.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import sk.tsystems.forum.entities.Comment;
import sk.tsystems.forum.entities.Task;
import sk.tsystems.forum.entities.Topic;
import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.services.CommentServices;
import sk.tsystems.forum.services.TaskServices;
import sk.tsystems.forum.services.TopicServices;
import sk.tsystems.forum.services.UserServices;
import sk.tsystems.forum.services.UsersTopicsServices;

/**
 * Main servlet responsible for processing and forwarding the requests from and back to 
 * JSP file. Represents a controller part in MVC architecture implemented for this web 
 * application.
 * 
 * @author martinharcarik
 *
 */
@WebServlet("/Forum")
@MultipartConfig(maxFileSize = 16177215)
public class ForumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<User> list = new ArrayList<>();
	HttpSession session;
	User admin = new User();
	List<Topic> topics = new ArrayList<>();
	User user = new User();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForumServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		session = request.getSession();

		if ("mainPage".equals(action)) {
			// go to main page
			try {
				session.removeAttribute("topic");
				session.removeAttribute("taskID");
				session.removeAttribute("task");
			} catch (NullPointerException e) {
				System.err.println("session is empty " + e.getMessage());
			}
		} else if ("login".equals(action) && "userName" != null && "userPassword" != null) {
			clearSession();
			if ((new UserServices().getUserID(request.getParameter("userName")) == 0)) {
				// user does not exist
				request.setAttribute("error", 5);
			} else if (!new UserServices().isUserApproved(request.getParameter("userName"))) {
				// user is not approved yet
				request.setAttribute("error", 6);
			} else if (new UserServices().isPasswordCorrect(request.getParameter("userName"),
					request.getParameter("userPassword"))
					&& new UserServices().isUserApproved(request.getParameter("userName"))) {
				// do login case
				doLogin(request);
			} else if (!(new UserServices().isPasswordCorrect(request.getParameter("userName"),
					request.getParameter("userPassword")))) {
				// bad login details case
				incorrectPassword(request);
			}
		} else if ("register".equals(action)) {
			clearSession();
			request.setAttribute("registerForm", 1);
		} else if ("registration".equals(action) && "userName" != null && "userPassword" != null
				&& "userPasswordCheck" != null && "birthdate" != null) {
			if (!(request.getParameter("userPassword")).equals(request.getParameter("userPasswordCheck"))) {
				// passwords do not match case
				matchPasswords(request);
			} else if (request.getParameter("userPassword").length() < 8) {
				// password too short case
				lenghtenPassword(request);
			} else if (new UserServices().getUserID(request.getParameter("userName")) != 0) {
				// username already exist case
				existingUser(request);
			} else {
				// registration accepted case
				doRegister(request);
			}
		} else if ("showProfile".equals(action)) {
			// show user's profile
			request.setAttribute("listProfile", 1);
			testGetImage();
		} else if ("UploadImage".equals(action)) {
			testSaveImage(request);
		} else if ("approve".equals(action)) {
			// show list of users, which are 'pending'
			if (!new UserServices().getPendingUsers().isEmpty()) {
				request.setAttribute("listUsersForApproval", 1);
				request.setAttribute("pendingUsers", new UserServices().getPendingUsers());
			} else {
				request.setAttribute("listUsersForApproval", 2);
			}
		} else if ("approveUser".equals(action)) {
			// approve user
			new UserServices().approveUser(request.getParameter("userForApproval"));
			if (!new UserServices().getPendingUsers().isEmpty()) {
				request.setAttribute("listUsersForApproval", 1);
				request.setAttribute("pendingUsers", new UserServices().getPendingUsers());
			}
		} else if ("dropUser".equals(action)) {
			// drop user
			new UserServices().dropUser(request.getParameter("userForApproval"));
			request.setAttribute("listUsersForApproval", 1);
			request.setAttribute("pendingUsers", new UserServices().getPendingUsers());
		} else if ("changePassword".equals(action)) {
			// show form for changing the password
			request.setAttribute("changePassword", 1);
		} else if ("changeMyPassword".equals(action)) {
			// changing user's password
			if (!(request.getParameter("userPasswordOld").equals(user.getUserPassword()))) {
				// old password wrong
				wrongOldPass(request);
			} else if (!(request.getParameter("userPasswordNew"))
					.equals(request.getParameter("userPasswordNewCheck"))) {
				// passwords do not match case
				matchPasswordsChange(request);
			} else if (request.getParameter("userPasswordNew").length() < 8) {
				// password too short case
				lenghtenPasswordChange(request);
			} else {
				// change user's password in DB
				changePassword(request);
			}
		} else if ("logout".equals(action)) {
			// logout case
			logout();
			clearSession();
		} else if ("showMyTopics".equals(action)) {
			// list topics that user subscribed for
			request.setAttribute("listTopics", 1);
			request.setAttribute("topics", new TopicServices().printTopics());
			request.setAttribute("userTopics", new UsersTopicsServices().getUsersTopics());
		} else if ("updateTopic".equals(action)) {
			// show form for updating the topic
			request.setAttribute("topicUpdating",
					new TopicServices().getPresentTopic(request.getParameter("topicToUpdate")));
			request.setAttribute("topicToUpdate", 1);
		} else if ("updateTheTopic".equals(action)) {
			// update of topic, rename, chenge of visibility
			// subscriptions for topic are removed from subscriptions table
			// after changing its state from private to public
			if (request.getParameter("original").equals(request.getParameter("editTopic"))
					&& !(new TopicServices().getPresentTopic(request.getParameter("original")).getVisibility()
							.equals(request.getParameter("visibility1")))
					|| !request.getParameter("original").equals(request.getParameter("editTopic"))
							&& new TopicServices().getPresentTopic(request.getParameter("editTopic")) == null) {
				updateTopicSubscriptions(request);
				new TopicServices().updateTopic(request.getParameter("original"),
						request.getParameter("editTopic").trim(), request.getParameter("visibility1"));
				request.setAttribute("listTopics", 1);
			} else if (new TopicServices().getPresentTopic(request.getParameter("editTopic")) != null) {
				// topic already exists
				request.setAttribute("topicToUpdate", 1);
				request.setAttribute("existingTopic", 1);
			}
		} else if ("removeTopic".equals(action)) {
			// topic removal
			new TopicServices().removeTopic(request.getParameter("topicToRemove"));
			request.setAttribute("listTopics", 1);
		} else if ("addTopic".equals(action)) {
			// showing form for adding topic
			request.setAttribute("topicAdding", 1);
		} else if ("addTheTopic".equals(action)) {
			// adding topic to database
			if (new TopicServices().getPresentTopic(request.getParameter("addTheTopic")) == null) {
				addTopicToDB(request);
				request.setAttribute("listTopics", 1);
			} else if (new TopicServices().getPresentTopic(request.getParameter("addTheTopic")) != null) {
				// topic already exists
				request.setAttribute("topicAdding", 1);
				request.setAttribute("existingTopic", 1);
			}
		} else if ("changeTopics".equals(action)) {
			// change in user's subscriptions
			if (request.getParameterValues("topic") != null) {
				addUserSubscriptions(request);
			} else {
				updateUserSubscriptions();
			}
		} else if ("openTopic".equals(action)) {
			// open the topic
			session.setAttribute("topic", new TopicServices().getPresentTopic(request.getParameter("topic")));
			getTasksForTopic(request);
		} else if ("addTask".equals(action)) {
			getTasksForTopic(request);
			request.setAttribute("taskAdding", 1);
		} else if ("addTheTask".equals(action)) {
			// insert task into DB
			if (!request.getParameter("nameOfTask").trim().isEmpty()
					&& !request.getParameter("bodyOfTask").trim().isEmpty()) {
				// add the task to DB
				addTaskToDB(request);
				getTasksForTopic(request);
			} else {
				// empty field/s
				request.setAttribute("emptyField", 1);
			}
		} else if ("updateTask".equals(action)) {
			// show form for updating the task
			getTaskToUpdate(request);
			getTasksForTopic(request);
		} else if ("updateTheTask".equals(action)) {
			// update of task, rename, change body of task
			if (!request.getParameter("editNameTask").trim().isEmpty()
					&& !request.getParameter("editBodyTask").trim().isEmpty()) {
				// update task
				updateTaskInDB(request);
				getTasksForTopic(request);
			} else {
				// return message of empty field/s
				request.setAttribute("emptyField", 1);
			}
		} else if ("removeTask".equals(action)) {
			// task removal
			removeTaskFromDB(request);
			getTasksForTopic(request);
		} else if ("openTask".equals(action)) {
			// open the task
			openTask(request);
			showCommentsForTask(request);
		} else if ("addComment".equals(action)) {
			// shows form for adding comment
			request.setAttribute("commentAdding", 1);
			request.setAttribute("taskOpened", 1);
		} else if ("addTheComment".equals(action)) {
			// insert comment into DB
			if (!request.getParameter("comment").trim().isEmpty()) {
				addCommentToDB(request);
				showCommentsForTask(request);
			} else {
				// empty field for comment, could not add to DB
				request.setAttribute("emptyField", 1);
				showCommentsForTask(request);
			}
		} else if ("updateComment".equals(action)) {
			// show form for updating the comment
			request.setAttribute("commentUpdating", new CommentServices()
					.getComment(Integer.parseInt((String) request.getParameter("CommentToUpdate"))));
			request.setAttribute("commentToUpdate", 1);
		} else if ("updateTheComment".equals(action)) {
			// update of comment
			if (!request.getParameter("editComment").trim().isEmpty()) {
				// update comment
				updateComment(request);
				showCommentsForTask(request);
			} else {
				// return message of empty field/s
				request.setAttribute("emptyField", 1);
				showCommentsForTask(request);
			}
		} else if ("removeComment".equals(action)) {
			// comment removal
			removeCommentFromDB(request);
			showCommentsForTask(request);
		} else if ("showTopics".equals(action)) {
			// shows all topics for user
			clearSession();
		} else if ("showTasks".equals(action)) {
			// shows all tasks for topic
			showTasksForTopic(request);
		} else if ("userRoles".equals(action)) {
			// show form with list of users, can change roles of users
			getUserRoles(request);
		} else if ("toggleUserRole".equals(action)) {
			// toggle user role (admin->user || user->admin)
			new UserServices().toggleUserRole(request.getParameter("userForToggle"), request.getParameter("newRole"));
			getUserRoles(request);
		} else if ("deleteUser".equals(action)) {
			// drop user
			new UserServices().dropUser(request.getParameter("userToDelete"));
			getUserRoles(request);
		} else if ("color".equals(action)){
			session.setAttribute("color", request.getParameter("color"));
		} else if ("language".equals(action)){
			session.setAttribute("language", request.getParameter("language"));
			clearSession();
		}
		// forwarding response back to node
		forwardToList(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
	
	/**
	 * Gets the ordered list of instances of {@link User} class with their property 'userName' set to 'confirmed'. 
	 * The list is ordered by the property userName. Method sets the list as an attribute of the request with name
	 * 'users'.
	 * 
	 * @param request contains the list of all instances of the User class, which have their property 'userName' equal to 
	 * 'confirmed'
	 */
	private void getUserRoles(HttpServletRequest request) {
		request.setAttribute("listUserRoles", 1);
		request.setAttribute("users", new UserServices().getUsers());
	}
	
	/**
	 * Triggers the method for adding specified instance of the {@link Topic} class into database.
	 * 
	 * @param request provides the information needed to specify the details of the instance of the Topic class
	 */
	private void addTopicToDB(HttpServletRequest request) {
		Topic topic = new Topic();
		topic.setCreator(new UserServices().getPresentUser(user.getUserName()));
		topic.setTopic(request.getParameter("addTheTopic").trim());
		topic.setVisibility(request.getParameter("visibility"));
		new TopicServices().addTopicToDatabase(topic);
	}
	
	/**
	 * Triggers the method updateTask of {@link TaskServices} class for updating specified task.
	 * 
	 * @param request provides the information needed to specify the details of the instance of the Task class
	 */
	private void updateTaskInDB(HttpServletRequest request) {
		new TaskServices().updateTask(Integer.parseInt(request.getParameter("taskID")),
				request.getParameter("editNameTask").trim(), request.getParameter("editBodyTask").trim());
	}
	
	/**
	 * Gets the instance of the {@link Task} class, add this instance as an attribute to the instance request of the 
	 * {@link HttpServletRequest}
	 * 
	 * @param request contains the instance of the Task class, which will be updated
	 */
	private void getTaskToUpdate(HttpServletRequest request) {
		request.setAttribute("taskUpdating",
				new TaskServices().getTask(Integer.parseInt(request.getParameter("taskToUpdate"))));
		request.setAttribute("taskToUpdate", 1);
	}
	
	/**
	 * Creates new instance of the {@link Task} class and then triggers the method addTaskToDatabase, which 
	 * stores this instance into the database
	 * 
	 * @param request provides the information needed to specify the details of the instance of the Task class
	 */
	private void addTaskToDB(HttpServletRequest request) {
		Task task = new Task(request.getParameter("nameOfTask").trim(),
				request.getParameter("bodyOfTask").trim(), (Topic) session.getAttribute("topic"),
				(User) session.getAttribute("user"));
		new TaskServices().addTaskToDatabase(task);
	}
	
	/**
	 * Triggers the method removeTask, which removes selected instance of the class Task from the database
	 * 
	 * @param request provides the information needed to specify which instance of the Task class will be 
	 * removed from the database. Parameter 'taskToRemove' is the value of the property 'taskID' of the 
	 * Task class
	 */
	private void removeTaskFromDB(HttpServletRequest request) {
		new TaskServices().removeTask(Integer.parseInt(request.getParameter("taskToRemove")));
	}
	
	/**
	 * Gets the list of instances of the {@link Task} class for specified intance of the {@link Topic} class. 
	 * The list is stored as an attribute 'topicTasks' of instance request of the {@link HttpServletRequest} 
	 * class.
	 * 
	 * @param request contains the list of instances of the Task class for specified instance of the Topic class
	 */
	private void getTasksForTopic(HttpServletRequest request) {
		Topic topic = (Topic) session.getAttribute("topic");
		request.setAttribute("topicTasks", new TaskServices().printTasks(topic.getTopicID()));
		request.setAttribute("topicOpened", 1);
	}
	
	/**
	 * Gets the instance of the {@link Task} class by calling method getTask, and then stores the result as 
	 * an attribute 'task' of instance request of the {@link HttpServletRequest} class.
	 * 
	 * @param request contains the instance of the Task class
	 */
	private void openTask(HttpServletRequest request) {
		session.setAttribute("taskID", request.getParameter("idOfTask"));
		session.setAttribute("task",
				new TaskServices().getTask(Integer.parseInt(request.getParameter("idOfTask"))));
	}
	
	/**
	 * Adds the specified instance of the {@link Comment} class into database.
	 * 
	 * @param request provides the information needed to specify which instance of the Comment class will be 
	 * added to the database.
	 */
	private void addCommentToDB(HttpServletRequest request) {
		Date date = new Date(System.currentTimeMillis());
		Comment comment = new Comment(request.getParameter("comment").trim(),
				new TaskServices().getTask(Integer.parseInt((String) session.getAttribute("taskID"))),
				(User) session.getAttribute("user"), date);
		new CommentServices().addCommentToDatabase(comment);
	}
	
	/**
	 * Gets the list of the instances of the {@link Comment} class for specified instance of the {@link Task} class. 
	 * Property 'taskID' is retrieved from the attribute of instance of {@link HttpSession} class session.
	 * 
	 * @param request parameter 'taskOpened' contains the value 1
	 */
	private void showCommentsForTask(HttpServletRequest request) {
		session.setAttribute("taskComments",
				new CommentServices().printComments(Integer.parseInt((String) session.getAttribute("taskID"))));
		request.setAttribute("taskOpened", 1);
	}
	
	/**
	 * Triggers the method updateComment.
	 * 
	 * @param request provides the information needed to specify which instance of the Comment class will be 
	 * updated in the database.
	 */
	private void updateComment(HttpServletRequest request) {
		new CommentServices().updateComment(Integer.parseInt(request.getParameter("commentID")),
				request.getParameter("editComment").trim());
	}
	
	/**
	 * Triggers the method removeComment.
	 * 
	 * @param request provides the information needed to specify which instance of the Comment class will be 
	 * removed from the database.
	 */
	private void removeCommentFromDB(HttpServletRequest request) {
		new CommentServices().removeComment(Integer.parseInt(request.getParameter("CommentToRemove")));
	}
	
	/**
	 * Triggers the method getTasksForTopic.
	 * 
	 * @param request provides information needed as a field of the method getTasksForTopic.
	 */
	private void showTasksForTopic(HttpServletRequest request) {
		if (session.getAttribute("taskID") != null) {
			session.removeAttribute("taskID");
		}
		if (session.getAttribute("task") != null) {
			session.removeAttribute("task");
		}
		getTasksForTopic(request);
	}
	
	/**
	 * Removes attributes 'topic', 'taskID', 'task' from the instance of the {@link HttpSession} class
	 */
	private void clearSession() {
		if (session.getAttribute("topic") != null) {
			session.removeAttribute("topic");
		}
		if (session.getAttribute("taskID") != null) {
			session.removeAttribute("taskID");
		}
		if (session.getAttribute("task") != null) {
			session.removeAttribute("task");
		}
	}
	
	/**
	 * Sets the attributes of the instance of the {@link HttpServletRequest} class request
	 * 
	 * @param request instance of the {@link HttpServletRequest} class
	 */
	private void lenghtenPasswordChange(HttpServletRequest request) {
		request.setAttribute("changePassword", 1);
		request.setAttribute("error", 2);
		request.setAttribute("passChanged", 0);
	}
	
	/**
	 * Sets the attributes of the instance of the {@link HttpServletRequest} class request
	 * 
	 * @param request instance of the {@link HttpServletRequest} class
	 */
	private void wrongOldPass(HttpServletRequest request) {
		request.setAttribute("changePassword", 1);
		request.setAttribute("error", 7);
		request.setAttribute("passChanged", 0);
	}
	
	/**
	 * Sets the attributes of the instance of the {@link HttpServletRequest} class request
	 * 
	 * @param request instance of the {@link HttpServletRequest} class
	 */
	private void matchPasswordsChange(HttpServletRequest request) {
		request.setAttribute("changePassword", 1);
		request.setAttribute("error", 8);
		request.setAttribute("passChanged", 0);
	}
	
	/**
	 * Triggers the method changePassword. Sets the attributes of the instance of the {@link HttpServletRequest} 
	 * class request.
	 * 
	 * @param request
	 */
	private void changePassword(HttpServletRequest request) {
		new UserServices().changePassword(user.getUserName(), request.getParameter("userPasswordNew"));
		request.setAttribute("changePassword", 1);
		request.setAttribute("passChanged", 1);
	}
	
	/**
	 * Removes attributes 'user' from the instance of the {@link HttpSession} class
	 */
	private void logout() {
		if (session.getAttribute("user") != null) {
			session.removeAttribute("user");
		}
	}
	
	/**
	 * Sets the attributes of the instance of the {@link HttpServletRequest} class request
	 * 
	 * @param request instance of the {@link HttpServletRequest} class
	 */
	private void existingUser(HttpServletRequest request) {
		request.setAttribute("error", 3);
		request.setAttribute("registerForm", 1);
		request.setAttribute("regWrong", "registerFcn()");
	}
	
	/**
	 * Sets the attributes of the instance of the {@link HttpServletRequest} class request
	 * 
	 * @param request instance of the {@link HttpServletRequest} class
	 */
	private void lenghtenPassword(HttpServletRequest request) {
		request.setAttribute("error", 2);
		request.setAttribute("registerForm", 1);
		request.setAttribute("regWrong", "registerFcn()");
	}
	
	/**
	 * Sets the attributes of the instance of the {@link HttpServletRequest} class request
	 * 
	 * @param request instance of the {@link HttpServletRequest} class
	 */
	private void matchPasswords(HttpServletRequest request) {
		request.setAttribute("error", 1);
		request.setAttribute("registerForm", 1);
		request.setAttribute("regWrong", "registerFcn()");
	}
	
	/**
	 * Triggers the method setPresentUser, then sets returned instance of the {@link User} class as 
	 * the attribute of the instance of the {@link HttpSession} class session.
	 * 
	 * @param request provides information needed to retrieve property 'userName' of the User classs
	 */
	private void doLogin(HttpServletRequest request) {
		user = new UserServices().getPresentUser(request.getParameter("userName"));
		session.setAttribute("user", user);
	}
	
	/**
	 * Triggers the method registerUser of the {@link UserServices} class or sets the attribute 'error' 
	 * of the instance of the {@link HttpServletRequest} class request to value 9
	 * 
	 * @param request provides information needed for the method registerUser. Contains messages of 
	 * successful registration or error, if the registration was not successful.
	 */
	private void doRegister(HttpServletRequest request) {
		user = null;
		String dateString = request.getParameter("birthdate");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = df.parse(dateString);
		} catch (ParseException e) {
			System.err.println("Bad DOB format " + e.getLocalizedMessage());
		}
		if (date != null) {
			Date regDate = new Date(System.currentTimeMillis());
			new UserServices().registerUser(request.getParameter("userName").trim(),
					request.getParameter("userPassword").trim(), date, request.getParameter("role"),
					request.getParameter("status"), regDate);
			request.setAttribute("succesRegister", 1);
		} else {
			request.setAttribute("error", 9);
		}
	}
	
	/**
	 * Sets the attribute of the instance of the {@link HttpServletRequest} class request
	 * 
	 * @param request instance of the {@link HttpServletRequest} class
	 */
	private void incorrectPassword(HttpServletRequest request) {
		request.setAttribute("error", 5);
	}
	
	/**
	 * Triggers method setSubscriber of the {@link TopicServices} class for each instance of the 
	 * {@link Topic} class whose value of property 'topicID' is equal to the value of variable 
	 * topicsId.
	 * 
	 * @param request provides values of parameter 'topic'
	 */
	private void addUserSubscriptions(HttpServletRequest request) {
		updateUserSubscriptions();
		String[] topicsId = request.getParameterValues("topic");
		for (int i = 0; i <= topicsId.length - 1; i++) {
			for (Topic topic : new TopicServices().printTopics()) {
				if (topic.getTopicID() == Integer.parseInt(topicsId[i])) {
					new TopicServices().setSubscriber(topic, user);
				}
			}
		}
	}
	
	/**
	 * Triggers method removeSubscriber of the {@link TopicServices} class for each instance of the 
	 * {@link Topic} class that are stored in the database
	 */
	private void updateUserSubscriptions() {
		for (Topic topic : new TopicServices().printTopics()) {
			new TopicServices().removeSubscriber(topic, user);
		}
	}
	
	/**
	 * Clears the list of instances of {@link User} class for specified instance of {@link Topic} class, if the visibility 
	 * of the topic changes from 'private' to 'public'.
	 * 
	 * @param request provides parameters needed to identify the instance of the {@link Topic} class, that will be updated
	 */
	private void updateTopicSubscriptions(HttpServletRequest request) {
		Topic topic = new TopicServices().getPresentTopic(request.getParameter("original"));
		if (topic.getVisibility().equals("private") && request.getParameter("visibility1").equals("public")) {
			List<User> list = topic.getUsers();
			if(!list.isEmpty()) {
				list.clear();
			}
			topic.setUsers(list);
		}
	}
	
	/**
	 * Ensures creation of a single user in case of an empty database and storing it in database. 
	 * Sets the attributes 'topics' and 'userTopics' of the instance of {@link HttpServletRequest} class 
	 * request. Attribute 'topics' contains the list of all instances of the {@link Topic} class stored 
	 * in the database. Attribute 'userTopics' contains the list of all instances of the Topic class 
	 * for current instance of the {@link User} class stored in the database. 
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void forwardToList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// adds admin to DB if empty
		if (new UserServices().isDBEmpty()) {
			admin.setUserName("jozko");
			admin.setUserPassword("jozko");
			admin.setRole("admin");
			admin.setStatus("confirmed");
			admin.setRegisteredOn(new Date(System.currentTimeMillis()));
			String dateString = "2016-08-01";
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
		if(session.getAttribute("language") == null) {
			session.setAttribute("language", "en");
		}
		request.setAttribute("topics", new TopicServices().printTopics());
		request.setAttribute("userTopics", new UsersTopicsServices().getUsersTopics());
		request.getRequestDispatcher("/WEB-INF/JSP/Forum.jsp").forward(request, response);
	}
	
	/**
	 * Prepares and stores the property 'profileImage' of the instance of the {@link User} class in the database.
	 * 
	 * @param request provides the image needed to be stored for specified instance of User class
	 */
	private void testSaveImage(HttpServletRequest request) {
		byte[] bFile = null;
		try {
			Part filePart = request.getPart("fileToUpload");
			InputStream inputStream = filePart.getInputStream();
			BufferedImage image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
			image = ImageIO.read(inputStream);
			File f = new File("C:\\Users\\Študent\\git\\DAR1\\WebContent\\images\\" + getUser().getUserID() + ".jpg");
			ImageIO.write(image, "jpg", f);
			bFile = new byte[(int) f.length()];
			FileInputStream fileInputStream = new FileInputStream(f);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (IOException e) {
			System.err.println("IO Exception occured with message " + e.getMessage());
		} catch (ServletException e) {
			System.err.println("Servlet Exceptionn occured with message " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		getUser().setProfileImage(bFile);
		UserServices ur = new UserServices();
		ur.updateImage(getUser(), bFile);
	}
	
	/**
	 * Retrieves the property 'profileImage' of the instance of the {@link User} class and stores 
	 * it as an image file at the location defined by the argument of the instance of the 
	 * {@link FileOutputStream} class.
	 * 
	 */
	private void testGetImage() {
		byte[] bAvatar = getUser().getProfileImage();
		try {
			FileOutputStream fos = new FileOutputStream("C:\\Users\\Študent\\git\\DAR1\\WebContent\\images\\" + getUser().getUserID() + ".jpg");
			fos.write(bAvatar);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the instance of the {@link User} class that is stored as an attribute of the instance of the {@link HttpSession} 
	 * class session.
	 * 
	 * @return instance of the User class
	 */
	private User getUser() {
		User user = new User();
		if(session.getAttribute("user") != null) {
			user = (User) session.getAttribute("user");
		}
		return user;
	}
}