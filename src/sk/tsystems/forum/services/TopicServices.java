package sk.tsystems.forum.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sk.tsystems.forum.entities.Topic;
import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.services.jpahelper.JpaHelper;

public class TopicServices {

	public List<Topic> printTopic() {

		List<Topic> listOfTopics = new ArrayList<>();
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select t from Topic t");
		listOfTopics = query.getResultList();
		return listOfTopics;

	}
	
	public List<Topic> printSpecificTopicsByUsersID(User user) {

		List<Topic> listOfTopics = new ArrayList<>();
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createNativeQuery("select t.topic from TOPIC_USER tu join TOPIC t on t.TOPICID=tu.TOPIC_ID join USERS u on tu.USER_ID=u.USERID where u.USERID LIKE:userID;");
		query.setParameter("userID", user.getUserID());
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
