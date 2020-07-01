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
		IUser user = UserMockDB.setDefault();
		userRepository = mock(UserRepository.class);
		when(userRepository.createUser(user)).thenReturn(true);
		assertEquals(true, userRepository.createUser(user));
	}

	@Test
	public void createUserWrongDetails() {
		IUser user = UserMockDB.setDefault();
		userRepository = mock(UserRepository.class);
		when(userRepository.createUser(user)).thenReturn(false);
		assertEquals(false, userRepository.createUser(user));
	}

	@Test
	public void getBannerIdIfExistsDetails() {
		IUser user = UserMockDB.setDefault();
		userRepository = mock(UserRepository.class);
		userRepository = mock(UserRepository.class);
		when(userRepository.getUserByBannerId(user)).thenReturn(true);
		assertEquals(true, userRepository.getUserByBannerId(user));
	}

	@Test
	public void getBannerIdIfDoesntNotExists() {
		IUser user = UserMockDB.setDefault();
		userRepository = mock(UserRepository.class);
		userRepository = mock(UserRepository.class);
		when(userRepository.getUserByBannerId(user)).thenReturn(false);
		assertEquals(false, userRepository.getUserByBannerId(user));
	}

	@Test
	public void getEmailIdDetails() throws Exception {
		IUser user = UserMockDB.setDefault();
		userRepository = mock(UserRepository.class);
		when(userRepository.getUserByEmailId(user)).thenReturn(true);
		assertEquals(true, userRepository.getUserByEmailId(user));
	}

	@Test
	public void getEmailIdIfDoesNotExists() throws Exception {
		IUser user = UserMockDB.setDefault();
		userRepository = mock(UserRepository.class);
		when(userRepository.getUserByEmailId(user)).thenReturn(false);
		assertEquals(false, userRepository.getUserByEmailId(user));
	}

	

	@Test
	public void getBannerIDs() throws Exception {
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
	public void getUserPasswordPolicy() throws Exception {
		userRepository = mock(UserRepository.class);
		when(userRepository.getUserPasswordPolicy()).thenReturn(UserPasswordPolicyDB.getDefault());
		assertThat(userRepository.getUserPasswordPolicy()).isEqualToComparingFieldByField(UserPasswordPolicyDB.getDefault());
	}
}