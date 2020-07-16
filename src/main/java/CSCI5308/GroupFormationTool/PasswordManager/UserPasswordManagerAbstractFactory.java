package CSCI5308.GroupFormationTool.PasswordManager;

public abstract class UserPasswordManagerAbstractFactory {


	private static UserPasswordManagerAbstractFactory instance = null;

    public static UserPasswordManagerAbstractFactory instance(){

        if (instance == null) {
            instance = new PasswordManagerAbstractConcrete();
        }
        return instance;
    }
	public abstract IForgetPasswordRepository getForgetPasswordRepository();

	public abstract void setForgetPasswordRepository(IForgetPasswordRepository forgetPasswordRepository);

	public abstract IForgetPasswordService getForgetPasswordService();

	public abstract void setForgetPasswordService(IForgetPasswordService forgetPasswordService);

	public abstract IUserPasswordPolicyRepository getPasswordPolicyRepository();

	public abstract void setPasswordPolicyRepository(IUserPasswordPolicyRepository passwordPolicyRepository);

	public abstract IUserPasswordPolicyService getPasswordPolicyService();

	public abstract void setPasswordPolicyService(IUserPasswordPolicyService passwordPolicyService);



}
