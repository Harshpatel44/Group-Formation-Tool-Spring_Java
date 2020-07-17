package CSCI5308.GroupFormationTool.PasswordManager;

public class PasswordManagerAbstractConcrete extends UserPasswordManagerAbstractFactory {
    private IForgetPasswordRepository forgetPasswordRepository;
    private IForgetPasswordService forgetPasswordService;
    private IUserPasswordPolicyRepository passwordPolicyRepository;
    private IUserPasswordPolicyService passwordPolicyService;

    public IForgetPasswordRepository getForgetPasswordRepository() {
        if (null == forgetPasswordRepository) {
            forgetPasswordRepository = new ForgetPasswordRepository();
        }
        return forgetPasswordRepository;
    }

    public void setForgetPasswordRepository(IForgetPasswordRepository forgetPasswordRepository) {
        this.forgetPasswordRepository = forgetPasswordRepository;
    }

    public IForgetPasswordService getForgetPasswordService() {
        if (null == forgetPasswordService) {
            forgetPasswordService = new ForgetPasswordService();
        }
        return forgetPasswordService;
    }

    public void setForgetPasswordService(IForgetPasswordService forgetPasswordService) {
        this.forgetPasswordService = forgetPasswordService;
    }

    public IUserPasswordPolicyRepository getPasswordPolicyRepository() {
        if (null == passwordPolicyRepository) {
            passwordPolicyRepository = new UserPasswordPolicyRepository();
        }
        return passwordPolicyRepository;
    }

    public void setPasswordPolicyRepository(IUserPasswordPolicyRepository passwordPolicyRepository) {
        this.passwordPolicyRepository = passwordPolicyRepository;
    }

    public IUserPasswordPolicyService getPasswordPolicyService() {
        if (null == passwordPolicyService) {
            passwordPolicyService = new UserPasswordPolicyService();
        }
        return passwordPolicyService;
    }

    public void setPasswordPolicyService(IUserPasswordPolicyService passwordPolicyService) {
        this.passwordPolicyService = passwordPolicyService;
    }
}
