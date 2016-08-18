package sk.tsystems.forum.junittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.services.UserServices;

public class UserServicesTests {

	private UserServices userServices = new UserServices();
	private CreatingTestingSamples sample = new CreatingTestingSamples();

	@Before
	public void createTesterUserAndTestingTopic() {
		sample.createTestedUser();
	}

	@After
	public void dropUser() {
		userServices.dropUser(sample.getNameOfTester());
	}

	@Test
	public void doesMethodAddUserWork() {
		// Checks if user "tester" is in database
		assertEquals("tester", userServices.setPresentUser(sample.getNameOfTester()).getUserName());
	}

	@Test
	public void doesMethodApproveUserWork() {
		// Compares users status before and after approveUser method.
		String statusBeforeUpdate = userServices.setPresentUser(sample.getNameOfTester()).getStatus();
		userServices.approveUser(sample.getNameOfTester());
		String statusAfterUpdate = userServices.setPresentUser(sample.getNameOfTester()).getStatus();
		assertNotEquals(statusBeforeUpdate, statusAfterUpdate);
	}

	@Test
	public void doesMethodChangePasswordWorks() {
		String passwordBeforeChange = userServices.setPresentUser(sample.getNameOfTester()).getUserPassword();
		userServices.changePassword(sample.getNameOfTester(), "anotherTestingPassowrd");
		String passwordAfterChange = userServices.setPresentUser(sample.getNameOfTester()).getUserPassword();
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
	public void doesMethodGetUserIDWorkAnotherTry() {
		// Compares two different ids, get by getUserID
		assertNotEquals("0", userServices.getUserID(sample.getNameOfTester()));
		// int userIDBeforeChange =
		// userServices.setPresentUser(nameOfTester).getUserID();
		// userServices.setPresentUser(nameOfTester).setUserID(99999);
		// int userIDAfterChange =
		// userServices.setPresentUser(nameOfTester).getUserID();
		// assertNotEquals(userIDAfterChange, userIDBeforeChange);
	}

	@Test
	public void doesMethodSetPresentUserWork() {
		// Checks if user "tester" is in database
		assertEquals("tester", userServices.setPresentUser(sample.getNameOfTester()).getUserName());
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
		assertNull(userServices.setPresentUser("testingDummy"));
	}

}
