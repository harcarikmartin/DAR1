package sk.tsystems.forum.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * This class represents the {@link Entity} required for creation of the
 * structure in the database using JPA connection. Class is equivalent to the
 * table in database. Encloses fields that are represented as a columns in this
 * table, instances of the class represent rows in the table in the database.
 * 
 * @author karolklescinsky
 */
@Entity
public class Task {

	/**
	 * Represents primary key of instances of this entity in the database table,
	 * generated automatically from database sequence.
	 */
	@Id
	@GeneratedValue
	private int taskID;

	/**
	 * Represents parent Topic of concrete Task.
	 */
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Topic topic;

	/**
	 * Represents creator {@link User}.
	 */
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	/**
	 * Represents Task's body.
	 */
	@Column(nullable = false, length = 500)
	private String task;

	/**
	 * Represents Task's name.
	 */
	@Column(nullable = false)
	private String taskName;

	/**
	 * Parametric constructor of {@link Task} class.
	 * 
	 * @param taskName @see field taskName of {@link Task} class
	 * @param task @see field task of {@link Task} class
	 * @param topic @see field topic of {@link Task} class
	 * @param user @see field user of {@link Task} class
	 */
	public Task(String taskName, String task, Topic topic, User user) {
		this.taskName = taskName;
		this.topic = topic;
		this.user = user;
		this.task = task;
	}
	
	/**
	 * Non-Parametric constructor of {@link Task} class.
	 */
	public Task() {

	}

	/**
	 * Gets the value of the field taskID of the instance of the {@link Task} class
	 * 
	 * @return int value of field taskID
	 */
	public int getTaskID() {
		return taskID;
	}

	/**
	 * Sets the value of the field taskID of the instance of the {@link Task} class to the value of method's parameter
	 * 
	 * @param taskID int value of field taskID
	 */
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	/**
	 * Gets the value of the field topic of the instance of the {@link Topic} class
	 * 
	 * @return specific topic of the {@link Topic}
	 */
	public Topic getTopic() {
		return topic;
	}

	/**
	 * Sets the value of the field topic of the instance of the {@link Topic} class to the value of method's parameter
	 * 
	 * @param topic specific topic of the {@link Topic} class
	 */
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	/**
	 * Gets the value of the field user of the instance of the {@link User} class
	 * 
	 * @return specific user of the {@link User} class
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the value of the field user of the instance of the {@link user} class to the value of method's parameter
	 * 
	 * @param user specific user of the {@link User} class
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the value of the field task of the instance of the {@link Task} class
	 * 
	 * @return String value of the field task
	 */
	public String getTask() {
		return task;
	}

	/**
	 * Sets the value of the field task of the instance of the {@link Task} class
	 * 
	 * @param task String value of the field task
	 */
	public void setTask(String task) {
		this.task = task;
	}

	/**
	 * Gets the value of the field taskName of the instance of the {@link Task} class
	 * 
	 * @return String value of the field taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * Sets the value of the field taskName of the instance of the {@link Task} class
	 * 
	 * @param taskName String value of the field taskName
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * Overriden method toString, useful for string representation of the instance of the {@link Task} class
	 */
	@Override
	public String toString() {
		return "Task [taskID=" + taskID + ", topic=" + topic + ", user=" + user + ", task=" + task + ", taskName="
				+ taskName + "]";
	}

}
