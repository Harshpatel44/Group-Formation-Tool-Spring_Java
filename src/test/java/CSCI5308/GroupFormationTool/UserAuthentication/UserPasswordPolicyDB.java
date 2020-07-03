package CSCI5308.GroupFormationTool.UserAuthentication;

import CSCI5308.GroupFormationTool.UserAuthentication.UserPasswordPolicy;

public class UserPasswordPolicyDB {
	public static UserPasswordPolicy getDefault() {
		return UserPasswordPolicy.setInstance(2,23,1,1,1,"@#");
	}
}
