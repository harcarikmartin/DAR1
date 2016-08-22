package sk.tsystems.forum.junittests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sk.tsystems.forum.services.CommentServices;
import sk.tsystems.forum.services.TaskServices;
import sk.tsystems.forum.services.TopicServices;
import sk.tsystems.forum.services.UserServices;

/**
 * This class contains JUnit Tests which check if all methods in
 * {@link CommentServices} work correctly.
 * 
 * @author karolklescinsky
 */

public class CommentServicesTests {

	private TopicServices topicServices = new TopicServices();
	private TaskServices taskServices = new TaskServices();
	private CommentServices commentServices = new CommentServices();
	private UserServices userServices = new UserServices();
	private CreatingTestingSamples sample = new CreatingTestingSamples();

	/**
	 * This method is called before each test. Calls method createTestedUser
	 * which adds testedUser into the database. Calls method createTestedTopic
	 * which adds testedTopic into the database. Calls method createTestedTask
	 * which adds testedTask into the database. Calls method createTestedComment
	 * which adds testedComment the into database.
	 */
	@Before
	public void createTestedUserAndTestedTopicAndTestedTaskAndTestedComment() {
		sample.createTestedUser();
		sample.createTestedTopic();
		sample.createTestedTask();
		sample.createTestedComment();
	}

	/**
	 * This method is called after each test. Calls method removeComment which
	 * drops testedComment from database. Calls method removeTask which drops
	 * testedTask from database. Calls method removeTopic which drops
	 * testedTopic from database. Calls method dropUser which drops testedUser
	 * from database.
	 */
	@After
	public void dropTestedUserAndTestedTopicAndTestedTaskAndTestedComment() {
		commentServices.removeComment(sample.getCommentID());
		taskServices.removeTask(sample.getTaskID());
		topicServices.removeTopic(sample.getNameOfTestingTopic());
		userServices.dropUser(sample.getNameOfTester());
	}

	/**
	 * Checks if specific comment was created earlier under "@Before"
	 * annotation. Checks if value after calling getComment method is not null.
	 */
	@Test
	public void doesMethodAddCommentToDatabaseWork() {
		assertNotNull(commentServices.getComment(sample.getCommentID()));
	}

	/**
	 * Checks if list of comments in specific task is not null.
	 */
	@Test
	public void doesMethodPrintComments() {
		assertNotNull(commentServices.printComments(sample.getTaskID()));
	}

	/**
	 * Creates specific comment, then this test removes that specific comment
	 * and checks if that specifi comment is in database. Null expected.
	 */
	@Test
	public void doesMethodRemoveCommentWork() {
		sample.createTestedCommentNumberTwo();
		int idOfTestedCommentNumberTwo = sample.getCommentID();
		commentServices.removeComment(idOfTestedCommentNumberTwo);
		assertNull(commentServices.getComment(idOfTestedCommentNumberTwo));
	}

	/**
	 * Checks two comments of type String, one stored as String in class
	 * {@link CreatingTestingSamples} and another stored in database.
	 */
	@Test
	public void doesMethodGetComment() {
		assertEquals(sample.getNameOfTestingComment(), commentServices.getComment(sample.getCommentID()).getComment());
	}

	/**
	 * Checks two Strings, one string before calling method updateComment and
	 * another one after calling method updateComment. Value should change from
	 * "testing comment" to "new comment".
	 */
	@Test
	public void doesMethodUpdateCommentWork() {
		String commentBodyBeforeUpdate = commentServices.getComment(sample.getCommentID()).getComment();
		commentServices.updateComment(sample.getCommentID(), "new comment");
		String commentBodyAfterUpdate = commentServices.getComment(sample.getCommentID()).getComment();
		assertNotEquals(commentBodyAfterUpdate, commentBodyBeforeUpdate);
	}

	/**
	 * Checks two task names of type String, one stored as String in class
	 * {@link CreatingTestingSamples} and another stored in database.
	 */
	@Test
	public void doesMethodGetTaskWork() {
		assertEquals(sample.getNameOfTestingTask(), taskServices.getTask(sample.getTaskID()).getTaskName());
	}

}
