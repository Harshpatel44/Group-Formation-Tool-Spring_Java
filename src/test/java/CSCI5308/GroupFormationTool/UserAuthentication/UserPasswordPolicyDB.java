package CSCI5308.GroupFormationTool.UserAuthentication;

public class UserPasswordPolicyDB {
	public static IUserPasswordPolicy getDefault() {
		return IUserPasswordPolicy.setInstance(2,23,1,1,1,"@#");
	}
}
