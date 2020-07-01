package CSCI5308.GroupFormationTool.UserAuthentication;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import CSCI5308.GroupFormationTool.ApplicationConstants;
import CSCI5308.GroupFormationTool.Injector;

public class UserNotification implements IUserNotification {

	@Override
	public Boolean sendUserCredentials(IUser user) throws Exception {
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
	
	public Boolean sendUserForgetPasswordLink(String email, String passKey) throws Exception {
        String resetLink="";
        try {
            
            MimeMessage msg = Injector.instance().getEmailConfiguration().getMessageCredentials();
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            msg.setSubject("Password Reset Link");
            resetLink = ApplicationConstants.restLink + ApplicationConstants.updatePasswordLink +passKey;
            msg.setContent(resetLink, "text/html");
            Transport.send(msg);
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
        return true;
    }
}
