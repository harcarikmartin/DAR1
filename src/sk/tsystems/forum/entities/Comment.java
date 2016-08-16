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

@Entity
@Table(name = "Coment")
public class Comment {

	@Id
	@GeneratedValue
	private int commentID;

	@ManyToOne
	private User user;
	
	private Date addedOn;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Task task;

	@Column(name = "coment", nullable = false, length=500)
	private String comment;

	public Comment(String comment, Task task, User user, Date addedOn) {
		this.comment = comment;
		this.task = task;
		this.user = user;
		this.addedOn = addedOn;
	}

	public Comment() {

	}

	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	@Override
	public String toString() {
		return "Comment [commentID=" + commentID + ", user=" + user + ", addedOn=" + addedOn + ", task=" + task
				+ ", comment=" + comment + "]";
	}
	
}
