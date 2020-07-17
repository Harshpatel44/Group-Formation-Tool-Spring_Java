package CSCI5308.GroupFormationTool.UserAuthentication;

import CSCI5308.GroupFormationTool.UserManager.IUser;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface IUserNotification {
    Boolean sendUserCredentials(IUser user) throws MessagingException;

    Boolean sendUserForgetPasswordLink(String email, String passKey) throws Exception;
}