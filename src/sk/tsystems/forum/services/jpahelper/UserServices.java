package sk.tsystems.forum.services.jpahelper;

import java.sql.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import sk.tsystems.forum.entities.User;

public class UserServices {

	public void addUser(User user) {
		JpaHelper.beginTransaction();
		JpaHelper.getEntityManager().persist(user);
		JpaHelper.commitTransaction();
	}
	
	
	public User setPresentPlayer(String userName, String userPassword,Date birthDate) {
		int userID = getUserID(userName);
		if(userID > 0) {
			EntityManager em = JpaHelper.getEntityManager();
			return em.find(User.class, userID);
		 } 
		else {
			addUser(new User(userName, userPassword,birthDate));
			return setPresentPlayer(userName, userPassword,birthDate);
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
	
	
	
}
