package sk.tsystems.forum.junittests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sk.tsystems.forum.services.CommentServices;
import sk.tsystems.forum.services.StatisticsServices;
import sk.tsystems.forum.services.TaskServices;
import sk.tsystems.forum.services.TopicServices;
import sk.tsystems.forum.services.UserServices;

public class StatisticsServicesTests {

	private TopicServices topicServices = new TopicServices();
	private TaskServices taskServices = new TaskServices();
	private CommentServices commentServices = new CommentServices();
	private UserServices userServices = new UserServices();
	private CreatingTestingSamples sample = new CreatingTestingSamples();
	private StatisticsServices statisticsServices = new StatisticsServices();

	@Before
	public void createTesterUserAndTestingTopic() {
		sample.createTestedUser();
		sample.createTestedTopic();
		sample.createTestedTask();
		sample.createTestedComment();
	}

	@After
	public void dropUser() {
		commentServices.removeComment(sample.getCommentID());
		taskServices.removeTask(sample.getTaskID());
		topicServices.removeTopic(sample.getNameOfTestingTopic());
		userServices.dropUser(sample.getNameOfTester());
	}

	@Test
	public void doesMethodGetAllUsersCountWork() {
		assertNotNull(statisticsServices.getAllUsersCount());
		assertEquals(statisticsServices.getAllUsersCount(),
				userServices.getPendingUsers().size() + userServices.getUsers().size());
	}

	@Test
	public void doesMethodGetAdminsCountWork() {
		assertEquals(statisticsServices.getAdminsCount(),
				statisticsServices.getAllUsersCount() - statisticsServices.getAllUsersCount());
	}

	@Test
	public void doesMethodGetUsersCountWork() {
		assertNotNull(statisticsServices.getUsersCount());
		assertEquals(statisticsServices.getUsersCount(),
				statisticsServices.getAllUsersCount() - statisticsServices.getAdminsCount());
	}

	@Test
	public void doesMethodGetApprovedCountWork() {
		assertNotNull(statisticsServices.getApprovedCount());
		assertEquals(statisticsServices.getApprovedCount(),
				statisticsServices.getAllUsersCount() - statisticsServices.getNotApprovedCount());
	}

	@Test
	public void doesMethodGetNotApprovedCountWork() {
		assertNotNull(statisticsServices.getNotApprovedCount());
		assertEquals(statisticsServices.getNotApprovedCount(),
				statisticsServices.getAllUsersCount() - statisticsServices.getApprovedCount());
	}

	@Test
	public void doesMethodGetTopicsCountWork() {
		assertNotNull(statisticsServices.getTopicsCount());
		assertEquals(statisticsServices.getTopicsCount(), topicServices.printTopics().size());
	}

	@Test
	public void doesMethodGetTasksCountWork() {
		assertNotNull(statisticsServices.getTasksCount());
	}

	@Test
	public void doesMethodGetCommentsCountWork() {
		assertNotNull(statisticsServices.getCommentsCount());
	}

	@Test
	public void doesMethodGetPublicTopicsCountWork() {
		assertEquals(statisticsServices.getPublicTopicsCount(),
				statisticsServices.getTopicsCount() - statisticsServices.getPrivateTopicsCount());
	}

	@Test
	public void doesMethodGetPrivateTopicsCountWork() {
		assertEquals(statisticsServices.getPrivateTopicsCount(),
				statisticsServices.getTopicsCount() - statisticsServices.getPublicTopicsCount());
	}

	@Test
	public void doesMethodGetMostAnsweredTaskWork() {
		assertNotNull(statisticsServices.getMostAnsweredTask());
	}

	@Test
	public void doesMethodGetNumberOfCommentsForMostAnsweredTaskWork() {
		assertNotNull(statisticsServices.getNumberOfCommentsForMostAnsweredTask());
	}

	@Test
	public void doesMethodGetMostActiveUserWork() {
		assertNotNull(statisticsServices.getMostActiveUser());
	}

	@Test
	public void doesMethodGetNumberOfCommentsForMostActiveUserWork() {
		assertNotNull(statisticsServices.getNumberOfCommentsForMostActiveUser());
	}

	@Test
	public void doesMethodGetNumberOfTasksForTopicWork() {
		assertNotNull(statisticsServices.getNumberOfTasksForTopic(sample.getNameOfTestingTopic()));
		assertEquals(statisticsServices.getNumberOfTasksForTopic(sample.getNameOfTestingTopic()), 1);
	}

	@Test
	public void doesMethodGetLatestUserWork() {
		assertNotNull(statisticsServices.getLatestUser());
	}

	@Test
	public void doesMethodGetRegisteredLastWeekWork() {
		assertNotNull(statisticsServices.getRegisteredLastWeek());
	}

}
