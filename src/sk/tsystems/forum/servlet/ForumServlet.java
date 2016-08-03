package sk.tsystems.forum.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.services.jpahelper.UserServices;
/**
 * Hlavny rozhodovaci servlet
 */
@WebServlet("/Forum")
public class ForumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User user;
    HttpSession session;   
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
				//bad login details
				incorrectPassword(request);
			} else if(new UserServices().isPasswordCorrect(request.getParameter("userName"), request.getParameter("userPassword"))){
				//do login
				doLogin(request);
			} else if(!(new UserServices().isPasswordCorrect(request.getParameter("userName"), request.getParameter("userPassword")))){
				//bad login details
				incorrectPassword(request);
			}
//		} else if("login".equals(action)) {
//			login(request);
//		} else if("register".equals(action)) {
//			register(request);
		} else  if("registration".equals(action) && "userName" != null && "userPassword" != null && "userPasswordCheck" != null && "birthdate" != null) {
			if(! (request.getParameter("userPassword")).equals(request.getParameter("userPasswordCheck"))) {
				matchPasswords(request);
			} else if (request.getParameter("userPassword").length() < 8) {
				lenghtenPassword(request);
			} else if (new UserServices().getUserID(request.getParameter("userName")) != 0) {
				existingUser(request);
			} else {
				doRegister(request);
			}
		} else if("logout".equals(action)) {
			logout(request);
//		} else if("play".equals(action) && request.getParameter("game") != null){
//			if(request.getParameter("player") == null) {
//				request.setAttribute("defaultLog", 1);
//			}
//			request.setAttribute("comments", new CommentJpa().findCommentsForGame(new GameJpa().setPresentGame(request.getParameter("game"))));
//			request.setAttribute("scores", new ScoreJpa().findTenBestScoresForGame(new GameJpa().setPresentGame(request.getParameter("game"))));
//		} else if("insertRating".equals(action) && request.getParameter("rating") != null) {
//			insertRating(request);
//		} else if("insertComment".equals(action) && !request.getParameter("comment").isEmpty()) {
//			insertComment(request);
//		} else {
//			request.setAttribute("defaultLog", 1);
		}
		forwardToList(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
//	private Game prepareEntities(HttpServletRequest request) {
//		request.setAttribute("name", player.getPlayerName());
//		Game game1 = new GameJpa().setPresentGame(request.getParameter("game"));
//		request.setAttribute("comments", new CommentJpa().findCommentsForGame(new GameJpa().setPresentGame(request.getParameter("game"))));
//		request.setAttribute("scores", new ScoreJpa().findTenBestScoresForGame(new GameJpa().setPresentGame(request.getParameter("game"))));
//		return game1;
//	}
//	
//	private void insertComment(HttpServletRequest request) {
//		Game game1 = prepareEntities(request);
//		new CommentJpa().addComment(new Comment(request.getParameter("comment").trim(), player, game1));
//	}
//
//	private void insertRating(HttpServletRequest request) {
//		Game game1 = prepareEntities(request);
//		new RatingJpa().addRating(new Rating(Integer.parseInt(request.getParameter("rating")), player, game1));
//	}
//

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

//	private void doRegister(HttpServletRequest request) {
//		request.setAttribute("showLogin", 1);
//		register(request);
//		request.setAttribute("error", 4);
//	}
//	
	private void serviceUpdate(HttpServletRequest request) {
//		request.setAttribute("games", games);
//		request.setAttribute("gamePlay", request.getParameter("game"));
		updateRatings();
//		request.setAttribute("avgRatings", avgRatings);
//		request.setAttribute("ratingsCounts", ratingsCounts);
	}
	
	private void forwardToList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		User user = new User();
//		user.setUserName("jozko");
//		user.setUserPassword("jozko");
//		new UserServices().addUser(user);
//		new GameJpa().setPresentGame("minesweeper");
//		new GameJpa().setPresentGame("gtn");
//		new GameJpa().setPresentGame("stones");
//		new GameJpa().setPresentGame("flipit");
//		new PlayerJpa().setPresentPlayer("root", "root");
//		new RatingJpa().addRating(new Rating(1, new PlayerJpa().setPresentPlayer("root", "root"), new GameJpa().setPresentGame("flipit")));
//		new RatingJpa().addRating(new Rating(1, new PlayerJpa().setPresentPlayer("root", "root"), new GameJpa().setPresentGame("gtn")));
//		new RatingJpa().addRating(new Rating(1, new PlayerJpa().setPresentPlayer("root", "root"), new GameJpa().setPresentGame("stones")));
		serviceUpdate(request);
		request.getRequestDispatcher("/WEB-INF/JSP/Forum.jsp").forward(request, response);
	}

	private void updateRatings() {
//		try {
//			avgRatings.clear();
//			ratingsCounts.clear();
//			for (int i = 0; i < games.size(); i++) {
//				
//					avgRatings.add(i, (int) new RatingJpa().findAverageRatingForGame(new GameJpa().setPresentGame(games.get(i).getGameName())));
//				
//			}
//			for (int i = 0; i < games.size(); i++) {
//				ratingsCounts.add(i,new RatingJpa().findRatingsCountForGame(new GameJpa().setPresentGame(games.get(i).getGameName())));
//			}
//		} catch (Exception e) {
//			System.err.println("No ratings found." + e.getMessage());
//		}
	}

}
