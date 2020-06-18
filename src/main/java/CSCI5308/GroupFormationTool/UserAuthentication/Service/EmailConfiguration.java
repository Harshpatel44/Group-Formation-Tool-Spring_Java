package CSCI5308.GroupFormationTool.UserAuthentication.Service;

import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IEmailConfiguration;

public class EmailConfiguration implements IEmailConfiguration {
	
	private static MimeMessage message;
	private static Session session = null;
	
	@Override
	public MimeMessage getMessageCredentials() {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port",587);
		session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("sdcmaster09@gmail.com","sdc.master"); 
			}
		});
		message = new MimeMessage(session);
		return message;
	}
}
