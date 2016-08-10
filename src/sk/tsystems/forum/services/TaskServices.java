package sk.tsystems.forum.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sk.tsystems.forum.entities.Task;
import sk.tsystems.forum.entities.Topic;
import sk.tsystems.forum.services.jpahelper.JpaHelper;

public class TaskServices {
	
	
	public void addTaskToDatabase(Task task) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(task);
		JpaHelper.commitTransaction();
	}
	
	
	public void removeTask(int taskID) {
		Task task = getTask(taskID);
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.remove(task);
		JpaHelper.commitTransaction();
		
	}
	
	
	public List<Task> printTasks() {
		List<Task> listOfTasks = new ArrayList<>();
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select t from Task t");
		listOfTasks = query.getResultList();
		return listOfTasks;

	}
	
	public Task getTask(int taskID) {
		if(taskID > 0) {
			EntityManager em = JpaHelper.getEntityManager();
			return em.find(Task.class, taskID);
		 } 
		else {
			return null;
		 }
	}

	
	public void updateTask(int taskID ,String origTask, String newTask, Topic topic) {
		Task task = new TaskServices().getTask(taskID);
		
		JpaHelper.beginTransaction();
		task.setTaskName(newTask);
		task.setTopic(topic);
		JpaHelper.commitTransaction();
	}
	
}
