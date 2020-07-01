package CSCI5308.GroupFormationTool.UserAuthentication;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface IUserNotification {
	Boolean sendUserCredentials(IUser user) throws AddressException, MessagingException, Exception;

	Boolean sendUserForgetPasswordLink(String email, String passKey) throws Exception;
}