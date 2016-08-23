package sk.tsystems.forum.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.JoinColumn;

/**
 * This class represents the {@link Entity} required for creation of the structure in the database using JPA connection. 
 * Class is equivalent to the table in database. Encloses fields that are represented as a columns in this table, 
 * instances of the class represent rows in the table in the database.
 * 
 * @author harcarikmartin
 *
 */
@Entity
public class Topic {
	
	/**
	 * Represents primary key of instances of this entity in the database table, generated automatically from database sequence
	 */
	@Id
	@GeneratedValue
	private int topicID;
	
	/**
	 * Represents the intance of {@link User} class who created this instance of this class
	 */
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User creator;
	
	/**
	 * Represents the list of users, whose subscribe this topic 
	 */
	@ManyToMany
	@JoinTable(name = "Topic_User", joinColumns = @JoinColumn(name = "topic_ID", referencedColumnName = "topicID"), inverseJoinColumns = @JoinColumn(name = "user_ID", referencedColumnName = "userID"))
	private List<User> users;
	
	/**
	 * Represents visibility of topic, values can be 'private' or 'public'
	 */
	@Column(nullable = false)
	private String visibility;
	
	/**
	 * Represents name of topic, values are of type String
	 */
	@Column(unique = true, nullable = false)
	private String topic;
	
	/**
	 * Parametric constructor of {@link Topic} class
	 * 
	 * @param creator @see field creator of {@link Topic} class
	 * @param users @see field users of {@link Topic} class
	 * @param visibility @see field visibility of {@link Topic} class
	 * @param topic @see field topic of {@link Topic} class
	 */
	public Topic(User creator, List<User> users, String visibility, String topic) {
		this.creator = creator;
		this.users = users;
		this.visibility = visibility;
		this.topic = topic;
	}
	
	/**
	 * Default constructor of {@link User} class
	 */
	public Topic() {
	}
	
	/**
	 * Adds the instance of the {@link User} class to the instance of the {@link Topic} class
	 * 
	 * @param user instance of the {@link User} class to be added to the instance of the Topic class
	 */
	public void addUser(User user) {
		users.add(user);
		user.getTopics().add(this);
	}
	
	/**
	 * Removes the instance of the {@link User} class to the instance of the {@link Topic} class
	 * 
	 * @param user instance of the {@link User} class to be removed from the instance of the Topic class
	 */
	public void removeUser(User user) {
		users.remove(user);
		user.getTopics().remove(this);
	}
	
	/**
	 * Gets the value of the field topicID of the instance of the {@link Topic} class
	 * 
	 * @return integer value of field topicID
	 */
	public int getTopicID() {
		return topicID;
	}
	
	/**
	 * Sets the value of the field topicID of the instance of the {@link Topic} class to the value of method's parameter
	 * 
	 * @param topicID integer value of field topicID
	 */
	public void setTopicID(int topicID) {
		this.topicID = topicID;
	}
	
	/**
	 * Gets the value of the field creator of the instance of the {@link Topic} class
	 * 
	 * @return User value of field creator, instance of the {@link User} class
	 */
	public User getCreator() {
		return creator;
	}
	
	/**
	 * Sets the value of the field creator of the instance of the {@link Topic} class to the value of method's parameter
	 * 
	 * @param creator instance of the {@link User} class to be added as a creator
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	/**
	 * Gets the list of instances of the {@link User} class for this instance of the {@link Topic} class
	 * 
	 * @return list of users who subscribe this topic
	 */
	public List<User> getUsers() {
		return users;
	}
	
	/**
	 * Sets the list of instances of the {@link User} class for this instance of the {@link Topic} class
	 * 
	 * @param users list of users who subscribe this topic
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	/**
	 * Gets the value of the field visibility of the instance of the {@link Topic} class
	 * 
	 * @return String value of field visibility
	 */
	public String getVisibility() {
		return visibility;
	}
	
	/**
	 * Sets the value of the field visibility of the instance of the {@link Topic} class to the value of method's parameter
	 * 
	 * @param visibility String value of field visibility
	 */
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
	/**
	 * Gets the value of the field topic of the instance of the {@link Topic} class
	 * 
	 * @return String value of field topic
	 */
	public String getTopic() {
		return topic;
	}
	
	/**
	 * Sets the value of the field topic of the instance of the {@link Topic} class to the value of method's parameter
	 * 
	 * @param topic String value of field topic
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	/**
	 * Overriden method toString, useful for string representation of the instance of the {@link Topic} class
	 */
	@Override
	public String toString() {
		return "Topic [topicID=" + topicID + ", creator=" + creator + ", users=" + users + ", visibility=" + visibility
				+ ", topic=" + topic + "]";
	}
}
