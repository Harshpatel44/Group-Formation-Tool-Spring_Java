package CSCI5308.GroupFormationTool.UserAuthentication;

public abstract class UserAuthenticationAbstractFactory {
    private static UserAuthenticationAbstractFactory instance = null;

    public static UserAuthenticationAbstractFactory instance() {

        if (instance == null) {
            instance = new UserAuthenticationAbstractConcrete();
        }
        return instance;
    }

    public abstract ILoginRepository getLoginRepository();

    public abstract void setLoginRepository(ILoginRepository loginRepository);

    public abstract ILoginService getLoginService();

    public abstract void setLoginService(ILoginService loginService);

    public abstract IEmailConfiguration getEmailConfiguration();

    public abstract void setEmailConfiguration(IEmailConfiguration emailConfiguration);

    public abstract IUserNotification getUserNotification();

    public abstract void setUserNotification(IUserNotification userNotification);

    public abstract IPasswordEncryptor getBCryptEncryption();
}
