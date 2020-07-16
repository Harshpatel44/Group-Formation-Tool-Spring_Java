package CSCI5308.GroupFormationTool.UserAuthentication;

public class UserAuthenticationAbstractFactory implements IUserAuthenticationAbstractFactory {

	private ILoginRepository loginRepository;
	private ILoginService loginService;
	private IEmailConfiguration emailConfiguration;
	private IUserNotification userNotification;

	@Override
	public ILoginRepository getLoginRepository() {
		if (null == loginRepository) {
			loginRepository = new UserLoginRepository();
		}
		return loginRepository;
	}

	@Override
	public void setLoginRepository(ILoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	@Override
	public ILoginService getLoginService() {
		if (null == loginService) {
			loginService = new UserLoginService();
		}
		return loginService;
	}

	@Override
	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	@Override
	public IEmailConfiguration getEmailConfiguration() {
		if(null == emailConfiguration)
    	{
			emailConfiguration = new EmailConfiguration();
    	}
		return emailConfiguration;
	}

	@Override
	public void setEmailConfiguration(IEmailConfiguration emailConfiguration) {
		this.emailConfiguration = emailConfiguration;
	}

	@Override
	public IUserNotification getUserNotification() {
		if (null == userNotification) {
			userNotification = new UserNotification();
		}
		return userNotification;
	}

	@Override
	public void setUserNotification(IUserNotification userNotification) {
		this.userNotification = userNotification;
	}

	@Override
	public IPasswordEncryptor getBCryptEncryption() {
		return new BCryptEncryption();
	}
}
