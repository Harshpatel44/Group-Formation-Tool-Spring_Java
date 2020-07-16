package CSCI5308.GroupFormationTool.UserManager;

import CSCI5308.GroupFormationTool.Exceptions.ServiceLayerException;
import static CSCI5308.GroupFormationTool.ApplicationConstants.admin;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.PasswordManager.UserPasswordPolicy;
import CSCI5308.GroupFormationTool.PasswordManager.UserPasswordPolicyStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
	private IUserService userService;
	private IUserRepository userRepository;
	private CurrentUser currentUser;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		userRepository = mock(UserRepository.class);
		currentUser = mock(CurrentUser.class);
		Injector.instance().setUserRepository(userRepository);
		CurrentUser.setInstance(currentUser);
		UserPasswordPolicy.setInstance(2, 23, 1, 1, 1, "@#");
		UserPasswordPolicyStatus.setInstance(1, 1, 1, 1, 1, 1);

	}

	@Test
	public void createExistingUserCorrectDetails() {
		IUser user = UserMockDB.setDefault();
		user.setBannerId("123456789");
		userService = Injector.instance().getUserService();
		when(userRepository.checkIfUserExists(user.getBannerId())).thenReturn(true);
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(user);
		});
		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("bannerId"), "Banner ID already exists");
	}

	@Test
	public void createWithEmptyLastname() throws Exception {
		IUser user = UserMockDB.setDefault();
		user.setLastName("");
		userService = Injector.instance().getUserService();
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(user);
		});
		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("lastName"), "last name cannot be null or empty");
	}

	@Test
	public void createWithInvalidEmail() throws Exception {
		IUser user = UserMockDB.setDefault();
		userService = Injector.instance().getUserService();
		user.setEmailId("neofvno");
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(user);
		});
		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("emailId"), "Enter valid Email");
	}

	@Test
	public void createInvalidPassword() throws Exception {
		IUser user = UserMockDB.setDefault();
		user.setPassword("qwert");
		user.setConfirmPassword("qwerty");
		userService = Injector.instance().getUserService();
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(user);
		});
		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("confirmPassword"), "Passwords and confirm password Does not match");
	}

	@Test
	public void createInvalidPassword1() throws Exception {
		IUser user = UserMockDB.setDefault();
		user.setPassword("qwerty!");
		user.setConfirmPassword("qwerty!");
		userService = Injector.instance().getUserService();
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(user);
		});
		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("confirmPassword"),
				"Password should have minimum 1 uppercaseLetters ");
	}

	@Test
	public void createInvalidPassword2() throws Exception {
		IUser user = UserMockDB.setDefault();
		user.setPassword("QWERTY!");
		user.setConfirmPassword("QWERTY!");
		userService = Injector.instance().getUserService();
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(user);
		});
		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("confirmPassword"),
				"Password should have minimum 1 lowerrcaseLetters");
	}

	@Test
	public void createInvalidPassword3() throws Exception {
		IUser user = UserMockDB.setDefault();
		userService = Injector.instance().getUserService();
		user.setPassword("Qwertnefnvnawjvnonwvonaownvonawonvoawnvonaownvonawovnoasnvojnasvdonoandsvo!");
		user.setConfirmPassword("Qwertnefnvnawjvnonwvonaownvonawonvoawnvonaownvonawovnoasnvojnasvdonoandsvo!");
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(user);
		});
		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("confirmPassword"), "Password should have maximum 23 letters");
	}

	@Test
	public void createInvalidPassword4() throws Exception {
		IUser user = UserMockDB.setDefault();
		user.setPassword("");
		user.setConfirmPassword("");
		userService = Injector.instance().getUserService();
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(user);
		});
		assertEquals(exception.getMapErrors().size() == 2, true);
	}

	@Test
	public void createInvalidPassword5() throws Exception {
		IUser user = UserMockDB.setDefault();
		user.setPassword("Qwertyuiop");
		user.setConfirmPassword("Qwertyuiop");
		userService = Injector.instance().getUserService();
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(user);
		});
		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("confirmPassword"), "Password should have minimum 1 symbols");
	}

	@Test
	public void createInvalidPassword6() throws Exception {
		IUser user = UserMockDB.setDefault();
		user.setPassword("Qwertyuiop@");
		user.setConfirmPassword("Qwertyuiop@");
		userService = Injector.instance().getUserService();
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(user);
		});
		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("confirmPassword"),
				"Following characters are not allowed in password @#");
	}

	@Test
	public void creaUserCorrectDetails() throws Exception {
		IUser user = UserMockDB.setDefault();
		userService = Injector.instance().getUserService();
		when(userRepository.createUser(user)).thenReturn(true);
		assertEquals(true, userService.createUser(user));
	}

	@Test
	public void createUserWithExceptions() {
		IUser iUser = UserMockDB.setDefault();
		userService = Injector.instance().getUserService();
		iUser.setBannerId("");
		iUser.setFirstName("");
		iUser.setLastName("");
		iUser.setPassword("");
		iUser.setContactNumber("");
		iUser.setEmailId("");
		iUser.setConfirmPassword("");
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(iUser);
		});
		assertEquals(6, exception.getMapErrors().size());
	}

	@Test
	public void setUserTest() {
		IUser iUser = new User();
		iUser.setFirstName("firstName");
		iUser.setLastName("lastName");
		iUser.setPassword("password");
		iUser.setBannerId("bannerId");
		iUser.setContactNumber("contactNumber");
		iUser.setEmailId("emailId");
		userService = Injector.instance().getUserService();
		IUser iUser1 = userService.setUser("bannerId", "firstName", "lastName", "emailId", "password", "contactNumber");
		assertEquals("firstName", iUser1.getFirstName());
		assertEquals("lastName", iUser1.getLastName());
		assertEquals("password", iUser1.getPassword());
		assertEquals("bannerId", iUser1.getBannerId());
		assertEquals("contactNumber", iUser1.getContactNumber());
		assertEquals("emailId", iUser1.getEmailId());
	}

	@Test
	public void checkIfUserExistsTest() {
		userService = Injector.instance().getUserService();
		when(userRepository.checkIfUserExists("123456789")).thenReturn(true);
		assertEquals(true, userService.checkIfUserExists("123456789"));
		when(userRepository.checkIfUserExists("123456789")).thenReturn(false);
		assertEquals(false, userService.checkIfUserExists("123456789"));
	}

	@Test
	public void setUserByBannerIdTest() {
		IUser iUser = new User();
		iUser.setFirstName("firstName");
		iUser.setLastName("lastName");
		iUser.setPassword("password");
		iUser.setBannerId("bannerId");
		iUser.setContactNumber("contactNumber");
		iUser.setEmailId("emailId");
		userService = Injector.instance().getUserService();
		when(userRepository.setUserByBannerId("bannerId", iUser)).thenReturn(iUser);
		assertEquals(iUser, userService.setUserByBannerId("bannerId", iUser));
	}

	@Test
	public void getAllBannerIdsTest() {
		List<String> bannerIDs = new ArrayList<>();
		bannerIDs.add("banner1");
		bannerIDs.add("banner2");
		userService = Injector.instance().getUserService();
		when(userRepository.getAllBannerIds()).thenReturn(bannerIDs);
		assertEquals(bannerIDs, userService.getAllBannerIds());
	}

	@Test
	public void checkUserRoleForCourseTest() {
		String bannerID = "bannerId";
		String courseID = "courseID";
		userService = Injector.instance().getUserService();
		when(userRepository.checkUserRoleForCourse(bannerID, courseID)).thenReturn("Instructor");
		assertEquals("Instructor", userService.checkUserRoleForCourse(bannerID, courseID));
	}

	@Test // when user is guest
	public void checkRoleTestGuest() {
		String bannerID = "B23456789";
		userService = Injector.instance().getUserService();
		when(userRepository.checkIfUserIsGuest(bannerID)).thenReturn(true);
		assertTrue(userService.checkIfUserIsGuest(bannerID));
	}

	@Test // when user is Not a guest
	public void checkRoleTestNotGuest() throws Exception {
		String bannerID = "B23456789";
		userService = Injector.instance().getUserService();
		when(userRepository.checkIfUserIsGuest(bannerID)).thenReturn(false);
		assertFalse(userService.checkIfUserIsGuest(bannerID));
	}

	@Test
	public void setCurrentUserByBannerIDTest1() {
		userService = Injector.instance().getUserService();
		userService.setCurrentUserByBannerID(admin);
		when(CurrentUser.instance().getBannerId()).thenReturn(admin);
		when(CurrentUser.instance().getLastName()).thenReturn(admin);
		when(CurrentUser.instance().getFirstName()).thenReturn(admin);
		assertEquals(admin, CurrentUser.instance().getBannerId());
		assertEquals(admin, CurrentUser.instance().getLastName());
		assertEquals(admin, CurrentUser.instance().getFirstName());
	}

	@Test
	public void setCurrentUserByBannerIDTest2() {
		IUser iUser1 = new User();
		IUser iUser = new User();
		iUser.setFirstName("firstName");
		iUser.setLastName("lastName");
		iUser.setPassword("password");
		iUser.setBannerId("B23456789");
		iUser.setContactNumber("contactNumber");
		iUser.setEmailId("emailId");
		userService = Injector.instance().getUserService();
		when(userRepository.setUserByBannerId("B23456789", iUser1)).thenReturn(iUser);
		when(CurrentUser.instance().getBannerId()).thenReturn(iUser.getBannerId());
		when(CurrentUser.instance().getLastName()).thenReturn(iUser.getLastName());
		when(CurrentUser.instance().getFirstName()).thenReturn(iUser.getFirstName());

//		userService.setCurrentUserByBannerID("B23456789");
		assertEquals(iUser.getBannerId(), CurrentUser.instance().getBannerId());
		assertEquals(iUser.getFirstName(), CurrentUser.instance().getFirstName());
		assertEquals(iUser.getLastName(), CurrentUser.instance().getLastName());
	}
}
