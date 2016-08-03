package sk.tsystems.forum.services.jpahelper;

import java.sql.Date;
import javax.persistence.EntityManager;
import sk.tsystems.forum.entities.User;

public class UserServices {

	
	
	
	public void addUser(String userName, String userPassword, Date birthDate) {

		EntityManager em = JpaHelper.getEntityManager();
		
		JpaHelper.beginTransaction();
		em.persist(new User(userName,userPassword,birthDate));
		JpaHelper.commitTransaction();

	}
	
	
	
	
	
	
	
	
}
