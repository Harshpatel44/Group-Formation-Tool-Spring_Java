package CSCI5308.GroupFormationTool.UserManager;

public class UserMockDB {

	public static IUser setDefault() {
		IUser user = new User() {
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
		return user;
	}
}
