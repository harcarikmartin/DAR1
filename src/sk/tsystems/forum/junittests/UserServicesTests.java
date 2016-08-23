package sk.tsystems.forum.junittests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.services.UserServices;

/**
 * This class contains JUnit Tests which check if all methods in
 * {@link UserServices} work correctly.
 * 
 * @author karolklescinsky
 */
public class UserServicesTests {

	private UserServices userServices = new UserServices();
	private CreatingTestingSamples sample = new CreatingTestingSamples();
	
	/**
	 * This method is called before each test. Calls method createTestedUser
	 * which adds testedUser into the database.
	 */
	@Before
	public void createTesterUserAndTestingTopic() {
		sample.createTestedUser();
	}
	
	/**
	 * This method is called after each test. Calls method dropUser which drops testedUser
	 * from database.
	 */
	@After
	public void dropUser() {
		userServices.dropUser(sample.getNameOfTester());
	}
	
	/**
	 * Checks if specific user was created earlier under "@Before"
	 * annotation. Checks if String value of userName after calling getPresentUser method equals to String "tester", 
	 * asserting equal.
	 */
	@Test
	public void doesMethodAddUserWork() {
		// Checks if user "tester" is in database
		assertEquals("tester", userServices.getPresentUser(sample.getNameOfTester()).getUserName());
	}
	
	/**
	 * Checks two Strings, one string before calling method approveUser and
	 * another one after calling method approveUser. Values should change from
	 * "pending" to "confirmed".
	 */
	@Test
	public void doesMethodApproveUserWork() {
		// Compares users status before and after approveUser method.
		String statusBeforeUpdate = userServices.getPresentUser(sample.getNameOfTester()).getStatus();
		userServices.approveUser(sample.getNameOfTester());
		String statusAfterUpdate = userServices.getPresentUser(sample.getNameOfTester()).getStatus();
		assertNotEquals(statusBeforeUpdate, statusAfterUpdate);
	}
	
	/**
	 * Checks if the calling of the method isDBEmpty returns false, after creating items in the database before.
	 */
	@Test
	public void doesMethodIsDBEmptyWork() {
		assertEquals(false, userServices.isDBEmpty());
	}
	
	
	@Test
	public void doesMethodRegisterUserWork() {
		doesMethodAddUserWork();
		doesMethodSetPresentUserWork();
	}
	
	
	@Test
	public void doesMethodChangePasswordWorks() {
		String passwordBeforeChange = userServices.getPresentUser(sample.getNameOfTester()).getUserPassword();
		userServices.changePassword(sample.getNameOfTester(), "anotherTestingPassowrd");
		String passwordAfterChange = userServices.getPresentUser(sample.getNameOfTester()).getUserPassword();
		assertNotEquals(passwordBeforeChange, passwordAfterChange);
	}

	@Test
	public void doesMethodIsPasswordCorrectWork() {
		// Good password
		assertTrue(userServices.isPasswordCorrect(sample.getNameOfTester(), "tester"));
		// bad password
		assertFalse(userServices.isPasswordCorrect(sample.getNameOfTester(), "testerios"));
	}

	@Test
	public void deosMethodIsUserApprovedWork() {
		// Checks if is user "tester" approved
		assertFalse(userServices.isUserApproved(sample.getNameOfTester()));
	}

	@Test
	public void doesMethodGetUserIDWork() {
		// Checks if user "tester" was created
		assertNotEquals(0, userServices.getUserID(sample.getNameOfTester()));
	}

	@Test
	public void doesMethodSetPresentUserWork() {
		// Checks if user "tester" is in database
		assertEquals("tester", userServices.getPresentUser(sample.getNameOfTester()).getUserName());
	}

	@Test
	public void doesMethodGetPendingUsersWork() {
		// Checks two different lists
		List<User> lisOfPendingUsersBeforeAddingAnother = new ArrayList<>();
		lisOfPendingUsersBeforeAddingAnother = userServices.getPendingUsers();
		userServices.addUser(
				new User("testingDummy", "testingDummy", sample.parseDate(), "user", "pending", sample.parseDate()));
		List<User> lisOfPendingUsersAfterAddingAnother = new ArrayList<>();
		lisOfPendingUsersAfterAddingAnother = userServices.getPendingUsers();
		assertNotEquals(lisOfPendingUsersAfterAddingAnother, lisOfPendingUsersBeforeAddingAnother);
		userServices.dropUser("testingDummy");
	}

	@Test
	public void doesMethodDropUserWork() {
		// Checks if user "testingDummy" is in table after calling dropUser
		// method
		userServices.addUser(
				new User("testingDummy", "testingDummy", sample.parseDate(), "user", "pending", sample.parseDate()));
		userServices.dropUser("testingDummy");
		assertNull(userServices.getPresentUser("testingDummy"));
	}

	@Test
	public void doesMethodUpdateImageWork() {
		assertNull(userServices.getPresentUser(sample.getNameOfTester()).getProfileImage());
		byte[] randomByteArray = new byte[] { 87, 79 };
		userServices.updateImage(sample.getTestedUser(), randomByteArray);
		assertNotNull(userServices.getPresentUser(sample.getNameOfTester()).getProfileImage());
	}

	@Test
	public void doesMethodGetUsersWork() {
		List<User> lisOfConfirmedUsersBeforeAddingAnother = new ArrayList<>();
		lisOfConfirmedUsersBeforeAddingAnother = userServices.getUsers();
		userServices.addUser(
				new User("testingDummy", "testingDummy", sample.parseDate(), "user", "confirmed", sample.parseDate()));
		List<User> lisOfConfirmedUsersAfterAddingAnother = new ArrayList<>();
		lisOfConfirmedUsersAfterAddingAnother = userServices.getPendingUsers();
		assertNotEquals(lisOfConfirmedUsersAfterAddingAnother, lisOfConfirmedUsersBeforeAddingAnother);
		userServices.dropUser("testingDummy");
	}

	@Test
	public void doesMethodToggleUserRole() {
		String userRoleBeforeTogglingUsersRole = userServices.getPresentUser(sample.getNameOfTester()).getRole();
		userServices.toggleUserRole(sample.getNameOfTester(), "admin");
		String userRoleAfterTogglingUsersRole = userServices.getPresentUser(sample.getNameOfTester()).getRole();
		assertNotEquals(userRoleAfterTogglingUsersRole, userRoleBeforeTogglingUsersRole);
	}
}
