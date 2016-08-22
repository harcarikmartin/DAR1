package sk.tsystems.forum.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sk.tsystems.forum.entities.Task;
import sk.tsystems.forum.entities.Topic;

import sk.tsystems.forum.services.jpahelper.JpaHelper;

/**
 * This class contains methods needed for adding, removing and retrieving information about stored data of 
 * {@link Task} class from the database.
 * 
 * @author martinharcarik
 *
 */
public class TaskServices {
	
	/**
	 * Adds the instance of {@link Task} class into database. Instance of Task is sent as a parameter.
	 * 
	 * @param task is the instance of class Task that needs to be stored in database.
	 */
	public void addTaskToDatabase(Task task) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(task);
		JpaHelper.commitTransaction();
	}
	
	/**
	 * Removes the instance of the {@link Task} class with specified property 'taskID' from database
	 * 
	 * @param taskID is value of 'taskID' property of the Task class. Needed to retrieve the specified 
	 * instance of the Task class.
	 */
	public void removeTask(int taskID) {
		Task task = getTask(taskID);
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.remove(task);
		JpaHelper.commitTransaction();
	}
	
	/**
	 * Gets the list of all instances of {@link Task} class stored in the database for the specified instance 
	 * of the {@link Topic} class. 
	 * 
	 * @param topicID value of 'topicID' property of Topic class needed to identify the instance of the Topic class
	 * to get the list of instances of Task class for
	 * @return list of all instances of the Task class from database for specified instance of Topic class
	 */
	public List<Task> printTasks(int topicID) {
		List<Task> listOfTasks = new ArrayList<>();
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select t from Task t where t.topic=:topic");
		query.setParameter("topic", new TopicServices().getTopic(topicID));
		listOfTasks = query.getResultList();
		return listOfTasks;

	}
	
	/**
	 * Gets the instance of the {@link Task} class from the database identified by the value of the property 
	 * 'taskID'.
	 * 
	 * @param taskID  is value of 'taskID' property of the Task class
	 * @return the instance of Task class with specified value of it's property 'taskID' or null, if the 
	 * specified instance is not present in the database
	 */
	public Task getTask(int taskID) {
		if (taskID > 0) {
			EntityManager em = JpaHelper.getEntityManager();
			return em.find(Task.class, taskID);
		} else {
			return null;
		}
	}
	
	/**
	 * Sets the properties 'taskName' and 'task' of the specified instance of the {@link Task} class and stores 
	 * this instance into the database. 
	 * 
	 * @param taskID value of the property 'taskID' stored in the database, used to retrieve the instance of the Task 
	 * class from the database before updating it's properties
	 * @param newTaskName new value of the property 'taskName' of the Task class to be stored in the database
	 * @param newTask new value of the property 'task' of the Task class to be stored in the database
	 */
	public void updateTask(int taskID, String newTaskName, String newTask) {
		Task task = new TaskServices().getTask(taskID);
		JpaHelper.beginTransaction();
		task.setTask(newTask);
		task.setTaskName(newTaskName);
		JpaHelper.commitTransaction();
	}
}
