package sk.tsystems.forum.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "Coment")
public class Comment {

	/**
	 * Represents primary key of instances of this entity in the database table,
	 * generated automatically from database sequence.
	 */
	@Id
	@GeneratedValue
	private int commentID;

	/**
	 * Represents creator {@link User}.
	 */
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	/**
	 * Represents date of creation.
	 */
	private Date addedOn;

	/**
	 * Represents parent task of concrete Comment.
	 */
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Task task;
	
	/**
	 * Represents body of comment.
	 */
	@Column(name = "coment", nullable = false, length=500)
	private String comment;

	/**
	 * Parametric constructor of {@link Comment} class.
	 * 
	 * @param comment @see field comment of {@link Comment} class
	 * @param task @see field task of {@link Comment} class
	 * @param user @see field user of {@link Comment} class
	 * @param addedOn @see field addedOn of {@link Comment} class
	 */
	public Comment(String comment, Task task, User user, Date addedOn) {
		this.comment = comment;
		this.task = task;
		this.user = user;
		this.addedOn = addedOn;
	}

	/**
	 * Non-Parametric constructor of {@link Comment} class.
	 */
	public Comment() {

	}

	/**
	 * Gets the value of the field commentID of the instance of the {@link Comment} class
	 * 
	 * @return int value of field commentID
	 */
	public int getCommentID() {
		return commentID;
	}

	/**
	 * Sets the value of the field commentID of the instance of the {@link Comment} class to the value of method's parameter
	 * 
	 * @param commentID int value of field commentID
	 */
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	/**
	 * Gets the value of the field comment of the instance of the {@link Comment} class
	 * 
	 * @return String value of the field comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the value of the field comment of the instance of the {@link Comment} class
	 * 
	 * @param comment String value of the field comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Gets the value of the field task of the instance of the {@link Task} class
	 * 
	 * @return specific task of the {@link Task}
	 */
	public Task getTask() {
		return task;
	}

	/**
	 * Sets the value of the field task of the instance of the {@link Task} class to the value of method's parameter
	 * 
	 * @param task specific task of the {@link Task} class
	 */
	public void setTask(Task task) {
		this.task = task;
	}

	/**
	 * Gets the value of the field user of the instance of the {@link User} class
	 * 
	 * @return specific user of the {@link User}
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the value of the field user of the instance of the {@link User} class to the value of method's parameter
	 * 
	 * @param user specific user of the {@link User} class
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the value of the field addedOn of the instance of the {@link Date} class
	 * 
	 * @return specific date of the {@link Date}
	 */
	public Date getAddedOn() {
		return addedOn;
	}

	/**
	 * Sets the value of the field addedOn of the instance of the {@link Date} class to the value of method's parameter
	 * 
	 * @param addedOn specific addedOn of the {@link Date} class
	 */
	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	/**
	 * Overriden method toString, useful for string representation of the instance of the {@link Comment} class
	 */
	@Override
	public String toString() {
		return "Comment [commentID=" + commentID + ", user=" + user + ", addedOn=" + addedOn + ", task=" + task
				+ ", comment=" + comment + "]";
	}
	
}
