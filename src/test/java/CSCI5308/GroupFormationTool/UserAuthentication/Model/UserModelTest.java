package CSCI5308.GroupFormationTool.UserAuthentication.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.UserAuthentication.AccessControll.UserMockDB;

@SpringBootTest
public class UserModelTest {

	@Test
	void getUserFirstName() {
		User user = UserMockDB.setDefault();	
		assertEquals("Arjun", user.getFirstName());

	}

	@Test
	void getUserLastName() {
		User user = UserMockDB.setDefault();
		assertEquals("Suresh", user.getLastName());

	}

	@Test
	void getUserBannerId() {
		User user = UserMockDB.setDefault();
		assertEquals("B00854475", user.getBannerId());

	}

	@Test
	void getUserEmailId() {
		User user = UserMockDB.setDefault();
		assertEquals("arjun14@gmaill.com", user.getEmailId());

	}

	@Test
	void getUserPassword() {
		User user = UserMockDB.setDefault();
		assertEquals("Qwerty12345!", user.getPassword());
		
	}
	
	@Test
	void getContactNumber() {
		User user = UserMockDB.setDefault();

		assertEquals("9787809082", user.getContactNumber());
	}
	
	@Test
	void setUserFirstName() {
		User user = new User();
		assertNull(user.getFirstName());
		user.setFirstName("arjun");
		assertEquals("arjun", user.getFirstName());

	}

	@Test
	void setUserLastName() {
		User user = new User();
		assertNull(user.getLastName());
		user.setLastName("arjun");
		assertEquals("arjun", user.getLastName());

	}

	@Test
	void setUserBannerId() {
		User user = new User();
		assertNull(user.getBannerId());
		user.setBannerId("B00854475");
		assertEquals("B00854475", user.getBannerId());

	}

	@Test
	void setUserEmailId() {
		User user = new User();
		assertNull(user.getEmailId());
		user.setEmailId("arjunstar14@gmail.com");
		assertEquals("arjunstar14@gmail.com", user.getEmailId());

	}

	@Test
	void setUserPassword() {
		User user = new User();
		assertNull(user.getPassword());
		user.setPassword("qwerty12345");
		assertEquals("qwerty12345", user.getPassword());
		
	}
	
	@Test
	void setContactNumber() {
		User user = new User();
		assertNull(user.getContactNumber());
		user.setContactNumber("1234567890");
		assertEquals("1234567890", user.getContactNumber());
	}
}
