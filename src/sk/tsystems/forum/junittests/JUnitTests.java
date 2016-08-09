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

	@BeforeClass
	public static void createTesterUserAndTestingTopic() {
		User testedUser = new User();
		testedUser.setUserName(tester);
		testedUser.setUserPassword("tester");
		testedUser.setBirthDate(null);
		testedUser.setRole("user");
		testedUser.setStatus("pending");
		userServices.addUser(testedUser);
		
		Topic testedTopic = new Topic();
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
}
