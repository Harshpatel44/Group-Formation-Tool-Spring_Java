package CSCI5308.GroupFormationTool.PasswordManager;

public interface IUserPasswordPolicyRepository {
    IUserPasswordPolicy getUserPasswordPolicy();
    IUserPasswordPolicyStatus getUserPasswordPolicyStatus();
}
