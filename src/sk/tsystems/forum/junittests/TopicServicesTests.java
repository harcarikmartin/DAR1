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
import sk.tsystems.forum.services.UsersTopicsServices;

/**
 * This class contains JUnit Tests which check if all methods in
 * {@link TopicServices} work correctly.
 * 
 * @author karolklescinsky
 */
public class TopicServicesTests {

	private TopicServices topicServices = new TopicServices();
	private UserServices userServices = new UserServices();
	private CreatingTestingSamples sample = new CreatingTestingSamples();
	private UsersTopicsServices usersTopicsServices = new UsersTopicsServices();
	
	/**
	 * This method is called before each test. Calls method createTestedUser
	 * which adds testedUser into the database. Calls method createTestedTopic
	 * which adds testedTopic into the database.
	 */
	@Before
	public void createTesterUserAndTestingTopic() {
		if (topicServices.setPresentTopic(sample.getNameOfTestingTopic()) != null) {
			topicServices.removeTopic(sample.getNameOfTestingTopic());
		}
		if (userServices.setPresentUser(sample.getNameOfTester()) != null) {
			userServices.dropUser(sample.getNameOfTester());
		}
		sample.createTestedUser();
		sample.createTestedTopic();
	}
	
	/**
	 * This method is called after each test. Calls method removeTopic which drops
	 * testedTopic from database. Calls method dropUser which drops testedUser
	 * from database.
	 */
	@After
	public void dropUser() {
		topicServices.removeTopic(sample.getNameOfTestingTopic());
		userServices.dropUser(sample.getNameOfTester());
	}
	
	/**
	 * Checks if specific topic was created earlier under "@Before"
	 * annotation. Checks if value after calling getTopic method is not null.
	 */
	@Test
	public void doesMethodAddTopicToDatabaseWork() {
		// Checks if topic "testing topic" is in database
		assertEquals("testing topic", topicServices.setPresentTopic("testing topic").getTopic());
	}
	
	/**
	 * prints topics from database to listOfTestedTopics.
	 * Secondly, adds one more topic to database and prints to listOfTestedTopics1.
	 * Finally, compares lists, asserting that those do not equal.
	 */
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
	
	/**
	 * Checks two topicsID of type integer, one stored as 0 and another stored in database, 
	 * asserting that they do not equal.
	 */
	@Test
	public void doesMethodGetTopicIDWork() {
		// Checks if topic "testing Topic" was created
		assertNotEquals(0, topicServices.getTopicID(sample.getNameOfTestingTopic()));
	}
	
	/**
	 * Checks two topics of type String, one stored as String in class
	 * {@link CreatingTestingSamples} and another stored in database.
	 */
	@Test
	public void doesMethodGetTopicWork() {
		assertEquals(sample.getNameOfTestingTopic(), sample.getTestedTopic().getTopic());
	}
	
	/**
	 * Checks if topic "testing Topic" is in database
	 */
	@Test
	public void doesMethodSetPresentTopicWork() {
		// Checks if topic "testing Topic" is in database
		assertEquals(sample.getNameOfTestingTopic(),
				topicServices.setPresentTopic(sample.getNameOfTestingTopic()).getTopic());
	}
	
	/**
	 * Checks if topic "Bonus" is in table after calling removeTopic method. Expecting null.
	 */
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
	
	/**
	 * Checks two visibilities, one before calling method updateTopic and
	 * another one after calling method updateTopic. Values should change from
	 * "private" to "public".
	 */
	@Test
	public void doesMethodUpdateTopicWork() {
		// Checks two vissibilities. One before and one after calling
		// updateTopic method
		String vissibilityBeforeUpdate = topicServices.setPresentTopic(sample.getNameOfTestingTopic()).getVisibility();
		topicServices.updateTopic(sample.getNameOfTestingTopic(), sample.getNameOfTestingTopic(), "public");
		String vissibilityAfterUpdate = topicServices.setPresentTopic(sample.getNameOfTestingTopic()).getVisibility();
		assertNotEquals(vissibilityBeforeUpdate, vissibilityAfterUpdate);
	}
	
	
	@Test
	public void doesMethodSetSubscriberWork() {
		int sizeOfListBeforeCallingSetSubscriber = usersTopicsServices.getUsersTopics().size();
		new TopicServices().setSubscriber(topicServices.setPresentTopic("testing topic"),
				userServices.setPresentUser("tester"));
		int sizeOfListAfterCallingSetSubscriber = usersTopicsServices.getUsersTopics().size();
		assertNotEquals(sizeOfListBeforeCallingSetSubscriber, sizeOfListAfterCallingSetSubscriber);
	}
	
	@Test
	public void doesMethodrRemoveSubscriberWork(){
		int sizeOfListBeforeCallingRemoveSubscriber = usersTopicsServices.getUsersTopics().size();
		new TopicServices().setSubscriber(topicServices.setPresentTopic("testing topic"),
				userServices.setPresentUser("tester"));
		new TopicServices().removeSubscriber(topicServices.setPresentTopic("testing topic"),
				userServices.setPresentUser("tester"));
		int sizeOfListAfterCallingRemoveSubscriber = usersTopicsServices.getUsersTopics().size();
		assertEquals(sizeOfListBeforeCallingRemoveSubscriber, sizeOfListAfterCallingRemoveSubscriber);
	}
}