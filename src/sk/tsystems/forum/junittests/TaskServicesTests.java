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

import sk.tsystems.forum.entities.Task;
import sk.tsystems.forum.entities.Topic;
import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.services.TaskServices;
import sk.tsystems.forum.services.TopicServices;
import sk.tsystems.forum.services.UserServices;

public class TaskServicesTests {

	private TopicServices topicServices = new TopicServices();
	private UserServices userServices = new UserServices();
	private TaskServices taskServices = new TaskServices();
	private String nameOfTester = "tester";
	private String nameOfTestingTopic = "testing topic";
	private String nameOfTestingTask = "testing task";
	private User testedUser = new User();
	private Topic testedTopic = new Topic();
	private Task testedTask = new Task();
	private Task testedTaskNumberTwo = new Task();
	private Task concereteTask = new Task();

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

	public int getTaskID() {
		List<Task> listOfTestedTasks = new ArrayList<>();
		listOfTestedTasks = taskServices.printTasks(testedTopic.getTopicID());
		int indexOfTask = listOfTestedTasks.size() - 1;
		concereteTask = listOfTestedTasks.get(indexOfTask);
		int taskID = concereteTask.getTaskID();
		return taskID;
	}

	public void createTestedTask() {
		testedTask.setTaskName(nameOfTestingTask);
		testedTask.setTopic(testedTopic);
		testedTask.setUser(testedUser);
		testedTask.setTask("testing task testing task");
		taskServices.addTaskToDatabase(testedTask);
	}

	public void createTestedTaskNumberTwo() {
		testedTaskNumberTwo.setTaskName("bonus");
		testedTaskNumberTwo.setTopic(testedTopic);
		testedTaskNumberTwo.setUser(testedUser);
		testedTaskNumberTwo.setTask("bonus bonus");
		taskServices.addTaskToDatabase(testedTaskNumberTwo);
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

	@Before
	public void createTesterUserAndTestingTopic() {
		createTestedUser();
		createTestedTopic();
		createTestedTask();
	}

	@After
	public void dropUser() {
		taskServices.removeTask(getTaskID());
		topicServices.removeTopic(nameOfTestingTopic);
		userServices.dropUser(nameOfTester);
	}

	@Test
	// Checks if task "testing task" was created, when @Before was called
	public void doesMethodAddTaskToDatabaseWork() {
		assertNotNull(taskServices.getTask(getTaskID()));
	}

	@Test
	// Checks if creating new task, and then deleting concrete task work
	public void doesMethodRemoveTaskWork() {
		createTestedTaskNumberTwo();
		int idIfTestedTaskNumberTwo = getTaskID();
		taskServices.removeTask(idIfTestedTaskNumberTwo);
		assertNull(taskServices.getTask(idIfTestedTaskNumberTwo));
	}

	@Test
	// Checks if after adding new task to table, method printtaska return
	// correct list
	public void doesMethodPrintTasksWork() {
		assertNotNull(taskServices.printTasks(testedTopic.getTopicID()));
	}

	@Test
	// Checks name in tasks using getTask method
	public void doesMethodGetTask() {
		assertEquals(nameOfTestingTask, taskServices.getTask(getTaskID()).getTaskName());
	}

	@Test
	// Checks task's 'taskBody' before and after update
	public void doesMethodUpdateTaskWork() {
		String taskBodyBeforeUpdate = taskServices.getTask(getTaskID()).getTask();
		taskServices.updateTask(getTaskID(), nameOfTestingTask, "New name", testedTopic);
		String taskBodyAfterUpdate = taskServices.getTask(getTaskID()).getTask();
		assertNotEquals(taskBodyAfterUpdate, taskBodyBeforeUpdate);
	}
}
