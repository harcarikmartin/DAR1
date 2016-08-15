package sk.tsystems.forum.junittests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sk.tsystems.forum.entities.Task;
import sk.tsystems.forum.entities.Topic;
import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.entities.Comment;
import sk.tsystems.forum.services.CommentServices;
import sk.tsystems.forum.services.TaskServices;
import sk.tsystems.forum.services.TopicServices;
import sk.tsystems.forum.services.UserServices;

public class CommentServicesTests {
	
	private TopicServices topicServices = new TopicServices();
	private UserServices userServices = new UserServices();
	private TaskServices taskServices = new TaskServices();
	private CommentServices commentServices = new CommentServices();
	private String nameOfTester = "tester";
	private String nameOfTestingTopic = "testing topic";
	private String nameOfTestingTask = "testing task";
	private String nameOfTestingComment = "testing comment";
	private User testedUser = new User();
	private Topic testedTopic = new Topic();
	private Task testedTask = new Task();
	private Task concereteTask = new Task();
	private Comment testedComment = new Comment();
	private Comment testedCommentNumberTwo = new Comment();
	private Comment concreteComment = new Comment();

	public Date parseDate() {
		String dateString = "2016-08-18";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = df.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
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

	public void createTestedUser() {
		testedUser.setUserName(nameOfTester);
		testedUser.setUserPassword("tester");
		testedUser.setBirthDate(parseDate());
		testedUser.setRole("user");
		testedUser.setStatus("pending");
		userServices.addUser(testedUser);
	}
	
	public void createTestedTopic() {
		testedTopic.setTopic(nameOfTestingTopic);
		testedTopic.setVisibility("private");
		testedTopic.setCreator(testedUser);
		topicServices.addTopicToDatabase(testedTopic);
	}
	
	public void createTestedTask() {
		testedTask.setTaskName(nameOfTestingTask);
		testedTask.setTopic(testedTopic);
		testedTask.setUser(testedUser);
		testedTask.setTask("testing task testing task");
		taskServices.addTaskToDatabase(testedTask);
	}

	public void createTestedTaskNumberTwo() {
		Task testedTaskNumberTwo = new Task();
		testedTaskNumberTwo.setTaskName("bonus");
		testedTaskNumberTwo.setTopic(testedTopic);
		testedTaskNumberTwo.setUser(testedUser);
		testedTaskNumberTwo.setTask("bonus bonus");
		taskServices.addTaskToDatabase(testedTaskNumberTwo);
	}
	
	public void createTestedComment(){
		testedComment.setComment(nameOfTestingComment);
		testedComment.setTask(testedTask);
		testedComment.setUser(testedUser);
		commentServices.addCommentToDatabase(testedComment);
	}
	
	private void createTestedCommentNumberTwo() {
		testedCommentNumberTwo.setComment("bonus");
		testedCommentNumberTwo.setTask(testedTask);
		testedCommentNumberTwo.setUser(testedUser);
		commentServices.addCommentToDatabase(testedCommentNumberTwo);
	}

	@Before
	public void createTestedUserAndTestedTopicAndTestedTaskAndTestedComment() {
		createTestedUser();
		createTestedTopic();
		createTestedTask();
		createTestedComment();
	}

	@After
	public void dropTestedUserAndTestedTopicAndTestedTaskAndTestedComment() {
		commentServices.removeComment(getCommentID());
		taskServices.removeTask(getTaskID());
		topicServices.removeTopic(nameOfTestingTopic);
		userServices.dropUser(nameOfTester);
	}
	
	@Test
	public void doesMethodAddCommentToDatabaseWork(){
		assertNotNull(commentServices.getComment(getCommentID()));
	}
	
	@Test
	public void doesMethodPrintComments(){
		assertNotNull(commentServices.printComments(getTaskID()));
	}
	
	@Test
	public void doesMethodRemoveCommentWork(){
		createTestedCommentNumberTwo();
		int idOfTestedCommentNumberTwo = getCommentID();
		commentServices.removeComment(idOfTestedCommentNumberTwo);
		assertNull(commentServices.getComment(idOfTestedCommentNumberTwo));
	}
	
	@Test
	public void doesMethodGetComment(){
		assertEquals(nameOfTestingComment, commentServices.getComment(getCommentID()).getComment());
	}
	
	@Test
	public void doesMethodUpdateCommentWork(){
		String commentBodyBeforeUpdate = commentServices.getComment(getCommentID()).getComment();
		commentServices.updateComment(getCommentID(),"new comment", testedTask);
		String commentBodyAfterUpdate = commentServices.getComment(getCommentID()).getComment();
		assertNotEquals(commentBodyAfterUpdate, commentBodyBeforeUpdate);
	}

}
