package sk.tsystems.forum.services;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import sk.tsystems.forum.entities.Comment;
import sk.tsystems.forum.entities.Task;
import sk.tsystems.forum.entities.Topic;
import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.services.jpahelper.JpaHelper;

public class StatisticsServices {
	
	/**
	 * Gets the amount of instances of {@link User} class stored in database.
	 * 
	 * @return count of users
	 */
	public int getAllUsersCount() {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select count(*) from User u");
		if(query.getResultList().isEmpty()) {
			em.close();
			return 0;
		} else {
			return Math.toIntExact((long) query.getResultList().get(0));
		}
	}
	
	/**
	 * Gets the amount of instances of {@link User} class stored in database, 
	 * that have the property role set to 'admin'.
	 * 
	 * @return count of administrators
	 */
	public int getAdminsCount() {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select count(*) from User u where u.role = 'admin'");
		if(query.getResultList().isEmpty()) {
			em.close();
			return 0;
		} else {
			return Math.toIntExact((long) query.getResultList().get(0));
		}
	}
	
	/**
	 * Gets the amount of instances of {@link User} class stored in database, 
	 * that have the property role set to 'user'.
	 * 
	 * @return count of users
	 */
	public int getUsersCount() {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select count(*) from User u where u.role = 'user'");
		if(query.getResultList().isEmpty()) {
			em.close();
			return 0;
		} else {
			return Math.toIntExact((long) query.getResultList().get(0));
		}
	}
	
	/**
	 * Gets the amount of instances of {@link User} class stored in database, 
	 * that have the property status set to 'confirmed'.
	 * 
	 * @return count of approved users
	 */
	public int getApprovedCount() {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select count(*) from User u where u.status = 'confirmed'");
		if(query.getResultList().isEmpty()) {
			em.close();
			return 0;
		} else {
			return Math.toIntExact((long) query.getResultList().get(0));
		}
	}
	
	/**
	 * Gets the amount of instances of {@link User} class stored in database, 
	 * that have the property status set to 'pending'.
	 * 
	 * @return count of not approved users
	 */
	public int getNotApprovedCount() {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select count(*) from User u where u.status = 'pending'");
		if(query.getResultList().isEmpty()) {
			em.close();
			return 0;
		} else {
			return Math.toIntExact((long) query.getResultList().get(0));
		}
	}
	
	/**
	 * Get the amount of instances of {@link Topic} class stored in database.
	 * 
	 * @return count of topics
	 */
	public int getTopicsCount() {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select count(*) from Topic");
		if(query.getResultList().isEmpty()) {
			em.close();
			return 0;
		} else {
			return Math.toIntExact((long) query.getResultList().get(0));
		}
	}
	
	/**
	 * Get the amount of instances of {@link Task} class stored in database.
	 * 
	 * @return count of tasks
	 */
	public int getTasksCount() {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select count(*) from Task");
		if(query.getResultList().isEmpty()) {
			em.close();
			return 0;
		} else {
			return Math.toIntExact((long) query.getResultList().get(0));
		}
	}
	
	/**
	 * Get the amount of instances of {@link Comment} class stored in database.
	 * 
	 * @return count of comments
	 */
	public int getCommentsCount() {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select count(*) from Comment");
		if(query.getResultList().isEmpty()) {
			em.close();
			return 0;
		} else {
			return Math.toIntExact((long) query.getResultList().get(0));
		}
	}
	
	/**
	 * Get the amount of instances of {@link Topic} class stored in database, 
	 * that have the property visibility set to 'public'.
	 * 
	 * @return count of public topics
	 */
	public int getPublicTopicsCount() {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select count(*) from Topic t where t.visibility = 'public'");
		if(query.getResultList().isEmpty()) {
			em.close();
			return 0;
		} else {
			return Math.toIntExact((long) query.getResultList().get(0));
		}
	}
	
	/**
	 * Get the amount of instances of {@link Topic} class stored in database, 
	 * that have the property visibility set to 'private'.
	 * 
	 * @return count of private topics
	 */
	public int getPrivateTopicsCount() {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select count(*) from Topic t where t.visibility = 'private'");
		if(query.getResultList().isEmpty()) {
			em.close();
			return 0;
		} else {
			return Math.toIntExact((long) query.getResultList().get(0));
		}
	}
	
