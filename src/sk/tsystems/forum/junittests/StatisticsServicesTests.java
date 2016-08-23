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

/**
 * This class contains JUnit Tests which check if all methods in
 * {@link StatisticsServices} work correctly.
 * 
 * @author karolklescinsky
 */
public class StatisticsServicesTests {

	private TopicServices topicServices = new TopicServices();
	private TaskServices taskServices = new TaskServices();
	private CommentServices commentServices = new CommentServices();
	private UserServices userServices = new UserServices();
	private CreatingTestingSamples sample = new CreatingTestingSamples();
	private StatisticsServices statisticsServices = new StatisticsServices();

	/**
	 * This method is called before each test. Calls method createTestedUser
	 * which adds testedUser into the database. Calls method createTestedTopic
	 * which adds testedTopic into the database. Calls method createTestedTask
	 * which adds testedTask into the database. Calls method createTestedComment
	 * which adds testedComment the into database.
	 */
	@Before
	public void createTesterUserAndTestingTopic() {
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
	public void dropUser() {
		commentServices.removeComment(sample.getCommentID());
		taskServices.removeTask(sample.getTaskID());
		topicServices.removeTopic(sample.getNameOfTestingTopic());
		userServices.dropUser(sample.getNameOfTester());
	}

	/**
	 * Checks if return of method getAllUsersCount is not null. Checks if return
	 * of method getAllUsersCount is equal to sum of pending users and confirmed
	 * users.
	 */
	@Test
	public void doesMethodGetAllUsersCountWork() {
		assertNotNull(statisticsServices.getAllUsersCount());
		assertEquals(statisticsServices.getAllUsersCount(),
				userServices.getPendingUsers().size() + userServices.getUsers().size());
	}

	/**
	 * Checks if return of method getAdminsCount is equal to deduction of all
	 * users count and users who are not administrators.
	 */
	@Test
	public void doesMethodGetAdminsCountWork() {
		assertEquals(statisticsServices.getAdminsCount(),
				statisticsServices.getAllUsersCount() - statisticsServices.getUsersCount());
	}

	/**
	 * Checks if return of method getUsersCount is not null. Checks if return of
	 * method getUsersCount is equal to deduction of all users count and
	 * administrators count.
	 */
	@Test
	public void doesMethodGetUsersCountWork() {
		assertNotNull(statisticsServices.getUsersCount());
		assertEquals(statisticsServices.getUsersCount(),
				statisticsServices.getAllUsersCount() - statisticsServices.getAdminsCount());
	}

	/**
	 * Checks if return of method getApprovedCount is not null. Checks if return
	 * of method getApprovedCount is equal to deduction of all users count and
	 * not approved users count.
	 */
	@Test
	public void doesMethodGetApprovedCountWork() {
		assertNotNull(statisticsServices.getApprovedCount());
		assertEquals(statisticsServices.getApprovedCount(),
				statisticsServices.getAllUsersCount() - statisticsServices.getNotApprovedCount());
	}

	/**
	 * Checks if return of method getNotApprovedCount is not null. Checks if
	 * return of method getNotApprovedCount is equal to deduction of all users
	 * count and approved users count.
	 */
	@Test
	public void doesMethodGetNotApprovedCountWork() {
		assertNotNull(statisticsServices.getNotApprovedCount());
		assertEquals(statisticsServices.getNotApprovedCount(),
				statisticsServices.getAllUsersCount() - statisticsServices.getApprovedCount());
	}

	/**
	 * Checks if return of method getTopicsCount is not null. Checks if return
	 * of method getTopicsCount is equal to size of list of all topics.
	 */
	@Test
	public void doesMethodGetTopicsCountWork() {
		assertNotNull(statisticsServices.getTopicsCount());
		assertEquals(statisticsServices.getTopicsCount(), topicServices.printTopics().size());
	}

	/**
	 * Checks if return of method getTasksCount is not null.
	 */
	@Test
	public void doesMethodGetTasksCountWork() {
		assertNotNull(statisticsServices.getTasksCount());
	}
	
	/**
	 * Checks if return of method getCommentsCount is not null.
	 */
	@Test
	public void doesMethodGetCommentsCountWork() {
		assertNotNull(statisticsServices.getCommentsCount());
	}

	/**
	 *	Checks if return of method getPublicTopicsCount is equal to deduction of topics
	 * count and private topics count.	
	 */
	@Test
	public void doesMethodGetPublicTopicsCountWork() {
		assertEquals(statisticsServices.getPublicTopicsCount(),
				statisticsServices.getTopicsCount() - statisticsServices.getPrivateTopicsCount());
	}

	/**
	 *	Checks if return of method getPrivateTopicsCount is equal to deduction of topics
	 * count and public topics count.	
	 */
	@Test
	public void doesMethodGetPrivateTopicsCountWork() {
		assertEquals(statisticsServices.getPrivateTopicsCount(),
				statisticsServices.getTopicsCount() - statisticsServices.getPublicTopicsCount());
	}

	/**
	 * Checks if return of method getMostAnsweredTask is not null.
	 */
	@Test
	public void doesMethodGetMostAnsweredTaskWork() {
		assertNotNull(statisticsServices.getMostAnsweredTask());
	}
	
	/**
	 * Checks if return of method getNumberOfCommentsForMostAnsweredTask is not null.
	 */
	@Test
	public void doesMethodGetNumberOfCommentsForMostAnsweredTaskWork() {
		assertNotNull(statisticsServices.getNumberOfCommentsForMostAnsweredTask());
	}

	/**
	 * Checks if return of method getMostActiveUser is not null.
	 */
	@Test
	public void doesMethodGetMostActiveUserWork() {
		assertNotNull(statisticsServices.getMostActiveUser());
	}

	/**
	 * Checks if return of method getNumberOfCommentsForMostActiveUser is not null.
	 */
	@Test
	public void doesMethodGetNumberOfCommentsForMostActiveUserWork() {
		assertNotNull(statisticsServices.getNumberOfCommentsForMostActiveUser());
	}

	/**
	 * Checks if return of method getNumberOfTasksForTopic is not null.
	 * Checks if count of tasks in topic "tested topic" is equal to 1;
	 */
	@Test
	public void doesMethodGetNumberOfTasksForTopicWork() {
		assertNotNull(statisticsServices.getNumberOfTasksForTopic(sample.getNameOfTestingTopic()));
		assertEquals(statisticsServices.getNumberOfTasksForTopic(sample.getNameOfTestingTopic()), 1);
	}

	/**
	 * Checks if return of method getLatestUser is not null.
	 */
	@Test
	public void doesMethodGetLatestUserWork() {
		assertNotNull(statisticsServices.getLatestUser());
	}

	/**
	 * Checks if return of method getRegisteredLastWeek is not null.
	 */
	@Test
	public void doesMethodGetRegisteredLastWeekWork() {
		assertNotNull(statisticsServices.getRegisteredLastWeek());
	}

}
