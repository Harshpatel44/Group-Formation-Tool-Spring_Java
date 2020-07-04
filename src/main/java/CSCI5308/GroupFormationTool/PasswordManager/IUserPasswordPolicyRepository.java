package CSCI5308.GroupFormationTool.PasswordManager;

public interface IUserPasswordPolicyRepository {
    UserPasswordPolicy getUserPasswordPolicy();
    UserPasswordPolicyStatus getUserPasswordPolicyStatus();
}