	/**
	 * Gets the name property of the instance of {@link Task} class, 
	 * that have the highest number of instances of {@link Comment} class assigned to or null.
	 * 
	 * @return name of the task, that have the highest amount of comments assigned to
	 */
	public String getMostAnsweredTask() {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select c.task.taskName from Comment c group by c.task.taskName order by count(c.task.taskName) desc");
		if(query.getResultList().isEmpty()) {
			em.close();
			return null;
		} else {
			return (String) query.getResultList().get(0);
		}
	}
	
	/**
	 * Gets the highest number of instances of {@link Comment} class that are assigned to the instance of {@link Task} class.
	 * 
	 * @return number of comments for task that has the highest amount of comments assigned to
	 */
	public int getNumberOfCommentsForMostAnsweredTask() {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select max(count(c.task.taskName)) from Comment c group by c.task.taskName order by count(c.task.taskName) desc");
		int result = 0;
		try {
			if(query.getResultList().size() == 0) {
				em.close();
			} else {
				result = Math.toIntExact((long) query.getResultList().get(0));
			}
		} catch(Exception e) {
			System.out.println("Result" + query.getResultList());
			System.out.println("Message" + e.getMessage());
		} finally {
			return result;
		}
	}
	
	/**
	 * Gets the userName property of the instance of {@link User} class which has 
	 * the highest number of instances of {@link Comment} class assigned to.
	 * 
	 * @return name of user, which has the highest number of comments assigned to
	 */
	public String getMostActiveUser() {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select c.user.userName from Comment c group by c.user.userName order by count(c.user.userName) desc");
		if(query.getResultList().isEmpty()) {
			em.close();
			return null;
		} else {
			return (String) query.getResultList().get(0);
		}
	}
	
	/**
	 * Gets the highest number of instances of {@link Comment} class assigned to the instance of {@link User} class.
	 * 
	 * @return number of comments for user that has the highest amount of comments assigned to
	 */
	public int getNumberOfCommentsForMostActiveUser() {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select max(count(c.user.userName)) from Comment c group by c.user.userName order by count(c.user.userName) desc");
		int result = 0;
		try {
			if(query.getResultList().isEmpty()) {
				em.close();
			} else {
				result = Math.toIntExact((long) query.getResultList().get(0));
			}
		} catch (Exception e) {
			System.out.println("Result" + query.getResultList());
			System.out.println("Message" + e.getMessage());
		} finally {
			return result;
		}
	}
	
	/**
	 * Gets the number of instances of {@link Task} class for specified instance of {@link Topic}. Topic is specified 
	 * by parameter topic of type String, that represents property topic of class Topic.
	 * 
	 * @param topic	the name of topic to get amount of tasks for
	 * @return number of tasks assigned to specific topic
	 */
	public int getNumberOfTasksForTopic(String topic) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select count(t) from Task t where t.topic.topic = :topic");
		query.setParameter("topic", topic);
		if(query.getResultList().isEmpty()) {
			em.close();
			return 0;
		} else {
			return Math.toIntExact((long) query.getResultList().get(0));
		}
	}
	
	/**
	 * Gets the instance of {@link User}, that has the property registeredOn with highest value, 
	 * or null if there are no instances of User.
	 * 
	 * @return User, that has registered as last or null, if there are no users to select from
	 */
	public User getLatestUser() {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select u from User u order by u.registeredOn desc");
		if(query.getResultList().isEmpty()) {
			em.close();
			return null;
		} else {
			return (User) query.getResultList().get(0);
		}
	}
	
	/**
	 * Gets the {@link List} of instances of {@link User} class, that have the property registeredOn 
	 * set not longer than 7 days ago from the moment of calling the method. Property's registeredOn type is 
	 * {@link Date}.
	 * 
	 * @return list of users that have registered not longer than a week ago from the moment of calling the method
	 */
	public List<User> getRegisteredLastWeek() {
		Date cal = new Date(System.currentTimeMillis() - 7*24*60*60*1000);
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("SELECT u FROM User u WHERE u.registeredOn > :date");
		query.setParameter("date", cal);
		return query.getResultList();
	}
}
