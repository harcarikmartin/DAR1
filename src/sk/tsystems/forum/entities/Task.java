package sk.tsystems.forum.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Task {

	@Id
	@GeneratedValue
	private int taskID;

	@ManyToOne
	private Topic topic;

	@ManyToOne
	private User user;

	@Column(nullable = false, length=500) 
	private String task;

	@Column(nullable = false) 
	private String taskName;
	
	
	public Task(String taskName, String task, Topic topic, User user) {
		this.taskName = taskName;
		this.topic = topic;
		this.user = user;
		this.task = task;
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
		return task;
	}

	public void setTaskName(String taskName) {
		this.task = taskName;
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
		return "Task [taskID=" + taskID + ", taskName=" + task + ", topic=" + topic + ", user=" + user + "]";
	}

}
