package sk.tsystems.forum.services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;

import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.services.jpahelper.JpaHelper;
import sk.tsystems.forum.servlet.ForumServlet;

/**
 * This class contains methods needed for retrieving information about stored data of {@link User} class from 
 * the database.
 * 
 * @author martinharcarik
 *
 */
public class UserServices {
	
	/**
	 * Adds the instance of {@link User} class into database. Instance of User is sent as a parameter.
	 * 
	 * @param user is the instance of class User that needs to be stored in database.
	 */
	public void addUser(User user) {
		JpaHelper.beginTransaction();
		JpaHelper.getEntityManager().persist(user);
		JpaHelper.commitTransaction();
	}
	
	/**
	 * Adds the instance of the {@link User} class into database and returns it. Contains of two methods. First method adds 
	 * the user into database, and second returns the user by property userName. 
	 * 
	 * @param userName represents the name of user
	 * @param userPassword is the user's password
	 * @param birthDate is the date of birth of user
	 * @param role is either 'user' as a regular user with less privileges or 'admin' for user with all the privileges
	 * @param status either 'confirmed' or 'pending', required to identify whether the user is able to log in or not.
	 * @param registeredOn represents the date of registration
	 * @return the instance of User or null
	 */
	public User registerUser(String userName, String userPassword, Date birthDate, String role, String status,Date registeredOn) {
		addUser(new User(userName, userPassword, birthDate, role, status,registeredOn));
		return getPresentUser(userName);
	}
	
	/**
	 * Sets the property 'status' of instance of {@link User} class to 'confirmed'. 
	 * 
	 * @param userName represents value of 'userName' property, needed for getting the instance of User class to change it's 'status'
	 * property
	 */
	public void approveUser(String userName) {
		User user = getPresentUser(userName);
		JpaHelper.beginTransaction();
		user.setStatus("confirmed");
		JpaHelper.commitTransaction();
	}
	
	/**
	 * Checks whether the database contains any instances of the {@link User} class.
	 * 
	 * @return true if the database contains 0 instances of the User class, false if it contains
	 * at least one instance of the User class
	 */
	public boolean isDBEmpty(){
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("FROM User u");
		if(query.getResultList().isEmpty()){
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Sets the property 'userPassword' of instance of {@link User} class. Method first gets the instance of class User
	 * by method setPresentUser and then sets it's property 'userPassword' to value of parameter newPassword.
	 * 
	 * @param userName is value of 'userName' property needed as parameter for method setPresentUser to get the instance of User class
	 * @param newPassword represents the value of userPassword property to be set in the instance of User class
	 */
	public void changePassword(String userName, String newPassword) {
		User user = getPresentUser(userName);
		JpaHelper.beginTransaction();
		user.setUserPassword(newPassword);
		JpaHelper.commitTransaction();
	}
	
	/**
	 * Gets the instance of {@link User} class. Method contains of getting id property of User class, then returning the found 
	 * instance of User class. 
	 * 
	 * @param userName represents value of 'userName' property, needed to get 'userID' property of User class, passed as parameter of method 
	 * getUserID
	 * @return the instance of User class or null, if there is no instance of User class with this 'userName' property
	 * stored in the database 
	 */
	public User getPresentUser(String userName) {
		int userID = getUserID(userName);
		if (userID > 0) {
			EntityManager em = JpaHelper.getEntityManager();
			return em.find(User.class, userID);
		} else {
			return null;
		}
	}
	
	/**
	 * Gets the integer value of the property 'userID' of the instance of the {@link User} class.
	 * 
	 * @param userName represents value of 'userName' property of User class, used to select the needed instance of User class
	 * @return integer value of userID property of the instance of User class, or 0 if there is no instance of User class
	 * with specified 'userName' property stored in the database.
	 */
	public int getUserID(String userName) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("SELECT userID FROM User u WHERE u.userName = :userName");
		query.setParameter("userName", userName);

		if (query.getResultList().isEmpty()) {
			em.close();
			return 0;
		} else {
			return (int) query.getResultList().get(0);
		}
	}
	
	/**
	 * Checks if the specified 'userPassword' property of the instance of {@link User} class has the same value than 
	 * the value of stored 'userPassword' property for this instance of User class.
	 * 
	 * @param userName is value of 'userName' property of the User class
	 * @param userPassword is value of 'userPassword' property of the User class
	 * @return true if the specified combination of 'userName' and 'userPassword' properties of User class matches these 
	 * properties of stored instance of the User class or false if specified properties do not match stored properties
	 */
	public boolean isPasswordCorrect(String userName, String userPassword) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery(
				"SELECT userID FROM User u WHERE u.userName = :userName and u.userPassword = :userPassword");
		query.setParameter("userName", userName);
		query.setParameter("userPassword", userPassword);

		if (query.getResultList().isEmpty()) {
			em.close();
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns the list of instances of the {@link User} class with property 'status' set to value 'pending'.
	 * 
	 * @return list of instances of User class
	 */
	public List<User> getPendingUsers() {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("SELECT userName FROM User u WHERE u.status='pending' ");
		return query.getResultList();

	}
	
	/**
	 * Removes the instance of the {@link User} class with specified property 'userName' from database
	 * 
	 * @param userName is value of 'userName' property of the User class
	 */
	public void dropUser(String userName) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("DELETE FROM User u Where u.userName=:userName ");
		query.setParameter("userName", userName);
		query.executeUpdate();
		JpaHelper.commitTransaction();
	}
	
	/**
	 * Checks whether the instance of the {@link User} class with specified property 'userName' has it's property
	 * 'status' set to 'confirmed'
	 * 
	 * @param userName is value of 'userName' property of the User class
	 * @return true if the property 'status' of specified instance of User class is set to 'confirmed', 
	 * otherwise returns false
	 */
	public boolean isUserApproved(String userName) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("SELECT status FROM User u WHERE u.userName = :userName");
		query.setParameter("userName", userName);

		if (query.getResultList().get(0).equals("confirmed")) {
			em.close();
			return true;
		} else {
			em.close();
			return false;
		}
	}
	
	/**
	 * Sets the property 'profileImage' of specified instance of {@link User} class.
	 * 
	 * @param user represents the instance of User class to set the 'profileImage' property of
	 * @param profileImage is the value of 'profileImage' property of User class that is set to this instance of User class
	 */
	public void updateImage(User user, byte[] profileImage) {
		JpaHelper.beginTransaction();
		user.setProfileImage(profileImage);
		JpaHelper.commitTransaction();
	}
	
	/**
	 * Gets the list of instances of {@link User} class that have their 'status' property set to value 'confirmed'.
	 * 
	 * @return list of confirmed users
	 */
	public List<User> getUsers() {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("SELECT u FROM User u where u.status = 'confirmed' order by u.userName asc");
		return query.getResultList();
	}
	
	/**
	 * Sets the 'role' property of specified instance of the {@link User} class to value of the parameter 'newRole'.
	 * Method first gets the instance of the class User using the method setPresentUser, then sets it's property 'role'.
	 * 
	 * @param userName is value of 'userName' property of the User class
	 * @param newRole is value of 'role' property of the User class
	 */
	public void toggleUserRole(String userName, String newRole) {
		User user = getPresentUser(userName);
		JpaHelper.beginTransaction();
		user.setRole(newRole);
		JpaHelper.commitTransaction();
		
	}
}
