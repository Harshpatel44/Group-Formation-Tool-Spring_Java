package CSCI5308.GroupFormationTool.UserAuthentication.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserNotification;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;

public class UserNotification implements IUserNotification {

	@Override
	public Boolean sendUserCredentials(User user) throws AddressException, MessagingException {
		String subject = "Login Credentails for GroupFormation Tool";
		String body = "Welcome to the GroupFormation Tool\n Your Login credentials are as follows: \n";
		body += "Username: "+ user.getBannerId();
		body += "\nPassword: "+ user.getBannerId();
		MimeMessage message = Injector.instance().getEmailConfiguration().getMessageCredentials();		
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmailId()));
		message.setSubject(subject);
		message.setText(body);
		Transport.send(message);
		return true;
	}

}
