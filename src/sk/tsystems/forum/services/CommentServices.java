package sk.tsystems.forum.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sk.tsystems.forum.entities.Comment;
import sk.tsystems.forum.entities.Task;
import sk.tsystems.forum.entities.Topic;
import sk.tsystems.forum.services.jpahelper.JpaHelper;

/**
 * This class contains methods needed for adding, removing and retrieving information about stored data of 
 * {@link Comment} class from the database.
 * 
 * @author martinharcarik
 *
 */
public class CommentServices {
	
	/**
	 * Adds the instance of {@link Comment} class into database. Instance of Comment is sent as a parameter.
	 * 
	 * @param comment is the instance of class Comment that needs to be stored in database.
	 */
	public void addCommentToDatabase(Comment comment) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(comment);
		JpaHelper.commitTransaction();
	}
	
	/**
	 * Gets the list of all instances of {@link Comment} class stored in the database for the specified instance 
	 * of the {@link Task} class. 
	 * 
	 * @param taskID value of 'taskID' property of Task class needed to identify the instance of the Task class
	 * to get the list of instances of Comment class for
	 * @return list of all instances of the Comment class from database for specified instance of Task class
	 */
	public List<Comment> printComments(int taskID) {
		List<Comment> listOfComments= new ArrayList<>();
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select c from Comment c where c.task=:task order by c.addedOn asc");
		query.setParameter("task", new TaskServices().getTask(taskID));
		listOfComments = query.getResultList();
		return listOfComments;
	}
	
	/**
	 * Removes the instance of the {@link Comment} class with specified property 'commentID' from database
	 * 
	 * @param commentID is value of 'commentID' property of the Comment class. Needed to retrieve the specified 
	 * instance of the Comment class.
	 */
	public void removeComment(int commentID) {
		Comment comment = getComment(commentID);
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.remove(comment);
		JpaHelper.commitTransaction();
	}
	
	/**
	 * Gets the instance of the {@link Comment} class from the database identified by the value of the property 
	 * 'commentID'.
	 * 
	 * @param commentID  is value of 'commentID' property of the Comment class
	 * @return the instance of Comment class with specified value of it's property 'commentID' or null, if the 
	 * specified instance is not present in the database
	 */
	public Comment getComment(int commentID) {
		if (commentID > 0) {
			EntityManager em = JpaHelper.getEntityManager();
			return em.find(Comment.class, commentID);
		} else {
			return null;
		}
	}
	
	/**
	 * Sets the property 'comment' of the specified instance of the {@link Comment} class and stores 
	 * this instance into the database. 
	 * 
	 * @param commentID value of the property 'commentID' stored in the database, used to retrieve the instance of the Comment 
	 * class from the database before updating it's properties
	 * @param newComment new value of the property 'comment' of the Comment class to be stored in the database
	 */
	public void updateComment(int commentID, String newComment) {
		Comment comment = new CommentServices().getComment(commentID);
		JpaHelper.beginTransaction();
		comment.setComment(newComment);
		JpaHelper.commitTransaction();
	}
}
