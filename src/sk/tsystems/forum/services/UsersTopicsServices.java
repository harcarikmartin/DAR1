package sk.tsystems.forum.services;

import java.util.List;

import javax.persistence.EntityManager;

import sk.tsystems.forum.entities.Topic;
import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.entities.dto.UsersTopics;
import sk.tsystems.forum.services.jpahelper.JpaHelper;

/**
 * UserTopicsServices is the class that contains a method for getting the list of instances of {@link UsersTopics} class.
 * It is useful for retrieving information about {@link User}s subscriptions for {@link Topic}s.
 * 
 * @author martinharcarik
 *
 */
public class UsersTopicsServices {
	
	/**
	 * gets the list of instances of {@link UsersTopics} class.
	 * 
	 * @return list of topics for users
	 */
	public List<UsersTopics> getUsersTopics(){
		EntityManager em = JpaHelper.getEntityManager();
		return em.createQuery("SELECT NEW sk.tsystems.forum.entities.dto.UsersTopics(u, t) FROM Topic t JOIN t.users u ", UsersTopics.class).getResultList();
	}

}
