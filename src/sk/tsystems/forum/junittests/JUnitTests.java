package sk.tsystems.forum.junittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sk.tsystems.forum.entities.Topic;
import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.services.TopicServices;
import sk.tsystems.forum.services.UserServices;

public class JUnitTests {

	TopicServices topicServices = new TopicServices();
	UserServices userServices = new UserServices();
	String tester = "tester";
	String testingTopic = "testing topic";
	User testedUser = new User();
	Topic testedTopic = new Topic();

	@Before
	public void createTesterUserAndTestingTopic() {
		testedUser.setUserName(tester);
		testedUser.setUserPassword("tester");
		testedUser.setBirthDate(null);
		testedUser.setRole("user");
		testedUser.setStatus("pending");
		userServices.addUser(testedUser);

		testedTopic.setTopic(testingTopic);
		testedTopic.setVisibility("private");
		testedTopic.setCreator(testedUser);
		topicServices.addTopicToDatabase(testedTopic);
	}

	@After
	public void dropUser() {
		topicServices.removeTopic(testingTopic);
		userServices.dropUser(tester);
	}

	@Test
	public void doesMethodIsPasswordCorrectWork() {
		// Good password
		assertTrue(userServices.isPasswordCorrect(tester, "tester"));
		// bad password
		assertFalse(userServices.isPasswordCorrect(tester, "testerios"));
	}

	@Test
	public void deosMethodIsUserApprovedWork() {
		// Checks if is user "tester" approved
		assertFalse(userServices.isUserApproved(tester));
	}

	@Test
	public void doesmethodGetUserIDWork() {
		// Checks if user "tester" was created
		assertNotEquals(0, userServices.getUserID(tester));
	}

	@Test
	public void doesMethodSetPresentUserWork() {
		// Checks if user "tester" is in database
		assertNotEquals(null, userServices.setPresentUser(tester));
	}

	@Test
	public void doesMethodPrintTopicsWork() {
		// Firstly, prints topics from database to listOfTestedTopics. Secondly, adds one more topic to database and prints to listOfTestedTopics1. Finally, compares lists. 
		List<Topic> listOfTestedTopics = new ArrayList<>();
		listOfTestedTopics = topicServices.printTopics();
		topicServices.addTopicToDatabase(new Topic(null, null, null, "Bonus"));
		List<Topic> listOfTestedTopics1 = new ArrayList<>();
		listOfTestedTopics1 = topicServices.printTopics();
		assertNotEquals(listOfTestedTopics, listOfTestedTopics1);
		topicServices.removeTopic("Bonus");
	}

	@Test
	public void doesMethodGetTopicIDWork() {
		// Checks if topic "testingTopic" was created
		assertNotEquals(0, topicServices.getTopicID(testingTopic));
	}

	@Test
	public void doesMethodSetPresentTopicWork() {
		// Checks if topic "testingTopic" is in database
		assertNotEquals(null, topicServices.setPresentTopic(testingTopic));
	}

	// TODO: Bazmeg nefunguje to more
	@Test
	public void doesUpdateTopicMethodWorks() {
		// Checks if method updateTopic works
		System.out.println(testedTopic);
		Topic testedTopic1 = new Topic();
		testedTopic1 = new TopicServices().setPresentTopic(testingTopic);
		Topic testedTopic2 = new Topic();
		new TopicServices().updateTopic(testingTopic, testingTopic, null);
		System.out.println(testedTopic);
		testedTopic2 = new TopicServices().setPresentTopic(testingTopic);
		System.out.println(testedTopic1);
		System.out.println(testedTopic2);
		System.out.println(testedTopic);
		assertEquals(testedTopic1, testedTopic2);

	}

}
