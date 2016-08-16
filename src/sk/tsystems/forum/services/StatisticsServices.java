package sk.tsystems.forum.services;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
		Query query = em.createQuery("select count(*) from User u where u.status = 'approved'");
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
		if(query.getResultList().isEmpty()) {
			em.close();
			return 0;
		} else {
			return Math.toIntExact((long) query.getResultList().get(0));
		}
	}
}
