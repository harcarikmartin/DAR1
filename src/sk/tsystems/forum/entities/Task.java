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

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@Override
	public String toString() {
		return "Task [taskID=" + taskID + ", topic=" + topic + ", user=" + user + ", task=" + task + ", taskName="
				+ taskName + "]";
	}

	

}
