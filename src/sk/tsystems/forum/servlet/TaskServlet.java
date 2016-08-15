package sk.tsystems.forum.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import sk.tsystems.forum.entities.Task;
import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.junittests.TopicServicesTests;
import sk.tsystems.forum.services.TaskServices;
import sk.tsystems.forum.services.TopicServices;
import sk.tsystems.forum.services.UserServices;

/**
 * Servlet implementation class TaskServlet
 */
@WebServlet("/Task")
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		session = request.getSession();
		
		// press the button for adding new task
		if ("addTask".equals(action)) {
			// show form for adding the task
			request.setAttribute("taskAdding", 1);
		} 
		// press the button for submitting new task to DB
		else if("addTheTask".equals(action)) {
			System.out.println("Topic attbribute value: " + session.getAttribute("topic"));
			Task task = new Task(request.getParameter("nameOfTask"), request.getParameter("bodyOfTask"), new TopicServices().setPresentTopic((String) session.getAttribute("topic")), (User) session.getAttribute("user"));
			session.removeAttribute("topic");
			new TaskServices().addTaskToDatabase(task);
		} 
		// press the button for updating the task
		else if("updateTask".equals(action)) {
			
		} 
		// press the button for submitting updated task to DB
		else if("updateTheTask".equals(action)) {
			
		} 
		// press the button for removing the task from DB
		else if("removeTheTask".equals(action)) {
			
		} 
		// press the button for submitting a reply to task to DB
		else if("replyToTask".equals(action)) {
			
		}
		
		// default action
		forwardToList(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void forwardToList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/JSP/Tasks.jsp").forward(request, response);
	}
}
