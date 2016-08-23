package sk.tsystems.forum.entities.dto;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import sk.tsystems.forum.entities.Topic;
import sk.tsystems.forum.entities.User;

/**
 * This class represents the {@link Entity} required for creation of the structure in the database using JPA connection. 
 * Table is created as a result of {@link ManyToMany} relationship between the entities User and Topic.
 * Class is equivalent to the table in database. Encloses fields that are represented as a columns in this table, 
 * instances of the class represent rows in the table in the database.
 * 
 * @author harcarikmartin
 *
 */
public class UsersTopics {
	
	/**
	 * Represents instance of {@link User} class
	 */
	private User user;
	
	/**
	 * Represents instance of {@link Topic} class
	 */
	private Topic topic;
	
	/**
	 * Gets the value of the field user of the instance of the {@link UsersTopics} class
	 * 
	 * @return value of field user, instance of the {@link User} class
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Sets the value of the field user of the instance of the {@link UsersTopics} class as the value of method's parameter
	 * 
	 * @param user instance of the {@link User} class, value of parameter user 
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Gets the value of the field topic of the instance of the {@link UsersTopics} class
	 * 
	 * @return value of field topic, instance of the {@link Topic} class
	 */
	public Topic getTopic() {
		return topic;
	}
	
	/**
	 * Sets the value of the field topic of the instance of the {@link UsersTopics} class as the value of method's parameter
	 * 
	 * @param topic instance of the {@link Topic} class, value of parameter topic 
	 */
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	/**
	 * Parametric constructor of {@link UsersTopics} class
	 * 
	 * @param user @see field user of {@link UsersTopics} class
	 * @param topic @see field topic of {@link UsersTopics} class
	 */
	public UsersTopics(User user, Topic topic) {
		this.user = user;
		this.topic = topic;
	}
	
	/**
	 * Default constructor of {@link UsersTopics} class
	 */
	public UsersTopics() {}

	/**
	 * Overriden method toString, useful for string representation of the instance of the {@link Topic} class
	 */
	@Override
	public String toString() {
		return "UsersTopics [topicID=" + topic.getTopicID() + ", " + "topicName=" + topic.getTopic() + ", "
				+ "topicVisibility=" + topic.getVisibility() + ", " + "topicCreatorName="
				+ topic.getCreator().getUserName() + ", " + "topicCreatorID=" + topic.getCreator().getUserID() + ", "
				+ "userID=" + user.getUserName() + ", " + "userName=" + user.getUserName() + ", " + "userPassword="
				+ user.getUserPassword() + ", " + "userRole=" + user.getRole() + ", " + "userStatus=" + user.getStatus()
				+ ", " + "userBirthdate=" + user.getBirthDate() + "]";
	}

}
