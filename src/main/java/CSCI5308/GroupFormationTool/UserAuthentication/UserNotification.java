package CSCI5308.GroupFormationTool.UserAuthentication;

import static CSCI5308.GroupFormationTool.ApplicationConstants.passwordResetSubject;
import static CSCI5308.GroupFormationTool.ApplicationConstants.userNotificationBody;
import static CSCI5308.GroupFormationTool.ApplicationConstants.userNotificationSubject;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import CSCI5308.GroupFormationTool.ApplicationConstants;
import CSCI5308.GroupFormationTool.UserManager.IUser;

public class UserNotification implements IUserNotification {

	@Override
	public Boolean sendUserCredentials(IUser user) throws MessagingException {
		String subject = userNotificationSubject;
		String body = userNotificationBody;
		body += "Username: " + user.getBannerId();
		body += "\nPassword: " + user.getBannerId();
		MimeMessage message = UserAuthenticationAbstractFactory.instance().getEmailConfiguration()
				.getMessageCredentials();
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmailId()));
		message.setSubject(subject);
		message.setText(body);
		Transport.send(message);
		return true;
	}

	public Boolean sendUserForgetPasswordLink(String email, String passKey) {
		String resetLink = "";
		try {
			MimeMessage msg = UserAuthenticationAbstractFactory.instance().getEmailConfiguration()
					.getMessageCredentials();
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			msg.setSubject(passwordResetSubject);
			resetLink = ApplicationConstants.restLink + ApplicationConstants.updatePasswordLink + passKey;
			msg.setContent(resetLink, "text/html");
			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return true;
	}
}
