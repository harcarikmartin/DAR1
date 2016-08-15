package sk.tsystems.forum.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sk.tsystems.forum.entities.Comment;
import sk.tsystems.forum.entities.Task;

import sk.tsystems.forum.services.jpahelper.JpaHelper;

public class CommentServices {

	public void addCommentToDatabase(Comment comment) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(comment);
		JpaHelper.commitTransaction();
	}

	public List<Comment> printComments(int taskID) {
		List<Comment> listOfComments= new ArrayList<>();
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select c from Comment c where c.task=:task");
		query.setParameter("task", getTask(taskID));
		listOfComments = query.getResultList();
		return listOfComments;
	}
	
	public void removeComment(int commentID) {
		Comment comment = getComment(commentID);
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.remove(comment);
		JpaHelper.commitTransaction();
	}

	public Comment getComment(int commentID) {
		if (commentID > 0) {
			EntityManager em = JpaHelper.getEntityManager();
			return em.find(Comment.class, commentID);
		} else {
			return null;
		}
	}

	public void updateComment(int commentID, String newComment, Task task) {
		Comment comment = new CommentServices().getComment(commentID);
		JpaHelper.beginTransaction();
		comment.setComment(newComment);
		comment.setTask(task);
		JpaHelper.commitTransaction();
	}
	
	private Task getTask(int taskID) {
		if (taskID > 0) {
			EntityManager em = JpaHelper.getEntityManager();
			return em.find(Task.class, taskID);
		} else {
			return null;
		}
	}

}
