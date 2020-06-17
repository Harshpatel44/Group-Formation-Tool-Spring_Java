package CSCI5308.GroupFormationTool.UserAuthentication.AccessControll;

import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;

public class UserMockDB {

	public static User setDefault() {
		User u = new User() {
			{
				setFirstName("Arjun");
				setLastName("Suresh");
				setEmailId("arjun14@gmaill.com");
				setContactNumber(978780);
				setBannerId("B00854475");
				setPassword("qwerty12345");
				setConfirmPassword("qwerty12345");
			}
		};
		return u;
	}
}
