package sk.tsystems.forum.entities.dto;

import sk.tsystems.forum.entities.Topic;
import sk.tsystems.forum.entities.User;

public class UsersTopics {

	private User user;
	private Topic topic;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public UsersTopics(User user, Topic topic) {
		this.user = user;
		this.topic = topic;
	}

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
