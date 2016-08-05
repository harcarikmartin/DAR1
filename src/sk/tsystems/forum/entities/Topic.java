package sk.tsystems.forum.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class Topic {

	@Id
	@GeneratedValue
	private int topicID;

	@ManyToOne(cascade = CascadeType.ALL)
	private User creator;
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	private User subscriber;
	
	
	@ManyToMany
	@JoinTable(name = "Topic_User", joinColumns = @JoinColumn(name = "topic_ID", referencedColumnName="topicID"), inverseJoinColumns = @JoinColumn(name = "user_ID", referencedColumnName="userID"))
	private List<User> users;

	private String visibility;
	private String topic;

	public Topic(int topicID, User creator, List<User> users, String visibility, String topic) {
		this.creator = creator;
		this.users = users;
		this.visibility = visibility;
		this.topic = topic;
	}

	public Topic() {
	}
	
	public int getTopicID() {
		return topicID;
	}

	public void setTopicID(int topicID) {
		this.topicID = topicID;
	}

	public User getCreator() {
		return creator;
	}
	
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public String getVisibility() {
		return visibility;
	}
	
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
	public String getTopic() {
		return topic;
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	@Override
	public String toString() {
		return "Topic [topicID=" + topicID + ", creator=" + creator + ", users=" + users + ", visibility=" + visibility
				+ ", topic=" + topic + "]";
	}

}
