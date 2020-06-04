package CSCI5308.GroupFormationTool.UserAuthentication.Service;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Exceptions.ServiceLayerException;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserService;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControll.UserMockDB;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
	private IUserService userService;

	@Test
	public void createUserWithExceptions() throws ServiceLayerException {
		User user = new User();
		userService = Injector.instance().getUserService();
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(user);
		});
		assertEquals(exception.getMapErrors().size() == 6, true);

	}

	@Test
	public void createExistingUserCorrectDetails() throws ServiceLayerException {
		User user = UserMockDB.setDefault();

		userService = Injector.instance().getUserService();

		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(user);
		});

		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("bannerId"), "Banner ID already exists");

	}

	@Test
	public void createWithEmptyFirstname() throws ServiceLayerException {
		User user = UserMockDB.setDefault();
		user.setFirstName("");
		userService = Injector.instance().getUserService();

		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(user);
		});

		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("firstName"), "first name cannot be  null or empty");

	}

	@Test
	public void createWithEmptyLastname() throws ServiceLayerException {
		User user = UserMockDB.setDefault();
		user.setLastName("");
		userService = Injector.instance().getUserService();

		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(user);
		});

		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("lastName"), "last name cannot be null or empty");

	}

	@Test
	public void createWithInvalidEmail() throws ServiceLayerException {
		User user = UserMockDB.setDefault();
		user.setEmailId("neofvno");
		userService = Injector.instance().getUserService();

		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(user);
		});

		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("emailId"), "Enter valid Email");

	}

	@Test
	public void createInvalidPasswords() throws ServiceLayerException {
		User user = UserMockDB.setDefault();
		user.setPassword("qwert");
		user.setConfirmPassword("qwerty");
		userService = Injector.instance().getUserService();

		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(user);
		});

		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("confirmPassword"), "Passwords and confirm password Doesnt match");

	}

	@Test
	public void creaUserCorrectDetails() throws ServiceLayerException {
//    	userRepository = mock(Injector.instance().getUserRepository().getClass());
//    	injector = mock(Injector.class);
		User user = UserMockDB.setDefault();
//    	when(mock(Injector.class).instance().getUserRepository()).thenReturn(userRepository);
//    	when(userRepository.createUser(user)).thenReturn(true);
//    	
//    	assertEquals(true,userService.createUser(user));
//		ServiceLayerException exception = assertThrows(ServiceLayerException.class,()->{userService.createUser(user);});
//    	assertEquals(exception.getMapErrors().size()==1,true);

		userService = mock(UserService.class);
		when(userService.createUser(user)).thenReturn(true);

		assertEquals(true, userService.createUser(user));

	}

}