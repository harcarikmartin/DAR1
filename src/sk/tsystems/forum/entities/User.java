package sk.tsystems.forum.entities;

import java.sql.Timestamp;
import java.util.Arrays;
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

/**
 * This class represents the {@link Entity} required for creation of the structure in the database using JPA connection. 
 * Class is equivalent to the table in database. Encloses fields that are represented as a columns in this table, 
 * instances of the class represent rows in the table in the database.
 * 
 * @author harcarikmartin
 *
 */
@Entity
@Table(name = "Users")
public class User {
	
	/**
	 * Represents primary key of instances of this entity in the database table, generated automatically from database sequence
	 */
	@Id
	@GeneratedValue
	private int userID;
	
	/**
	 * Represents the private topics, whose user is subscribing
	 */
	@ManyToMany(mappedBy = "users")
	private List<Topic> topics;
	
	/**
	 * Represents user's name, must be unique
	 */
	@Column(unique = true, nullable = false)
	private String userName;
	
	/**
	 * Represents user's password, must comply with restrictions specified
	 */
	@Column(nullable = false)
	private String userPassword;
	
	/**
	 * Represents user's date of birth
	 */
	@Type(type = "date")
	@Column(nullable = false)
	private Date birthDate;
	
	/**
	 * Represents user's role, can be 'admin' or 'user'. Set to 'user' by default.
	 */
	@Column(nullable = false)
	private String role;
	
	/**
	 * Represents user's status. Values of the variable can be 'pending' or 'confirmed'. Value 'pending' is set by default, meaning
	 * user is not able to login, unless the value changes to 'confirmed'.
	 */
	@Column(nullable = false)
	private String status;
	
	/**
	 * Represents the {@link Timestamp} value of when the instance of {@link User} class was added to database
	 */
	private Date registeredOn;
	
	/**
	 * Stores user's profile image
	 */
	@Lob
	@Column(length=16777215)
	private byte[] profileImage;
	
	/**
	 * Parametric constructor of {@link User} class
	 * 
	 * @param topics @see field topics of {@link User} class
	 * @param userName @see field userName of {@link User} class
	 * @param userPassword @see field userPassword of {@link User} class
	 * @param birthDate @see field birthDate of {@link User} class
	 * @param role @see field role of {@link User} class
	 * @param status @see field status of {@link User} class
	 */
	public User(List<Topic> topics, String userName, String userPassword, Date birthDate, String role, String status) {
		this.topics = topics;
		this.userName = userName;
		this.userPassword = userPassword;
		this.birthDate = birthDate;
		this.role = role;
		this.status = status;
	}
	
	/**
	 * Parametric constructor of {@link User} class
	 * 
	 * @param userName @see field userName of {@link User} class
	 * @param userPassword @see field userPassword of {@link User} class
	 * @param birthDate @see field birthDate of {@link User} class
	 * @param role @see field role of {@link User} class
	 * @param status @see field status of {@link User} class
	 * @param registeredOn @see field registeredOn of {@link User} class
	 */
	public User(String userName, String userPassword, Date birthDate, String role, String status,Date registeredOn) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.birthDate = birthDate;
		this.role = role;
		this.status = status;
		this.registeredOn = registeredOn;
	}
	
	/**
	 * Default constructor of {@link User} class
	 */
	public User() {

	}
	
	/**
	 * Gets the value of the field userID of the instance of the {@link User} class
	 * 
	 * @return int value of field userID
	 */
	public int getUserID() {
		return userID;
	}
	
	/**
	 * Sets the value of the field userID of the instance of the {@link User} class to the value of method's parameter
	 * 
	 * @param userID int value of field userID
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	/**
	 * Gets the list of instances of the {@link Topic} class for the instance of the {@link User} class
	 * 
	 * @return list of subscribed private topics
	 */
	public List<Topic> getTopics() {
		return topics;
	}
	
	/**
	 * Sets the list of instances of the {@link Topic} class for the instance of the {@link User} class
	 * 
	 * @param topics list of private topics to be added to user's list
	 */
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	
	/**
	 * Gets the value of the field userName of the instance of the {@link User} class
	 * 
	 * @return String value of the field userName
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Sets the value of the field userName of the instance of the {@link User} class
	 * 
	 * @param userName String value of the field userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Gets the value of the field userPassword of the instance of the {@link User} class
	 * 
	 * @return String value of the field userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}
	
	/**
	 * Sets the value of the field userPassword of the instance of the {@link User} class
	 * 
	 * @param userPassword String value of the field userPassword
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	/**
	 * Gets the value of the field birthDate of the instance of the {@link User} class
	 * 
	 * @return String value of the field birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}
	
	/**
	 * Sets the value of the field birthDate of the instance of the {@link User} class
	 * 
	 * @param birthDate String value of the field birthDate
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	/**
	 * Gets the value of the field role of the instance of the {@link User} class
	 * 
	 * @return String value of the field role
	 */
	public String getRole() {
		return role;
	}
	
	/**
	 * Sets the value of the field role of the instance of the {@link User} class
	 * 
	 * @param role String value of the field role
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
	/**
	 * Gets the value of the field status of the instance of the {@link User} class
	 * 
	 * @return String value of the field status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Sets the value of the field status of the instance of the {@link User} class
	 * 
	 * @param status String value of the field status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Gets the value of the field registeredOn of the instance of the {@link User} class
	 * 
	 * @return String value of the field registeredOn
	 */
	public Date getRegisteredOn() {
		return registeredOn;
	}
	
	/**
	 * Sets the value of the field registeredOn of the instance of the {@link User} class
	 * 
	 * @param registeredOn String value of the field registeredOn
	 */
	public void setRegisteredOn(Date registeredOn) {
		this.registeredOn = registeredOn;
	}
	
	/**
	 * Gets the value of the field profileImage of the instance of the {@link User} class
	 * 
	 * @return String value of the field profileImage
	 */
	public byte[] getProfileImage() {
		return profileImage;
	}
	
	/**
	 * Sets the value of the field profileImage of the instance of the {@link User} class
	 * 
	 * @param profileImage String value of the field profileImage
	 */
	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}
	
	/**
	 * Overriden method toString, useful for string representation of the instance of the {@link User} class
	 */
	@Override
	public String toString() {
		return "User [userID=" + userID + ", topics=" + topics + ", userName=" + userName + ", userPassword="
				+ userPassword + ", birthDate=" + birthDate + ", role=" + role + ", status=" + status
				+ ", registeredOn=" + registeredOn + ", profileImage=" + Arrays.toString(profileImage) + "]";
	}
}
