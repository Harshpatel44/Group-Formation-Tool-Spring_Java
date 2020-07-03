package CSCI5308.GroupFormationTool.UserAuthentication;

import CSCI5308.GroupFormationTool.UserAuthentication.IUser;
import CSCI5308.GroupFormationTool.UserAuthentication.User;

public class UserMockDB {

	public static IUser setDefault() {
		IUser u = new User() {
			{
				setFirstName("Arjun");
				setLastName("Suresh");
				setEmailId("arjun14@gmaill.com");
				setContactNumber("9787809082");
				setBannerId("B00854475");
				setPassword("Qwerty12345!");
				setConfirmPassword("Qwerty12345!");
			}
		};
		return u;
	}
}
