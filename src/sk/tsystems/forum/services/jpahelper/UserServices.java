package sk.tsystems.forum.services.jpahelper;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sk.tsystems.forum.entities.Topic;
import sk.tsystems.forum.entities.User;

public class UserServices {

	public void addUser(User user) {
		JpaHelper.beginTransaction();
		JpaHelper.getEntityManager().persist(user);
		JpaHelper.commitTransaction();
	}
	
	
	
//	public User registerUser(List<Topic> topics, String userName, String userPassword,Date birthDate,String role,String status){
//		addUser(new User(topics, userName, userPassword, birthDate, role, status));
//		return setPresentUser(userName, userPassword);
//	}
	
	public User registerUser(String userName, String userPassword,Date birthDate,String role,String status){
		addUser(new User(userName, userPassword, birthDate, role, status));
		return setPresentUser(userName, userPassword);
	}

	public User setPresentUser(String userName, String userPassword) {
		int userID = getUserID(userName);
		if(userID > 0) {
			EntityManager em = JpaHelper.getEntityManager();
			return em.find(User.class, userID);
		 } 
		else {
			return null;
		 }
	}
	
	
	
	public int getUserID(String userName) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("SELECT userID FROM User u WHERE u.userName = :userName");
		query.setParameter("userName", userName);
		
		if(query.getResultList().isEmpty()) {
			em.close();
			return 0;
		 } 
		else {
			return (int) query.getResultList().get(0);
		 }
	}
	
	
	
	public boolean isPasswordCorrect(String userName, String userPassword) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("SELECT userID FROM User u WHERE u.userName = :userName and u.userPassword = :userPassword");
		query.setParameter("userName", userName);
		query.setParameter("userPassword", userPassword);
	
		if(query.getResultList().isEmpty()) {
			em.close();
			return false;
		} 
		else {
			return true;
		}
	}
	
	
}
