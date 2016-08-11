package sk.tsystems.forum.junittests;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;

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
		testedUser.setUserName(nameOfTester);
		testedUser.setUserPassword("tester");
		testedUser.setBirthDate(parseDate());
		testedUser.setRole("user");
		testedUser.setStatus("pending");
		userServices.addUser(testedUser);

		testedTopic.setTopic(nameOfTestingTopic);
		testedTopic.setVisibility("private");
		testedTopic.setCreator(testedUser);
		topicServices.addTopicToDatabase(testedTopic);

		testedTask.setTaskName(nameOfTestingTask);
		testedTask.setTopic(testedTopic);
		testedTask.setUser(testedUser);
		taskServices.addTaskToDatabase(testedTask);
	}

	// TODO: Not done yet. Under construction.
	@After
	public void dropUser() {
		topicServices.removeTopic(nameOfTestingTopic);
		userServices.dropUser(nameOfTester);
		// taskServices.removeTask(taskServices.getTask());
	}

}
