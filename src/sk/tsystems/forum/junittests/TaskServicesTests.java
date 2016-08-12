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

	TopicServices topicServices = new TopicServices();
	UserServices userServices = new UserServices();
	TaskServices taskServices = new TaskServices();
	String nameOfTester = "tester";
	String nameOfTestingTopic = "testing topic";
	String nameOfTestingTask = "testing task";
	User testedUser = new User();
	Topic testedTopic = new Topic();
	Task testedTask = new Task();
	Task concereteTask = new Task();
	int taskID;
	int concreteTaskID;
	
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

	public void getTaskID() {
		List<Task> listOfTestedTasks = new ArrayList<>();
		listOfTestedTasks = taskServices.printTasks(testedTopic.getTopicID());
		concereteTask=listOfTestedTasks.get(0);
		taskID = concereteTask.getTaskID();
	}
	
	public int getConcreteTaskID() {
		List<Task> listOfTestedTasksNumberTwo = new ArrayList<>();
		listOfTestedTasksNumberTwo = taskServices.printTasks(testedTopic.getTopicID());
		concereteTask=listOfTestedTasksNumberTwo.get(1);
		int concreteTaskID = concereteTask.getTaskID();
		return concreteTaskID;
	}

	public void createTestedTask() {
		testedTask.setTaskName(nameOfTestingTask);
		testedTask.setTopic(testedTopic);
		testedTask.setUser(testedUser);
		taskServices.addTaskToDatabase(testedTask);
	}
	
	public void createTestedTaskNumberTwo() {
		Task testedTaskNumberTwo = new Task();
		testedTaskNumberTwo.setTaskName("bonus");
		testedTaskNumberTwo.setTopic(testedTopic);
		testedTaskNumberTwo.setUser(testedUser);
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
		getTaskID();
	}
	
	@After
	public void dropUser() {
		taskServices.removeTask(taskID);
		topicServices.removeTopic(nameOfTestingTopic);
		userServices.dropUser(nameOfTester);
	}
	
	@Test
	// Checks if task "testing task" was created, when @Before was called
	public void doesMethodAddTaskToDatabaseWork(){
		assertNotNull(taskServices.getTask(taskID));
		}
	
	@Test
	// Checks if creating new task, and then deleting concrete task work
	public void doesMethodRemoveTaskWork(){
		createTestedTaskNumberTwo();
		concreteTaskID = getConcreteTaskID();
		taskServices.removeTask(concreteTaskID);
		assertNull(taskServices.getTask(concreteTaskID));
	}

	@Test
	// Checks if after adding new task to table, method printtaska return correct list
	public void doesMethodPrintTasksWork(){
		assertNotNull(taskServices.printTasks(testedTopic.getTopicID()));
	}
	
	@Test
	public void doesMethodGetTask(){
		assertEquals(nameOfTestingTask, taskServices.getTask(taskID).getTaskName());
	}
	
	@Test
	public void doesMethodUpdateTaskWork(){
		
	}
}
