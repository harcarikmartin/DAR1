package sk.tsystems.forum.junittests;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sk.tsystems.forum.entities.Comment;
import sk.tsystems.forum.entities.Task;
import sk.tsystems.forum.entities.Topic;
import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.services.CommentServices;
import sk.tsystems.forum.services.TaskServices;
import sk.tsystems.forum.services.TopicServices;
import sk.tsystems.forum.services.UserServices;

/**
 * This class contains usefull methods needed in JUnit tests, getters and
 * setters, and is used for filling objects with testing data.
 * 
 * @author karolklescinsky
 * 
 */

public class CreatingTestingSamples {

	private Topic testedTopic = new Topic();
	private Task testedTask = new Task();
	private Task testedTaskNumberTwo = new Task();
	private Task concereteTask = new Task();
	private Comment testedComment = new Comment();
	private Comment concreteComment = new Comment();
	private Comment testedCommentNumberTwo = new Comment();
	private User testedUser = new User();

	private TopicServices topicServices = new TopicServices();
	private TaskServices taskServices = new TaskServices();
	private CommentServices commentServices = new CommentServices();
	private UserServices userServices = new UserServices();

	private String nameOfTester = "tester";
	private String nameOfTestingTopic = "testing topic";
	private String nameOfTestingTask = "testing task";
	private String nameOfTestingComment = "testing comment";

	private List<User> listOfTestedUsers = new ArrayList<>();
	private List<Topic> listOfTopics = new ArrayList<>();

	/**
	 * Gets current date and stores it in instance of Date class.
	 * 
	 * @return instance of class date filled with current date.
	 */
	public Date currentDate() {
		Date date = new Date();
		return date;
	}

	/**
	 * Fills instance "testedUser" of class User with data needed for testing.
	 * 
	 * @return filled instance "testedUser" of User class.
	 */
	public User getTestedUser() {
		testedUser.setUserName(nameOfTester);
		testedUser.setUserPassword("tester");
		testedUser.setBirthDate(currentDate());
		testedUser.setRole("user");
		testedUser.setStatus("pending");
		testedUser.setTopics(listOfTopics);
		return testedUser;
	}

	/**
	 * Fills instance "testedTopic" of class Topic with data needed for testing.
	 * 
	 * @return filled instance "testedTopic" of Topic class.
	 */
	public Topic getTestedTopic() {
		testedTopic.setTopic(nameOfTestingTopic);
		testedTopic.setVisibility("private");
		testedTopic.setCreator(testedUser);
		listOfTestedUsers.add(0, getTestedUser());
		testedTopic.setUsers(listOfTestedUsers);
		return testedTopic;
	}

	/**
	 * Fills instance "testedTask" of class Task with data needed for testing.
	 * 
	 * @return filled instance "testedTask" of Task class.
	 */
	public Task getTestedTask() {
		testedTask.setTaskName(nameOfTestingTask);
		testedTask.setTopic(testedTopic);
		testedTask.setUser(testedUser);
		testedTask.setTask("testing task testing task");
		return testedTask;
	}

	/**
	 * Fills instance "testedTaskNumberTwo" of class Task with data needed for
	 * testing.
	 * 
	 * @return filled instance "testedTaskNumberTwo" of Task class.
	 */
	public Task getTestedTaskNumberTwo() {
		testedTaskNumberTwo.setTaskName("bonus");
		testedTaskNumberTwo.setTopic(testedTopic);
		testedTaskNumberTwo.setUser(testedUser);
		testedTaskNumberTwo.setTask("bonus bonus");
		return testedTaskNumberTwo;
	}

	/**
	 * Fills instance "testedComment" of class Comment with data needed for
	 * testing.
	 * 
	 * @return filled instance "testedComment" of Comment class.
	 */
	public Comment getTestedComment() {
		testedComment.setComment(nameOfTestingComment);
		testedComment.setTask(testedTask);
		testedComment.setUser(testedUser);
		return testedComment;
	}

