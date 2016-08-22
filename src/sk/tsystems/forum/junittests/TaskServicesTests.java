package sk.tsystems.forum.junittests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sk.tsystems.forum.services.TaskServices;
import sk.tsystems.forum.services.TopicServices;
import sk.tsystems.forum.services.UserServices;

public class TaskServicesTests {

	private TopicServices topicServices = new TopicServices();
	private TaskServices taskServices = new TaskServices();
	private UserServices userServices = new UserServices();
	private CreatingTestingSamples sample = new CreatingTestingSamples();

	@Before
	public void createTesterUserAndTestingTopic() {
		sample.createTestedUser();
		sample.createTestedTopic();
		sample.createTestedTask();
	}

	@After
	public void dropUser() {
		taskServices.removeTask(sample.getTaskID());
		topicServices.removeTopic(sample.getNameOfTestingTopic());
		userServices.dropUser(sample.getNameOfTester());
	}

	@Test
	public void doesMethodAddTaskToDatabaseWork() {
		// Checks if task "testing task" was created, when @Before was called
		assertNotNull(taskServices.getTask(sample.getTaskID()));
	}

	@Test
	public void doesMethodRemoveTaskWork() {
		// Checks if creating new task, and then deleting concrete task work
		sample.createTestedTaskNumberTwo();
		int idIfTestedTaskNumberTwo = sample.getTaskID();
		taskServices.removeTask(idIfTestedTaskNumberTwo);
		assertNull(taskServices.getTask(idIfTestedTaskNumberTwo));
	}

	@Test
	public void doesMethodGetTopicWork() {
		assertEquals(sample.getNameOfTestingTopic(), sample.getTestedTopic().getTopic());
	}

	@Test
	public void doesMethodPrintTasksWork() {
		// Checks if after adding new task to table, method printtaska return
		// correct list
		assertNotNull(taskServices.printTasks(sample.getTestedTopic().getTopicID()));
	}

	@Test
	public void doesMethodGetTaskWork() {
		// Checks name in tasks using getTask method
		assertEquals(sample.getNameOfTestingTask(), taskServices.getTask(sample.getTaskID()).getTaskName());
	}

	@Test
	public void doesMethodUpdateTaskWork() {
		// Checks task's 'taskBody' before and after update
		String taskBodyBeforeUpdate = taskServices.getTask(sample.getTaskID()).getTask();
		taskServices.updateTask(sample.getTaskID(), "New task nameeee", "New task bodyyyy");
		String taskBodyAfterUpdate = taskServices.getTask(sample.getTaskID()).getTask();
		assertNotEquals(taskBodyAfterUpdate, taskBodyBeforeUpdate);
	}
}
