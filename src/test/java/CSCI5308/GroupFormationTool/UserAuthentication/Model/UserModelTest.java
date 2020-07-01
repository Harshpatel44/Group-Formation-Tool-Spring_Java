package CSCI5308.GroupFormationTool.UserAuthentication.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import CSCI5308.GroupFormationTool.UserAuthentication.IUser;
import CSCI5308.GroupFormationTool.UserAuthentication.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.UserAuthentication.AccessControll.UserMockDB;

@SpringBootTest
public class UserModelTest {

	@Test
	void getUserFirstName() {
		IUser iUser = UserMockDB.setDefault();
		assertEquals("Arjun", iUser.getFirstName());

	}

	@Test
	void getUserLastName() {
		IUser iUser = UserMockDB.setDefault();
		assertEquals("Suresh", iUser.getLastName());

	}

	@Test
	void getUserBannerId() {
		IUser iUser = UserMockDB.setDefault();
		assertEquals("B00854475", iUser.getBannerId());

	}

	@Test
	void getUserEmailId() {
		IUser iUser = UserMockDB.setDefault();
		assertEquals("arjun14@gmaill.com", iUser.getEmailId());

	}

	@Test
	void getUserPassword() {
		IUser iUser = UserMockDB.setDefault();
		assertEquals("Qwerty12345!", iUser.getPassword());
		
	}
	
	@Test
	void getContactNumber() {
		IUser iUser = UserMockDB.setDefault();

		assertEquals("9787809082", iUser.getContactNumber());
	}
	
	@Test
	void setUserFirstName() {
		IUser iUser = new User();
		assertNull(iUser.getFirstName());
		iUser.setFirstName("arjun");
		assertEquals("arjun", iUser.getFirstName());

	}

	@Test
	void setUserLastName() {
		IUser iUser = new User();
		assertNull(iUser.getLastName());
		iUser.setLastName("arjun");
		assertEquals("arjun", iUser.getLastName());

	}

	@Test
	void setUserBannerId() {
		IUser iUser = new User();
		assertNull(iUser.getBannerId());
		iUser.setBannerId("B00854475");
		assertEquals("B00854475", iUser.getBannerId());

	}

	@Test
	void setUserEmailId() {
		IUser iUser = new User();
		assertNull(iUser.getEmailId());
		iUser.setEmailId("arjunstar14@gmail.com");
		assertEquals("arjunstar14@gmail.com", iUser.getEmailId());

	}

	@Test
	void setUserPassword() {
		IUser iUser = new User();
		assertNull(iUser.getPassword());
		iUser.setPassword("qwerty12345");
		assertEquals("qwerty12345", iUser.getPassword());
		
	}
	
	@Test
	void setContactNumber() {
		IUser iUser = new User();
		assertNull(iUser.getContactNumber());
		iUser.setContactNumber("1234567890");
		assertEquals("1234567890", iUser.getContactNumber());
	}
}
