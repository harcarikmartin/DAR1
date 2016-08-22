package sk.tsystems.forum.services;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import sk.tsystems.forum.entities.Topic;
import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.services.jpahelper.JpaHelper;

/**
 * This class contains methods needed for adding, removing and retrieving information about stored data of 
 * {@link Topic} class from the database.
 * 
 * @author martinharcarik
 *
 */
public class TopicServices {
	
	/**
	 * Gets the list of all instances of {@link Topic} class stored in the database.
	 * 
	 * @return list of all instances of the Topic class from database
	 */
	public List<Topic> printTopics() {
		List<Topic> listOfTopics = new ArrayList<>();
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select t from Topic t");
		listOfTopics = query.getResultList();
		return listOfTopics;
	}
	
	/**
	 * Adds the instance of {@link Topic} class into database. Instance of Topic is sent as a parameter.
	 * 
	 * @param topic is the instance of class Topic that needs to be stored in database.
	 */
	public void addTopicToDatabase(Topic topic) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(topic);
		JpaHelper.commitTransaction();
	}
	
	/**
	 * Gets the integer value of the property 'topicID' of the instance of the {@link Topic} class.
	 * 
	 * @param topic represents value of 'topic' property of Topic class, used to select the needed instance of Topic class
	 * @return integer value of 'topicID' property of the instance of Topic class, or 0 if there is no instance of Topic class
	 * with specified 'topic' property stored in the database.
	 */
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
	
	/**
	 * Gets the instance of {@link Topic} class. Method contains of getting id property of Topic class, then returning the found 
	 * instance of Topic class. 
	 * 
	 * @param topic represents value of 'topic' property, needed to get 'topicID' property of Topic class, 
	 * passed as a parameter of method getTopicID
	 * @return the instance of Topic class or null, if there is no instance of Topic class with this 'topic' property
	 * stored in the database 
	 */
	public Topic setPresentTopic(String topic) {
		int topicID = getTopicID(topic);
		if (topicID > 0) {
			EntityManager em = JpaHelper.getEntityManager();
			return em.find(Topic.class, topicID);
		} else {
			return null;
		}
	}
	
	/**
	 * Gets the instance of the {@link Topic} class from the database identified by the value of the property 
	 * 'topicID'.
	 * 
	 * @param topicID  is value of 'topicID' property of the Topic class
	 * @return the instance of Topic class with specified value of it's property 'topicID' or null, if the 
	 * specified instance is not present in the database
	 */
	public Topic getTopic(int topicID) {
		if (topicID > 0) {
			EntityManager em = JpaHelper.getEntityManager();
			return em.find(Topic.class, topicID);
		} else {
			return null;
		}
	}
	
	/**
	 * Removes the instance of the {@link Topic} class with specified property 'topic' from database
	 * 
	 * @param topicName is value of 'topic' property of the Topic class
	 */
	public void removeTopic(String topicName) {
		Topic topic = setPresentTopic(topicName);
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.remove(topic);
		JpaHelper.commitTransaction();
	}
	
	/**
	 * Sets the properties 'topic' and 'visibility' of the specified instance of the {@link Topic} class and stores 
	 * this instance into the database. 
	 * 
	 * @param origTopic value of the property 'topic' stored in the database, used to retrieve the instance of the Topic 
	 * class from the database before updating it's properties
	 * @param newTopic new value of the property 'topic' of the Topic class to be stored in the database
	 * @param newVisibility new value of the property 'topic' of the Topic class to be stored in the database
	 */
	public void updateTopic(String origTopic, String newTopic, String newVisibility) {
		Topic topic = new TopicServices().setPresentTopic(origTopic);
		JpaHelper.beginTransaction();
		topic.setTopic(newTopic);
		topic.setVisibility(newVisibility);
		JpaHelper.commitTransaction();
	}
	
	/**
	 * Adds the specified instance of {@link User} class into the 'user' property of the instance of {@link Topic} class. 
	 * 
	 * @param topic is the instance of the Topic class
	 * @param user is the instance of the User class to be added as a property of this instance of the Topic class
	 */
	public void setSubscriber(Topic topic, User user) {
		JpaHelper.beginTransaction();
		topic.addUser(user);
		JpaHelper.commitTransaction();
	}
	
	/**
	 * Removes the specified instance of {@link User} class from the 'user' property of the instance of {@link Topic} class. 
	 * 
	 * @param topic is the instance of the Topic class
	 * @param user is the instance of the User class to be removed from the property of this instance of the Topic class
	 */
	public void removeSubscriber(Topic topic, User user) {
		JpaHelper.beginTransaction();
		topic.removeUser(user);
		JpaHelper.commitTransaction();
	}
}
