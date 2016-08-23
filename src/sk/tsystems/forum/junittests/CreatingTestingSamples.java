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

	public Date parseDate() {
		Date date = new Date();
		return date;
	}

	public int getTaskID() {
		List<Task> listOfTestedTasks = new ArrayList<>();
		listOfTestedTasks = taskServices.printTasks(testedTopic.getTopicID());
		int indexOfTask = listOfTestedTasks.size() - 1;
		concereteTask = listOfTestedTasks.get(indexOfTask);
		int taskID = concereteTask.getTaskID();
		return taskID;
	}

	public int getCommentID() {
		List<Comment> listOfTestedComments = new ArrayList<>();
		listOfTestedComments = commentServices.printComments(testedTask.getTaskID());
		int indexOfComment = listOfTestedComments.size() - 1;
		concreteComment = listOfTestedComments.get(indexOfComment);
		int commentID = concreteComment.getCommentID();
		return commentID;
	}

	public User getTestedUser() {
		testedUser.setUserName(nameOfTester);
		testedUser.setUserPassword("tester");
		testedUser.setBirthDate(parseDate());
		testedUser.setRole("user");
		testedUser.setStatus("pending");
		testedUser.setTopics(listOfTopics);
		return testedUser;
	}
	
	public Topic getTestedTopic() {
		testedTopic.setTopic(nameOfTestingTopic);
		testedTopic.setVisibility("private");
		testedTopic.setCreator(testedUser);
		listOfTestedUsers.add(0, getTestedUser());
		testedTopic.setUsers(listOfTestedUsers);
		return testedTopic;
	}

	public Task getTestedTask() {
		testedTask.setTaskName(nameOfTestingTask);
		testedTask.setTopic(testedTopic);
		testedTask.setUser(testedUser);
		testedTask.setTask("testing task testing task");
		return testedTask;
	}

	public Task getTestedTaskNumberTwo() {
		testedTaskNumberTwo.setTaskName("bonus");
		testedTaskNumberTwo.setTopic(testedTopic);
		testedTaskNumberTwo.setUser(testedUser);
		testedTaskNumberTwo.setTask("bonus bonus");
		return testedTaskNumberTwo;
	}

	public Comment getTestedComment() {
		testedComment.setComment(nameOfTestingComment);
		testedComment.setTask(testedTask);
		testedComment.setUser(testedUser);
		return testedComment;
	}

	public Comment getTestedCommentNumberTwo() {
		testedCommentNumberTwo.setComment("bonus");
		testedCommentNumberTwo.setTask(testedTask);
		testedCommentNumberTwo.setUser(testedUser);
		return testedCommentNumberTwo;
	}

	public void createTestedUser() {
		getTestedUser();
		userServices.addUser(testedUser);
	}

	public void createTestedTopic() {
		getTestedTopic();
		topicServices.addTopicToDatabase(testedTopic);
	}

	public void createTestedTask() {
		getTestedTask();
		taskServices.addTaskToDatabase(testedTask);
	}

	public void createTestedTaskNumberTwo() {
		getTestedTaskNumberTwo();
		taskServices.addTaskToDatabase(testedTaskNumberTwo);
	}

	public void createTestedComment() {
		getTestedComment();
		commentServices.addCommentToDatabase(testedComment);
	}

	public void createTestedCommentNumberTwo() {
		getTestedCommentNumberTwo();
		commentServices.addCommentToDatabase(testedCommentNumberTwo);
	}

	public String getNameOfTester() {
		return nameOfTester;
	}

	public void setNameOfTester(String nameOfTester) {
		this.nameOfTester = nameOfTester;
	}

	public String getNameOfTestingTopic() {
		return nameOfTestingTopic;
	}

	public void setNameOfTestingTopic(String nameOfTestingTopic) {
		this.nameOfTestingTopic = nameOfTestingTopic;
	}

	public String getNameOfTestingTask() {
		return nameOfTestingTask;
	}

	public void setNameOfTestingTask(String nameOfTestingTask) {
		this.nameOfTestingTask = nameOfTestingTask;
	}

	public String getNameOfTestingComment() {
		return nameOfTestingComment;
	}

	public void setNameOfTestingComment(String nameOfTestingComment) {
		this.nameOfTestingComment = nameOfTestingComment;
	}

}