package CSCI5308.GroupFormationTool.UserAuthentication.Repository;

import CSCI5308.GroupFormationTool.UserAuthentication.IUser;
import CSCI5308.GroupFormationTool.UserAuthentication.IUserRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControll.UserMockDB;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControll.UserPasswordPolicyDB;

import CSCI5308.GroupFormationTool.UserAuthentication.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

@SpringBootTest
public class UserRepositoryTest {

	private IUserRepository userRepository;

	@Test
	public void createUserCorrectionDetails() {
		IUser iUser = UserMockDB.setDefault();
		userRepository = mock(UserRepository.class);
		when(userRepository.createUser(iUser)).thenReturn(true);
		assertEquals(true, userRepository.createUser(iUser));
	}

	@Test
	public void createUserWrongDetails() {
		IUser iUser = UserMockDB.setDefault();
		userRepository = mock(UserRepository.class);
		when(userRepository.createUser(iUser)).thenReturn(false);
		assertEquals(false, userRepository.createUser(iUser));
	}

	@Test
	public void getBannerIdIfExistsDetails() {
		IUser iUser = UserMockDB.setDefault();
		userRepository = mock(UserRepository.class);
		userRepository = mock(UserRepository.class);
		when(userRepository.getUserByBannerId(iUser)).thenReturn(true);
		assertEquals(true, userRepository.getUserByBannerId(iUser));
	}

	@Test
	public void getBannerIdIfDoesntNotExists() {
		IUser iUser = UserMockDB.setDefault();
		userRepository = mock(UserRepository.class);
		userRepository = mock(UserRepository.class);
		when(userRepository.getUserByBannerId(iUser)).thenReturn(false);
		assertEquals(false, userRepository.getUserByBannerId(iUser));
	}

	@Test
	public void getEmailIdDetails() {
		IUser iUser = UserMockDB.setDefault();
		userRepository = mock(UserRepository.class);
		when(userRepository.getUserByEmailId(iUser)).thenReturn(true);
		assertEquals(true, userRepository.getUserByEmailId(iUser));
	}

	@Test
	public void getEmailIdIfDoesNotExists() {
		IUser iUser = UserMockDB.setDefault();
		userRepository = mock(UserRepository.class);
		when(userRepository.getUserByEmailId(iUser)).thenReturn(false);
		assertEquals(false, userRepository.getUserByEmailId(iUser));
	}

	

	@Test
	public void getBannerIDs() {
		userRepository = mock(UserRepository.class);
		when(userRepository.getAllBannerIds()).thenReturn(new ArrayList<String>() {
			{
				add("B00854475");
				add("B0085476");
			}
		});
		assertEquals(new ArrayList<String>() {
			{
				add("B00854475");
				add("B0085476");
			}
		}, userRepository.getAllBannerIds());
	}

	
	@Test
	public void getUserPasswordPolicy() {
		userRepository = mock(UserRepository.class);
		when(userRepository.getUserPasswordPolicy()).thenReturn(UserPasswordPolicyDB.getDefault());
		assertThat(userRepository.getUserPasswordPolicy()).isEqualToComparingFieldByField(UserPasswordPolicyDB.getDefault());
	}
}