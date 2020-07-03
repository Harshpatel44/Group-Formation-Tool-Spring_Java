package CSCI5308.GroupFormationTool.UserAuthentication;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Exceptions.ServiceLayerException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
	private IUserService userService;
	private IUserRepository userRepository;

	@BeforeEach
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        userRepository = mock(UserRepository.class);
        Injector.instance().setUserRepository(userRepository);
        UserPasswordPolicy.setInstance(2, 23, 1, 1, 1, "@#");
        UserPasswordPolicyStatus.setInstance(1,1,1,1,1,1);
    }
	
	@Test
	public void createUserWithExceptions() throws Exception {
		IUser user = new User();
		userService = Injector.instance().getUserService();
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(user);
		});
		assertEquals(7, exception.getMapErrors().size());
	}

	@Test
	public void createExistingUserCorrectDetails() throws Exception {
		IUser user = UserMockDB.setDefault();
		userService = Injector.instance().getUserService();
		when(userRepository.getUserByBannerId(user)).thenReturn(true);
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(user);
		});
		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("bannerId"), "Banner ID already exists");
	}

	@Test
	public void createWithEmptyFirstname() throws Exception {
		IUser user = UserMockDB.setDefault();
		user.setFirstName("");
		userService = Injector.instance().getUserService();
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(user);
		});
		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("firstName"), "first name cannot be  null or empty");
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
		user.setEmailId("neofvno");
		userService = Injector.instance().getUserService();
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
		assertEquals(exception.getMapErrors().get("confirmPassword"), "Password should have minimum 1 uppercaseLetters ");
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
		assertEquals(exception.getMapErrors().get("confirmPassword"), "Password should have minimum 1 lowerrcaseLetters");
	}

	@Test
	public void createInvalidPassword3() throws Exception {
		IUser user = UserMockDB.setDefault();
		user.setPassword("Qwertnefnvnawjvnonwvonaownvonawonvoawnvonaownvonawovnoasnvojnasvdonoandsvo!");
		user.setConfirmPassword("Qwertnefnvnawjvnonwvonaownvonawonvoawnvonaownvonawovnoasnvojnasvdonoandsvo!");
		userService = Injector.instance().getUserService();
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
		assertEquals(exception.getMapErrors().get("confirmPassword"), "Following characters are not allowed in password @#");
	}

	@Test
	public void creaUserCorrectDetails() throws Exception {
		IUser user = UserMockDB.setDefault();
		userService = mock(UserService.class);
		when(userService.createUser(user)).thenReturn(true);
		assertEquals(true, userService.createUser(user));
	}

	@Test
	public void getUserPasswordPolicy() throws Exception {
		userService = Injector.instance().getUserService();
		when(userRepository.getUserPasswordPolicy()).thenReturn(UserPasswordPolicyDB.getDefault());
		System.out.println(UserPasswordPolicyDB.getDefault().getMaxLength());
		System.out.println(userService.getUserPasswordPolicy().getMaxLength());
		assertThat(userService.getUserPasswordPolicy()).isEqualToComparingFieldByField(UserPasswordPolicyDB.getDefault());
	}
}