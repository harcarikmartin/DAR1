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

import sk.tsystems.forum.entities.Topic;
import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.services.TopicServices;
import sk.tsystems.forum.services.UserServices;

public class TopicServicesTests {

	TopicServices topicServices = new TopicServices();
	UserServices userServices = new UserServices();
	String nameOfTester = "tester";
	String nameOfTestingTopic = "testing topic";
	User testedUser = new User();
	Topic testedTopic = new Topic();

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

	@Before
	public void createTesterUserAndTestingTopic() {
		createTestedUser();
		createTestedTopic();
	}

	public void createTestedTopic() {
		testedTopic.setTopic(nameOfTestingTopic);
		testedTopic.setVisibility("private");
		testedTopic.setCreator(testedUser);
		topicServices.addTopicToDatabase(testedTopic);
	}

	public void createTestedUser() {
		testedUser.setUserName(nameOfTester);
		testedUser.setUserPassword("tester");
		testedUser.setBirthDate(parseDate());
		testedUser.setRole("user");
		testedUser.setStatus("pending");
		userServices.addUser(testedUser);
	}

	@After
	public void dropUser() {
		topicServices.removeTopic(nameOfTestingTopic);
		userServices.dropUser(nameOfTester);
	}

	@Test
	public void doesMethodAddTopicToDatabaseWork() {
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

		Topic testedTopicNumberTwo = new Topic();
		testedTopicNumberTwo.setTopic("Bonus");
		testedTopicNumberTwo.setVisibility("private");
		testedTopicNumberTwo.setCreator(testedUser);
		topicServices.addTopicToDatabase(testedTopicNumberTwo);

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
	public void doesMethodUpdateTopicMethodWork() {
		// Checks if method updateTopic works
		String vissibilityBeforeUpdate = new TopicServices().setPresentTopic(nameOfTestingTopic).getVisibility();
		new TopicServices().updateTopic(nameOfTestingTopic, nameOfTestingTopic, "public");
		String vissibilityAfterUpdate = new TopicServices().setPresentTopic(nameOfTestingTopic).getVisibility();
		assertNotEquals(vissibilityBeforeUpdate, vissibilityAfterUpdate);
	}

	@Test
	public void doesMethodRemoveTopicWork() {
		// Checks if topic "Bonus" is in table after calling removeTopic method
		Topic testedTopicNumberTwo = new Topic();
		testedTopicNumberTwo.setTopic("Bonus");
		testedTopicNumberTwo.setVisibility("private");
		testedTopicNumberTwo.setCreator(testedUser);
		topicServices.addTopicToDatabase(testedTopicNumberTwo);
		topicServices.removeTopic("Bonus");
		assertNull(topicServices.setPresentTopic("Bonus"));
	}

	@Test
	public void doesMethodUpdateTopicWork() {
		// Checks two vissibilities. One before and one after calling
		// updateTopic method
		String vissibilityBeforeUpdate = topicServices.setPresentTopic(nameOfTestingTopic).getVisibility();
		topicServices.updateTopic(nameOfTestingTopic, nameOfTestingTopic, "public");
		String vissibilityAfterUpdate = topicServices.setPresentTopic(nameOfTestingTopic).getVisibility();
		assertNotEquals(vissibilityBeforeUpdate, vissibilityAfterUpdate);
	}
}
