package CSCI5308.GroupFormationTool.UserAuthentication.Service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import CSCI5308.GroupFormationTool.UserAuthentication.UserNotification;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.UserAuthentication.IUserNotification;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControll.UserMockDB;
import CSCI5308.GroupFormationTool.UserAuthentication.User;

@SpringBootTest
public class UserNotificationTest {
	private IUserNotification userNotification;
	
	@Test
	public void notificationTesting() throws AddressException, MessagingException {
		userNotification = mock(UserNotification.class);
		User user = UserMockDB.setDefault();
		when(userNotification.sendUserCredentials(user)).thenReturn(true);
		assertTrue(userNotification.sendUserCredentials(user));
	}
	@Test
	public void notificationTestingFails() throws AddressException, MessagingException {
		userNotification = mock(UserNotification.class);
		User user = UserMockDB.setDefault();
		when(userNotification.sendUserCredentials(user)).thenReturn(false);
		assertFalse(userNotification.sendUserCredentials(user));
	}
}