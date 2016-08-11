package sk.tsystems.forum.services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.services.jpahelper.JpaHelper;

public class UserServices {

	public void addUser(User user) {
		JpaHelper.beginTransaction();
		JpaHelper.getEntityManager().persist(user);
		JpaHelper.commitTransaction();
	}

	// public User registerUser(List<Topic> topics, String userName, String
	// userPassword,Date birthDate,String role,String status){
	// addUser(new User(topics, userName, userPassword, birthDate, role,
	// status));
	// return setPresentUser(userName, userPassword);
	// }

	public User registerUser(String userName, String userPassword, Date birthDate, String role, String status) {
		addUser(new User(userName, userPassword, birthDate, role, status));
		return setPresentUser(userName);
	}

	public void approveUser(String userName) {
		User user = setPresentUser(userName);
		JpaHelper.beginTransaction();
		user.setStatus("confirmed");
		JpaHelper.commitTransaction();
	}

	public void changePassword(String userName, String newPassword) {
		User user = setPresentUser(userName);
		JpaHelper.beginTransaction();
		user.setUserPassword(newPassword);
		JpaHelper.commitTransaction();
	}

	public User setPresentUser(String userName) {
		int userID = getUserID(userName);
		if (userID > 0) {
			EntityManager em = JpaHelper.getEntityManager();
			return em.find(User.class, userID);
		} else {
			return null;
		}
	}

	public int getUserID(String userName) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("SELECT userID FROM User u WHERE u.userName = :userName");
		query.setParameter("userName", userName);

		if (query.getResultList().isEmpty()) {
			em.close();
			return 0;
		} else {
			return (int) query.getResultList().get(0);
		}
	}

	public boolean isPasswordCorrect(String userName, String userPassword) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery(
				"SELECT userID FROM User u WHERE u.userName = :userName and u.userPassword = :userPassword");
		query.setParameter("userName", userName);
		query.setParameter("userPassword", userPassword);

		if (query.getResultList().isEmpty()) {
			em.close();
			return false;
		} else {
			return true;
		}
	}

	public List<User> getPendingUsers() {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("SELECT userName FROM User u WHERE u.status='pending' ");

		return query.getResultList();

	}

	public void dropUser(String userName) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("DELETE FROM User u Where u.userName=:userName ");
		query.setParameter("userName", userName);
		query.executeUpdate();
		JpaHelper.commitTransaction();
	}

	public boolean isUserApproved(String userName) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("SELECT status FROM User u WHERE u.userName = :userName");
		query.setParameter("userName", userName);

		if (query.getResultList().get(0).equals("confirmed")) {
			em.close();
			return true;
		} else {
			em.close();
			return false;
		}
	}

}
