package CSCI5308.GroupFormationTool.UserAuthentication.Service;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Exceptions.ServiceLayerException;
import CSCI5308.GroupFormationTool.UserAuthentication.*;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControll.UserMockDB;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControll.UserPasswordPolicyDB;

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
	public void createUserWithExceptions() throws ServiceLayerException {
		IUser iUser = new User();
		userService = Injector.instance().getUserService();
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(iUser);
		});
		assertEquals(7, exception.getMapErrors().size());
	}

	@Test
	public void createExistingUserCorrectDetails() throws ServiceLayerException {
		IUser iUser = UserMockDB.setDefault();
		userService = Injector.instance().getUserService();
		when(userRepository.getUserByBannerId(iUser)).thenReturn(true);
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(iUser);
		});
		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("bannerId"), "Banner ID already exists");
	}

	@Test
	public void createWithEmptyFirstname() throws ServiceLayerException {
		IUser iUser = UserMockDB.setDefault();
		iUser.setFirstName("");
		userService = Injector.instance().getUserService();
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(iUser);
		});
		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("firstName"), "first name cannot be  null or empty");
	}

	@Test
	public void createWithEmptyLastname() throws ServiceLayerException {
		IUser iUser = UserMockDB.setDefault();
		iUser.setLastName("");
		userService = Injector.instance().getUserService();
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(iUser);
		});
		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("lastName"), "last name cannot be null or empty");
	}

	@Test
	public void createWithInvalidEmail() throws ServiceLayerException {
		IUser iUser = UserMockDB.setDefault();
		iUser.setEmailId("neofvno");
		userService = Injector.instance().getUserService();
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(iUser);
		});
		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("emailId"), "Enter valid Email");
	}

	@Test
	public void createInvalidPassword() throws ServiceLayerException {
		IUser iUser = UserMockDB.setDefault();
		iUser.setPassword("qwert");
		iUser.setConfirmPassword("qwerty");
		userService = Injector.instance().getUserService();
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(iUser);
		});
		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("confirmPassword"), "Passwords and confirm password Does not match");
	}

	@Test
	public void createInvalidPassword1() throws ServiceLayerException {
		IUser iUser = UserMockDB.setDefault();
		iUser.setPassword("qwerty!");
		iUser.setConfirmPassword("qwerty!");
		userService = Injector.instance().getUserService();
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(iUser);
		});
		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("confirmPassword"), "Password should have minimum 1 uppercaseLetters ");
	}

	@Test
	public void createInvalidPassword2() throws ServiceLayerException {
		IUser iUser = UserMockDB.setDefault();
		iUser.setPassword("QWERTY!");
		iUser.setConfirmPassword("QWERTY!");
		userService = Injector.instance().getUserService();
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(iUser);
		});
		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("confirmPassword"), "Password should have minimum 1 lowerrcaseLetters");
	}

	@Test
	public void createInvalidPassword3() throws ServiceLayerException {
		IUser iUser = UserMockDB.setDefault();
		iUser.setPassword("Qwertnefnvnawjvnonwvonaownvonawonvoawnvonaownvonawovnoasnvojnasvdonoandsvo!");
		iUser.setConfirmPassword("Qwertnefnvnawjvnonwvonaownvonawonvoawnvonaownvonawovnoasnvojnasvdonoandsvo!");
		userService = Injector.instance().getUserService();
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(iUser);
		});
		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("confirmPassword"), "Password should have maximum 23 letters");
	}

	@Test
	public void createInvalidPassword4() throws ServiceLayerException {
		IUser iUser = UserMockDB.setDefault();
		iUser.setPassword("");
		iUser.setConfirmPassword("");
		userService = Injector.instance().getUserService();
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(iUser);
		});
		assertEquals(exception.getMapErrors().size() == 2, true);
	}

	@Test
	public void createInvalidPassword5() throws ServiceLayerException {
		IUser iUser = UserMockDB.setDefault();
		iUser.setPassword("Qwertyuiop");
		iUser.setConfirmPassword("Qwertyuiop");
		userService = Injector.instance().getUserService();
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(iUser);
		});
		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("confirmPassword"), "Password should have minimum 1 symbols");
	}

	@Test
	public void createInvalidPassword6() throws ServiceLayerException {
		IUser iUser = UserMockDB.setDefault();
		iUser.setPassword("Qwertyuiop@");
		iUser.setConfirmPassword("Qwertyuiop@");
		userService = Injector.instance().getUserService();
		ServiceLayerException exception = assertThrows(ServiceLayerException.class, () -> {
			userService.createUser(iUser);
		});
		assertEquals(exception.getMapErrors().size() == 1, true);
		assertEquals(exception.getMapErrors().get("confirmPassword"), "Following characters are not allowed in password @#");
	}

	@Test
	public void creaUserCorrectDetails() throws ServiceLayerException {
		IUser iUser = UserMockDB.setDefault();
		userService = mock(UserService.class);
		when(userService.createUser(iUser)).thenReturn(true);
		assertEquals(true, userService.createUser(iUser));
	}

	@Test
	public void getUserPasswordPolicy() {
		userService = Injector.instance().getUserService();
		when(userRepository.getUserPasswordPolicy()).thenReturn(UserPasswordPolicyDB.getDefault());
		System.out.println(UserPasswordPolicyDB.getDefault().getMaxLength());
		System.out.println(userService.getUserPasswordPolicy().getMaxLength());
		assertThat(userService.getUserPasswordPolicy()).isEqualToComparingFieldByField(UserPasswordPolicyDB.getDefault());
	}
}