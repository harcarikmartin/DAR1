package sk.tsystems.forum.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Task {

	@Id
	@GeneratedValue
	private int taskID;

	private String taskName;
	private Topic topic;
	private User user;

	public Task(String taskName, Topic topic, User user) {
		this.taskName = taskName;
		this.topic = topic;
		this.user = user;
	}

	public Task() {

	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Task [taskID=" + taskID + ", taskName=" + taskName + ", topic=" + topic + ", user=" + user + "]";
	}

}
