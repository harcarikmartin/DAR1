package sk.tsystems.forum.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Topic {

	@Id
	@GeneratedValue
	private int topicID;

	@ManyToOne(cascade = CascadeType.ALL)
	private User creator;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private User subscriber;
	
	private String visibility;
	private String topicName;
	
	public Topic(User creator, User subscriber, String visibility, String topicName) {
		this.creator = creator;
		this.subscriber = subscriber;
		this.visibility = visibility;
		this.topicName = topicName;
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

	public User getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(User subscriber) {
		this.subscriber = subscriber;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	@Override
	public String toString() {
		return "Topic [topicID=" + topicID + ", creator=" + creator + ", subscriber=" + subscriber + ", visibility="
				+ visibility + ", topicName=" + topicName + "]";
	}
	
}
