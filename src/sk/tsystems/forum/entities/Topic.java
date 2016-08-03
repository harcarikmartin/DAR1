package sk.tsystems.forum.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Topic {

	@Id
	@GeneratedValue
	private int topicID;

	private String topicName;
	private User user;
	private String visibility;

	public Topic(String topicName, User user, String visibility) {
		this.topicName = topicName;
		this.user = user;
		this.visibility = visibility;
	}

	public Topic() {

	}

	public int getTopicID() {
		return topicID;
	}

	public void setTopicID(int topicID) {
		this.topicID = topicID;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	@Override
	public String toString() {
		return "Topic [topicID=" + topicID + ", topicName=" + topicName + ", user=" + user + ", visibility="
				+ visibility + "]";
	}

}
