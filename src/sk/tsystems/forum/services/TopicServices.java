package sk.tsystems.forum.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sk.tsystems.forum.entities.Topic;
import sk.tsystems.forum.services.jpahelper.JpaHelper;

public class TopicServices {

	public List<Topic> printTopic() {

		List<Topic> listOfTopics = new ArrayList<>();
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select t from Topic t");
		listOfTopics = query.getResultList();
		return listOfTopics;

	}
	
	public void addTopicToDatabase(Topic topic) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(topic);
		JpaHelper.commitTransaction();
	}

}
