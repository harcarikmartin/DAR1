package sk.tsystems.forum.junittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sk.tsystems.forum.entities.Topic;
import sk.tsystems.forum.services.TopicServices;
import sk.tsystems.forum.services.UserServices;

public class TopicServicesTests {

	private TopicServices topicServices = new TopicServices();
	private UserServices userServices = new UserServices();
	private CreatingTestingSamples sample = new CreatingTestingSamples();

	@Before
	public void createTesterUserAndTestingTopic() {
		sample.createTestedUser();
		sample.createTestedTopic();
	}

	@After
	public void dropUser() {
		topicServices.removeTopic(sample.getNameOfTestingTopic());
		userServices.dropUser(sample.getNameOfTester());
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
		testedTopicNumberTwo.setCreator(sample.getTestedUser());
		topicServices.addTopicToDatabase(testedTopicNumberTwo);
		List<Topic> listOfTestedTopics1 = new ArrayList<>();
		listOfTestedTopics1 = topicServices.printTopics();
		assertNotEquals(listOfTestedTopics, listOfTestedTopics1);
		topicServices.removeTopic("Bonus");
	}

	@Test
	public void doesMethodGetTopicIDWork() {
		// Checks if topic "testing Topic" was created
		assertNotEquals(0, topicServices.getTopicID(sample.getNameOfTestingTopic()));
	}

	@Test
	public void doesMethodSetPresentTopicWork() {
		// Checks if topic "testing Topic" is in database
		assertEquals(sample.getNameOfTestingTopic(), topicServices.setPresentTopic(sample.getNameOfTestingTopic()).getTopic());
	}

	@Test
	public void doesMethodUpdateTopicMethodWork() {
		// Checks if method updateTopic works
		String vissibilityBeforeUpdate = new TopicServices().setPresentTopic(sample.getNameOfTestingTopic()).getVisibility();
		new TopicServices().updateTopic(sample.getNameOfTestingTopic(), sample.getNameOfTestingTopic(), "public");
		String vissibilityAfterUpdate = new TopicServices().setPresentTopic(sample.getNameOfTestingTopic()).getVisibility();
		assertNotEquals(vissibilityBeforeUpdate, vissibilityAfterUpdate);
	}

	@Test
	public void doesMethodRemoveTopicWork() {
		// Checks if topic "Bonus" is in table after calling removeTopic method
		Topic testedTopicNumberTwo = new Topic();
		testedTopicNumberTwo.setTopic("Bonus");
		testedTopicNumberTwo.setVisibility("private");
		testedTopicNumberTwo.setCreator(sample.getTestedUser());
		topicServices.addTopicToDatabase(testedTopicNumberTwo);
		topicServices.removeTopic("Bonus");
		assertNull(topicServices.setPresentTopic("Bonus"));
	}

	@Test
	public void doesMethodUpdateTopicWork() {
		// Checks two vissibilities. One before and one after calling
		// updateTopic method
		String vissibilityBeforeUpdate = topicServices.setPresentTopic(sample.getNameOfTestingTopic()).getVisibility();
		topicServices.updateTopic(sample.getNameOfTestingTopic(), sample.getNameOfTestingTopic(), "public");
		String vissibilityAfterUpdate = topicServices.setPresentTopic(sample.getNameOfTestingTopic()).getVisibility();
		assertNotEquals(vissibilityBeforeUpdate, vissibilityAfterUpdate);
	}
}
