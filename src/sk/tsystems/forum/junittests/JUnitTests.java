package sk.tsystems.forum.junittests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import sk.tsystems.forum.entities.Topic;
import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.services.TopicServices;
import sk.tsystems.forum.services.UserServices;

public class JUnitTests {

	static TopicServices topicServices = new TopicServices();
	static UserServices userServices = new UserServices();
	static String tester = "tester";
	static String testingTopic = "testing topic";
	static User testedUser = new User();
	static Topic testedTopic = new Topic();

	@BeforeClass
	public static void createTesterUserAndTestingTopic() {
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

	@AfterClass
	public static void dropUser() {
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
	public void doesMethodGetTopicIDWork() {
		// Checks if topic "testingTopic" was created
		assertNotEquals(0, topicServices.getTopicID(testingTopic));
	}

	@Test
	public void doesMethodSetPresentTopicWork() {
		// Checks if topic "testingTopic" is in database
		assertNotEquals(null, topicServices.setPresentTopic(testingTopic));
	}

	@Test
	public void doesUpdateTopicMethodWorks() {
		// Checks if method updateTopic works
		topicServices.updateTopic(testingTopic, testingTopic, "public");
		Topic testedTopic1 = new Topic();
		testedTopic1 = topicServices.setPresentTopic(testingTopic);
		assertNotEquals(testedTopic, testedTopic1);
	}

}