	/**
	 * Fills instance "testedCommentNumberTwo" of class Comment with data needed
	 * for testing.
	 * 
	 * @return filled instance "testedCommentNumberTwo" of Comment class.
	 */
	public Comment getTestedCommentNumberTwo() {
		testedCommentNumberTwo.setComment("bonus");
		testedCommentNumberTwo.setTask(testedTask);
		testedCommentNumberTwo.setUser(testedUser);
		return testedCommentNumberTwo;
	}

	/**
	 * Calls method getTestedUser to fill in instance of User class. Adds filled
	 * instance of User class into the database.
	 */
	public void createTestedUser() {
		getTestedUser();
		userServices.addUser(testedUser);
	}

	/**
	 * Calls method getTestedTopic to fill in instance of Topic class. Adds
	 * filled instance of Topic class into the database.
	 */
	public void createTestedTopic() {
		getTestedTopic();
		topicServices.addTopicToDatabase(testedTopic);
	}

	/**
	 * Calls method getTestedTask to fill in instance of Task class. Adds filled
	 * instance of Task class into the database.
	 */
	public void createTestedTask() {
		getTestedTask();
		taskServices.addTaskToDatabase(testedTask);
	}

	/**
	 * Calls method getTestedTaskNumberTwo to fill in instance of Task class.
	 * Adds filled instance of Task class into the database.
	 */
	public void createTestedTaskNumberTwo() {
		getTestedTaskNumberTwo();
		taskServices.addTaskToDatabase(testedTaskNumberTwo);
	}

	/**
	 * Calls method getTestedComment to fill in instance of Comment class. Adds
	 * filled instance of Comment class into the database.
	 */
	public void createTestedComment() {
		getTestedComment();
		commentServices.addCommentToDatabase(testedComment);
	}

	/**
	 * Calls method getTestedCommentNumberTwo to fill in instance of Comment
	 * class. Adds filled instance of Comment class into the database.
	 */
	public void createTestedCommentNumberTwo() {
		getTestedCommentNumberTwo();
		commentServices.addCommentToDatabase(testedCommentNumberTwo);
	}

	/**
	 * Creates empty list of tasks. Fills that list with instances of task class
	 * stored in database under specific Topic.
	 * 
	 * @return tasks Id of type integer.
	 */
	public int getTaskID() {
		List<Task> listOfTestedTasks = new ArrayList<>();
		listOfTestedTasks = taskServices.printTasks(testedTopic.getTopicID());
		int indexOfTask = listOfTestedTasks.size() - 1;
		concereteTask = listOfTestedTasks.get(indexOfTask);
		int taskID = concereteTask.getTaskID();
		return taskID;
	}

	/**
	 * Creates empty list of comments. Fills that list with instances of comment
	 * class stored in database under specific Task.
	 * 
	 * @return comments Id of type integer.
	 */
	public int getCommentID() {
		List<Comment> listOfTestedComments = new ArrayList<>();
		listOfTestedComments = commentServices.printComments(testedTask.getTaskID());
		int indexOfComment = listOfTestedComments.size() - 1;
		concreteComment = listOfTestedComments.get(indexOfComment);
		int commentID = concreteComment.getCommentID();
		return commentID;
	}

	public Task getConcereteTask() {
		return concereteTask;
	}

	public Comment getConcreteComment() {
		return concreteComment;
	}

	public TopicServices getTopicServices() {
		return topicServices;
	}

	public TaskServices getTaskServices() {
		return taskServices;
	}

	public CommentServices getCommentServices() {
		return commentServices;
	}

	public UserServices getUserServices() {
		return userServices;
	}

	public String getNameOfTester() {
		return nameOfTester;
	}

	public String getNameOfTestingTopic() {
		return nameOfTestingTopic;
	}

	public String getNameOfTestingTask() {
		return nameOfTestingTask;
	}

	public String getNameOfTestingComment() {
		return nameOfTestingComment;
	}

	public List<User> getListOfTestedUsers() {
		return listOfTestedUsers;
	}

	public List<Topic> getListOfTopics() {
		return listOfTopics;
	}

}