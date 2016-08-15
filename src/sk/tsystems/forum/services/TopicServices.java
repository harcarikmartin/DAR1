package sk.tsystems.forum.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sk.tsystems.forum.entities.Topic;
import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.services.jpahelper.JpaHelper;

public class TopicServices {

	public List<Topic> printTopics() {
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

	public int getTopicID(String topic) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("SELECT topicID FROM Topic t WHERE t.topic = :topic");
		query.setParameter("topic", topic);

		if (query.getResultList().isEmpty()) {
			em.close();
			return 0;
		} else {
			return (int) query.getResultList().get(0);
		}
	}

	public Topic setPresentTopic(String topic) {
		int topicID = getTopicID(topic);
		if (topicID > 0) {
			EntityManager em = JpaHelper.getEntityManager();
			return em.find(Topic.class, topicID);
		} else {
			return null;
		}
	}

	public void removeTopic(String topicName) {
		Topic topic = setPresentTopic(topicName);
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.remove(topic);
		JpaHelper.commitTransaction();
	}

	public void updateTopic(String origTopic, String newTopic, String newVisibility) {
		Topic topic = new TopicServices().setPresentTopic(origTopic);
		JpaHelper.beginTransaction();
		topic.setTopic(newTopic);
		topic.setVisibility(newVisibility);
		JpaHelper.commitTransaction();
	}

	public void setSubscriber(Topic topic, User user) {
		JpaHelper.beginTransaction();
		topic.addUser(user);
		JpaHelper.commitTransaction();
	}

	public void removeSubscriber(Topic topic, User user) {
		JpaHelper.beginTransaction();
		topic.removeUser(user);
		JpaHelper.commitTransaction();
	}

}
