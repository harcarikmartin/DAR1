package sk.tsystems.forum.services;

import java.util.List;

import javax.persistence.EntityManager;

import sk.tsystems.forum.entities.dto.UsersTopics;
import sk.tsystems.forum.services.jpahelper.JpaHelper;

public class UsersTopicsServices {
	
	public List<UsersTopics> getUsersTopics(){
		EntityManager em = JpaHelper.getEntityManager();
		return em.createQuery("SELECT NEW sk.tsystems.forum.entities.dto.UsersTopics(u, t) FROM Topic t JOIN t.users u ", UsersTopics.class).getResultList();
	}

}
