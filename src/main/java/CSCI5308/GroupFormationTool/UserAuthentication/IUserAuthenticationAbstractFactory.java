package CSCI5308.GroupFormationTool.UserAuthentication;

public interface IUserAuthenticationAbstractFactory {
	public ILoginRepository getLoginRepository(); 

	public void setLoginRepository(ILoginRepository loginRepository); 

	public ILoginService getLoginService(); 

	public void setLoginService(ILoginService loginService);
	public IEmailConfiguration getEmailConfiguration();

	public void setEmailConfiguration(IEmailConfiguration emailConfiguration);

	public IUserNotification getUserNotification(); 

	public void setUserNotification(IUserNotification userNotification); 

	public IPasswordEncryptor getBCryptEncryption();
}
