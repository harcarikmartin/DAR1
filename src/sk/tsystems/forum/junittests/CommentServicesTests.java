package sk.tsystems.forum.junittests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sk.tsystems.forum.services.CommentServices;
import sk.tsystems.forum.services.TaskServices;
import sk.tsystems.forum.services.TopicServices;
import sk.tsystems.forum.services.UserServices;

public class CommentServicesTests {

	private TopicServices topicServices = new TopicServices();
	private TaskServices taskServices = new TaskServices();
	private CommentServices commentServices = new CommentServices();
	private UserServices userServices = new UserServices();
	private CreatingTestingSamples sample = new CreatingTestingSamples();

	@Before
	public void createTestedUserAndTestedTopicAndTestedTaskAndTestedComment() {
		sample.createTestedUser();
		sample.createTestedTopic();
		sample.createTestedTask();
		sample.createTestedComment();
	}

	@After
	public void dropTestedUserAndTestedTopicAndTestedTaskAndTestedComment() {
		commentServices.removeComment(sample.getCommentID());
		taskServices.removeTask(sample.getTaskID());
		topicServices.removeTopic(sample.getNameOfTestingTopic());
		userServices.dropUser(sample.getNameOfTester());
	}

	@Test
	public void doesMethodAddCommentToDatabaseWork() {
		assertNotNull(commentServices.getComment(sample.getCommentID()));
	}

	@Test
	public void doesMethodPrintComments() {
		assertNotNull(commentServices.printComments(sample.getTaskID()));
	}

	@Test
	public void doesMethodRemoveCommentWork() {
		sample.createTestedCommentNumberTwo();
		int idOfTestedCommentNumberTwo = sample.getCommentID();
		commentServices.removeComment(idOfTestedCommentNumberTwo);
		assertNull(commentServices.getComment(idOfTestedCommentNumberTwo));
	}

	@Test
	public void doesMethodGetComment() {
		assertEquals(sample.getNameOfTestingComment(), commentServices.getComment(sample.getCommentID()).getComment());
	}

	@Test
	public void doesMethodUpdateCommentWork() {
		String commentBodyBeforeUpdate = commentServices.getComment(sample.getCommentID()).getComment();
		commentServices.updateComment(sample.getCommentID(), "new comment");
		String commentBodyAfterUpdate = commentServices.getComment(sample.getCommentID()).getComment();
		assertNotEquals(commentBodyAfterUpdate, commentBodyBeforeUpdate);
	}

}
