package sk.tsystems.forum.junittests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sk.tsystems.forum.services.TaskServices;
import sk.tsystems.forum.services.TopicServices;
import sk.tsystems.forum.services.UserServices;

/**
 * This class contains JUnit Tests which check if all methods in
 * {@link TaskServices} work correctly.
 * 
 * @author karolklescinsky
 */
public class TaskServicesTests {

	private TopicServices topicServices = new TopicServices();
	private TaskServices taskServices = new TaskServices();
	private UserServices userServices = new UserServices();
	private CreatingTestingSamples sample = new CreatingTestingSamples();
	
	/**
	 * This method is called before each test. Calls method createTestedUser
	 * which adds testedUser into the database. Calls method createTestedTopic
	 * which adds testedTopic into the database. Calls method createTestedTask
	 * which adds testedTask into the database.
	 */
	@Before
	public void createTesterUserAndTestingTopic() {
		if (topicServices.getPresentTopic(sample.getNameOfTestingTopic()) != null) {
			topicServices.removeTopic(sample.getNameOfTestingTopic());
		}
		if (userServices.getPresentUser(sample.getNameOfTester()) != null) {
			userServices.dropUser(sample.getNameOfTester());
		}
		sample.createTestedUser();
		sample.createTestedTopic();
		sample.createTestedTask();
	}
	
	/**
	 * This method is called after each test. Calls method removeTask which drops
	 * testedTask from database. Calls method removeTopic which drops
	 * testedTopic from database. Calls method dropUser which drops testedUser
	 * from database.
	 */
	@After
	public void dropUser() {
		taskServices.removeTask(sample.getTaskID());
		topicServices.removeTopic(sample.getNameOfTestingTopic());
		userServices.dropUser(sample.getNameOfTester());
	}
	
	/**
	 * Checks if specific task was created earlier under "@Before"
	 * annotation. Checks if value after calling getTask method is not null.
	 */
	@Test
	public void doesMethodAddTaskToDatabaseWork() {
		// Checks if task "testing task" was created, when @Before was called
		assertNotNull(taskServices.getTask(sample.getTaskID()));
	}
	
	/**
	 * Creates specific task, then this test removes that specific task
	 * and checks if that specific task is in database. Null expected.
	 */
	@Test
	public void doesMethodRemoveTaskWork() {
		// Checks if creating new task, and then deleting concrete task work
		sample.createTestedTaskNumberTwo();
		int idIfTestedTaskNumberTwo = sample.getTaskID();
		taskServices.removeTask(idIfTestedTaskNumberTwo);
		assertNull(taskServices.getTask(idIfTestedTaskNumberTwo));
	}
	
	/**
	 * Checks if list of tasks in specific topic is not null.
	 */
	@Test
	public void doesMethodPrintTasksWork() {
		// Checks if after adding new task to table, method printtaska return
		// correct list
		assertNotNull(taskServices.printTasks(sample.getTestedTopic().getTopicID()));
	}
	
	/**
	 * Checks two tasks of type String, one stored as String in class
	 * {@link CreatingTestingSamples} and another stored in database.
	 */
	@Test
	public void doesMethodGetTaskWork() {
		// Checks name in tasks using getTask method
		assertEquals(sample.getNameOfTestingTask(), taskServices.getTask(sample.getTaskID()).getTaskName());
	}
	
	/**
	 * Checks two Strings, one string before calling method updateTask and
	 * another one after calling method updateTask. Values should change from
	 * "testing task" and "testing task testing task" to "New task nameee" and "New task bodyyyy".
	 */
	@Test
	public void doesMethodUpdateTaskWork() {
		// Checks task's 'taskBody' before and after update
		String taskBodyBeforeUpdate = taskServices.getTask(sample.getTaskID()).getTask();
		taskServices.updateTask(sample.getTaskID(), "New task nameeee", "New task bodyyyy");
		String taskBodyAfterUpdate = taskServices.getTask(sample.getTaskID()).getTask();
		assertNotEquals(taskBodyAfterUpdate, taskBodyBeforeUpdate);
	}
}
