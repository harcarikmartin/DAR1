package sk.tsystems.forum.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Coment")
public class Comment {

	@Id
	@GeneratedValue
	private int commentID;

	@ManyToOne
	private User user;

	@ManyToOne
	private Task task;

	@Column(name = "coment", nullable = false)
	private String comment;

	public Comment(String comment, Task task, User user) {
		this.comment = comment;
		this.task = task;
		this.user = user;
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

	@Override
	public String toString() {
		return "Comment [commentID=" + commentID + ", comment=" + comment + ", task=" + task + ", user=" + user + "]";
	}

}
