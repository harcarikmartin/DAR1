package sk.tsystems.forum.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sk.tsystems.forum.services.StatisticsServices;


/**
 * Servlet implementation class ForumStatsServlet
 */
@WebServlet("/ForumStatsServlet")
public class ForumStatsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("topic") != null) {
			session.removeAttribute("topic");
		}
		if(session.getAttribute("taskID") != null) {
			session.removeAttribute("taskID");
		}
		if(session.getAttribute("task") != null) {
			session.removeAttribute("task");
		}
		
		request.setAttribute("allUsersCount", new StatisticsServices().getAllUsersCount());
		request.setAttribute("adminsCount", new StatisticsServices().getAdminsCount());
		request.setAttribute("usersCount", new StatisticsServices().getUsersCount());
		request.setAttribute("approvedCount", new StatisticsServices().getApprovedCount());
		request.setAttribute("notApprovedCount", new StatisticsServices().getNotApprovedCount());
		request.setAttribute("topicsCount", new StatisticsServices().getTopicsCount());
		request.setAttribute("tasksCount", new StatisticsServices().getTasksCount());
		request.setAttribute("commentsCount", new StatisticsServices().getCommentsCount());
		request.setAttribute("publicTopicsCount", new StatisticsServices().getPublicTopicsCount());
		request.setAttribute("privateTopicsCount", new StatisticsServices().getPrivateTopicsCount());
		request.setAttribute("mostAnsweredTask", new StatisticsServices().getMostAnsweredTask());
		request.setAttribute("commentsForMostAnsweredTask", new StatisticsServices().getNumberOfCommentsForMostAnsweredTask());
		request.getRequestDispatcher("/WEB-INF/JSP/forumStats.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
