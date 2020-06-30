package CSCI5308.GroupFormationTool.UserAuthentication.AccessControl;

import javax.mail.internet.MimeMessage;

public interface IEmailConfiguration {
	MimeMessage getMessageCredentials();
}
