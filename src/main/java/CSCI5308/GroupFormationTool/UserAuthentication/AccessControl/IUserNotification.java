package CSCI5308.GroupFormationTool.UserAuthentication.AccessControl;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;

public interface IUserNotification {
	public Boolean sendUserCredentials(User user) throws AddressException, MessagingException;
	public Boolean sendUserForgetPasswordLink(String email, String passKey);
}