package sk.tsystems.forum.junittests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.services.UserServices;

public class UserServicesTests {

	private UserServices userServices = new UserServices();
	private String nameOfTester = "tester";
	private User testedUser = new User();

	public Date parseDate() {
		String dateString = "2016-08-18";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = df.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	@Before
	public void createTesterUserAndTestingTopic() {
		testedUser.setUserName(nameOfTester);
		testedUser.setUserPassword("tester");
		testedUser.setBirthDate(parseDate());
		testedUser.setRole("user");
		testedUser.setStatus("pending");
		userServices.addUser(testedUser);
	}

	@After
	public void dropUser() {
		userServices.dropUser(nameOfTester);
	}

	@Test
	public void doesMethodAddUserWork() {
		// Checks if user "tester" is in database
		assertEquals("tester", userServices.setPresentUser(nameOfTester).getUserName());
	}

	@Test
	public void doesMethodApproveUserWork() {
		// Compares users status before and after approveUser method.
		String statusBeforeUpdate = userServices.setPresentUser(nameOfTester).getStatus();
		userServices.approveUser(nameOfTester);
		String statusAfterUpdate = userServices.setPresentUser(nameOfTester).getStatus();
		assertNotEquals(statusBeforeUpdate, statusAfterUpdate);
	}

	@Test
	public void doesMethodChangePasswordWorks() {
		String passwordBeforeChange = userServices.setPresentUser(nameOfTester).getUserPassword();
		userServices.changePassword(nameOfTester, "anotherTestingPassowrd");
		String passwordAfterChange = userServices.setPresentUser(nameOfTester).getUserPassword();
		assertNotEquals(passwordBeforeChange, passwordAfterChange);
	}

	@Test
	public void doesMethodIsPasswordCorrectWork() {
		// Good password
		assertTrue(userServices.isPasswordCorrect(nameOfTester, "tester"));
		// bad password
		assertFalse(userServices.isPasswordCorrect(nameOfTester, "testerios"));
	}

	@Test
	public void deosMethodIsUserApprovedWork() {
		// Checks if is user "tester" approved
		assertFalse(userServices.isUserApproved(nameOfTester));
	}

	@Test
	public void doesMethodGetUserIDWork() {
		// Checks if user "tester" was created
		assertNotEquals(0, userServices.getUserID(nameOfTester));
	}

	@Test
	public void doesMethodGetUserIDWorkAnotherTry() {
		// Compares two different ids, get by getUserID
		assertNotEquals("0", userServices.getUserID(nameOfTester));
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
		assertEquals("tester", userServices.setPresentUser(nameOfTester).getUserName());
	}

	@Test
	public void doesMethodGetPendingUsersWork() {
		// Checks two different lists
		List<User> lisOfPendingUsersBeforeAddingAnother = new ArrayList<>();
		lisOfPendingUsersBeforeAddingAnother = userServices.getPendingUsers();
		userServices.addUser(new User("testingDummy", "testingDummy", parseDate(), "user", "pending"));
		List<User> lisOfPendingUsersAfterAddingAnother = new ArrayList<>();
		lisOfPendingUsersAfterAddingAnother = userServices.getPendingUsers();
		assertNotEquals(lisOfPendingUsersAfterAddingAnother, lisOfPendingUsersBeforeAddingAnother);
		userServices.dropUser("testingDummy");
	}

	@Test
	public void doesMethodDropUserWork() {
		// Checks if user "testingDummy" is in table after calling dropUser
		// method
		userServices.addUser(new User("testingDummy", "testingDummy", parseDate(), "user", "pending"));
		userServices.dropUser("testingDummy");
		assertNull(userServices.setPresentUser("testingDummy"));
	}

}
