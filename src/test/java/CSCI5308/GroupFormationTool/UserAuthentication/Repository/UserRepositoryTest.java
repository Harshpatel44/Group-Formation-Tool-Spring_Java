package CSCI5308.GroupFormationTool.UserAuthentication.Repository;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControll.UserMockDB;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;
import CSCI5308.GroupFormationTool.UserAuthentication.Service.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

@SpringBootTest
public class UserRepositoryTest {

	private IUserRepository userRepository;

	@Test
	public void createUserCorrectionDetails() {
		User user = UserMockDB.setDefault();
									
		userRepository = mock(UserRepository.class);
		when(userRepository.createUser(user)).thenReturn(true);

		assertEquals(true, userRepository.createUser(user));
    }
	@Test
	public void createUserWrongDetails() {
		User user = UserMockDB.setDefault();
									
		userRepository = mock(UserRepository.class);
		when(userRepository.createUser(user)).thenReturn(false);

		assertEquals(false, userRepository.createUser(user));
    }

	@Test
	public void getBannerIdIfExistsDetails() {
		User user = UserMockDB.setDefault();
		
		userRepository = mock(UserRepository.class);
		
		
//		doThrow(Exception.class).when(userRepository).createUser(user);
		userRepository = mock(UserRepository.class);
		when(userRepository.getUserByBannerId(user)).thenReturn(true);

		assertEquals(true, userRepository.getUserByBannerId(user));
    }
	@Test
	public void getBannerIdIfDoesntNotExists() {
		User user = UserMockDB.setDefault();
		
		userRepository = mock(UserRepository.class);
		
		
//		doThrow(Exception.class).when(userRepository).createUser(user);
		userRepository = mock(UserRepository.class);
		when(userRepository.getUserByBannerId(user)).thenReturn(false);

		assertEquals(false, userRepository.getUserByBannerId(user));
    }
	@Test
	public void getEmailIdDetails() {
		User user = UserMockDB.setDefault();
									
		userRepository = mock(UserRepository.class);
		when(userRepository.getUserByEmailId(user)).thenReturn(true);

		assertEquals(true, userRepository.getUserByEmailId(user));
    }
	@Test
	public void getEmailIdIfDoesNotExists() {
		User user = UserMockDB.setDefault();
									
		userRepository = mock(UserRepository.class);
		when(userRepository.getUserByEmailId(user)).thenReturn(false);

		assertEquals(false, userRepository.getUserByEmailId(user));
    }
	
}