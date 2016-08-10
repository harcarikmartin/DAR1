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
	String nameOfTester = "tester";
	String nameOfTestingTopic = "testing topic";
	User testedUser = new User();
	Topic testedTopic = new Topic();

	@Before
	public void createTesterUserAndTestingTopic() {
		testedUser.setUserName(nameOfTester);
		testedUser.setUserPassword("tester");
		testedUser.setBirthDate(null);
		testedUser.setRole("user");
		testedUser.setStatus("pending");
		userServices.addUser(testedUser);

		testedTopic.setTopic(nameOfTestingTopic);
		testedTopic.setVisibility("private");
		testedTopic.setCreator(testedUser);
		topicServices.addTopicToDatabase(testedTopic);
	}

	@After
	public void dropUser() {
		topicServices.removeTopic(nameOfTestingTopic);
		userServices.dropUser(nameOfTester);
	}

	@Test
	public void doesMethodAddUserWork() {
		// Checks if user "tester" is in database
		assertEquals("tester", userServices.setPresentUser(nameOfTester).getUserName());
	}

	@Test
	public void doesMethodApproveUserWork(){
		// Compares users status before and after approveUser method.
		String statusBeforeUpdate = userServices.setPresentUser(nameOfTester).getStatus();
		userServices.approveUser(nameOfTester);
		String statusAfterUpdate = userServices.setPresentUser(nameOfTester).getStatus();
		assertNotEquals(statusBeforeUpdate, statusAfterUpdate);
	}
	
	@Test
	public void doesMethodIsPasswordCorrectWork() {
		// Good password
		assertTrue(userServices.isPasswordCorrect(nameOfTester, "tester"));
		// bad password
		assertFalse(userServices.isPasswordCorrect(nameOfTester, "testerios"));
	}

	@Test
	public void deosMethodIsUserApprovedWork() {
		// Checks if is user "tester" approved
		assertFalse(userServices.isUserApproved(nameOfTester));
	}

	@Test
	public void doesmethodGetUserIDWork() {
		// Checks if user "tester" was created
		assertNotEquals(0, userServices.getUserID(nameOfTester));
	}

	@Test
	public void doesMethodSetPresentUserWork() {
		// Checks if user "tester" is in database
		assertEquals("tester", userServices.setPresentUser(nameOfTester).getUserName());
	}

	@Test
	public void doesMethodAddTopicToDatabase() {
		// Checks if topic "testing topic" is in database
		assertEquals("testing topic", topicServices.setPresentTopic("testing topic").getTopic());
	}

	@Test
	public void doesMethodPrintTopicsWork() {
		// Firstly, prints topics from database to listOfTestedTopics.
		// Secondly, adds one more topic to database and prints to
		// listOfTestedTopics1.
		// Finally, compares lists.
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
		// Checks if topic "testing Topic" was created
		assertNotEquals(0, topicServices.getTopicID(nameOfTestingTopic));
	}

	@Test
	public void doesMethodSetPresentTopicWork() {
		// Checks if topic "testing Topic" is in database
		assertEquals(nameOfTestingTopic, topicServices.setPresentTopic(nameOfTestingTopic).getTopic());
	}

	@Test
	public void doesUpdateTopicMethodWorks() {
		// Checks if method updateTopic works
		String vissibilityBeforeUpdate = new TopicServices().setPresentTopic(nameOfTestingTopic).getVisibility();
		new TopicServices().updateTopic(nameOfTestingTopic, nameOfTestingTopic, null);
		String vissibilityAfterUpdate = new TopicServices().setPresentTopic(nameOfTestingTopic).getVisibility();
		assertNotEquals(vissibilityBeforeUpdate, vissibilityAfterUpdate);
	}

}
