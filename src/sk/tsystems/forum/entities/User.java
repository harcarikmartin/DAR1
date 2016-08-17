package sk.tsystems.forum.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue
	private int userID;

	@ManyToMany(mappedBy = "users")
	private List<Topic> topics;

	@Column(unique = true, nullable = false)
	private String userName;

	@Column(nullable = false)
	private String userPassword;

	@Type(type = "date")
	@Column(nullable = false)
	private Date birthDate;

	@Column(nullable = false)
	private String role;

	@Column(nullable = false)
	private String status;

	private Date registeredOn;
	
	@Lob
	@Column(length=16777215)
	private byte[] profileImage;
	
	public User(List<Topic> topics, String userName, String userPassword, Date birthDate, String role, String status) {
		this.topics = topics;
		this.userName = userName;
		this.userPassword = userPassword;
		this.birthDate = birthDate;
		this.role = role;
		this.status = status;
	}

	public User(String userName, String userPassword, Date birthDate, String role, String status,Date registeredOn) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.birthDate = birthDate;
		this.role = role;
		this.status = status;
		this.registeredOn = registeredOn;
	}

	public User() {

	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRegisteredOn() {
		return registeredOn;
	}

	public void setRegisteredOn(Date registeredOn) {
		this.registeredOn = registeredOn;
	}

	public byte[] getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}


	

}
