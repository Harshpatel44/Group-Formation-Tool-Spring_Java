package CSCI5308.GroupFormationTool.PasswordManager;

public interface IPasswordManagerAbstractFactory {
	public IForgetPasswordRepository getForgetPasswordRepository();

	public void setForgetPasswordRepository(IForgetPasswordRepository forgetPasswordRepository);

	public IForgetPasswordService getForgetPasswordService();

	public void setForgetPasswordService(IForgetPasswordService forgetPasswordService);

	public IUserPasswordPolicyRepository getPasswordPolicyRepository();

	public void setPasswordPolicyRepository(IUserPasswordPolicyRepository passwordPolicyRepository);

	public IUserPasswordPolicyService getPasswordPolicyService();

	public void setPasswordPolicyService(IUserPasswordPolicyService passwordPolicyService);
}
