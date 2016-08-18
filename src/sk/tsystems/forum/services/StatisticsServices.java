package sk.tsystems.forum.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.services.jpahelper.JpaHelper;

public class StatisticsServices {
	
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

	public List<User> getRegisteredLastWeek() {
		Date cal = new Date(System.currentTimeMillis() - 7*24*60*60*1000);
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("SELECT u FROM User u WHERE u.registeredOn > :date");
		query.setParameter("date", cal);
		return query.getResultList();
	}
}
