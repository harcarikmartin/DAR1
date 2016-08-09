package sk.tsystems.forum.junittests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import sk.tsystems.forum.entities.User;
import sk.tsystems.forum.services.UserServices;

public class JUnitTests {

	UserServices userServices = new UserServices();
	String tester = "tester";

	@BeforeClass
	public void createTester() {
		User testedUser = new User();
		testedUser.setUserName(tester);
		testedUser.setUserPassword("tester");
		testedUser.setBirthDate(null);
		testedUser.setRole("user");
		testedUser.setStatus("pending");
		userServices.addUser(testedUser);
	}

	@AfterClass
	public void dropUser() {
		userServices.dropUser(tester);
	}

	@Test
	public void doesMethodIsPasswordCorrectWork() {
		// Good password
		assertTrue(userServices.isPasswordCorrect(tester, "tester"));
		// bad password
		assertFalse(userServices.isPasswordCorrect(tester, "testerios"));
	}

	@Test
	public void deosMethodIsUserApprovedWork() {
		// Checks if is user "tester" approved
		assertFalse(userServices.isUserApproved(tester));
	}

	@Test
	public void doesmethodGetUserIDWork() {
		// Checks if user "tester" was created
		assertNotEquals(0, userServices.getUserID(tester));
	}
}
