package sk.tsystems.forum.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue
	private int userID;

	private String userName;

	private String userPassword;
	private Date birthDate;
	private String role;
	private String status;
	
	public User(String userName, String userPassword, Date birthDate, String role, String status) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.birthDate = birthDate;
		this.role = role;
		this.status = status;
	}
	
	public User(String userName, String userPassword, Date birthDate) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.birthDate = birthDate;
	}

	public User() {

	}

	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
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

	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName + ", userPassword=" + userPassword + ", birthDate="
				+ birthDate + ", role=" + role + ", status=" + status + "]";
	}
	
}
