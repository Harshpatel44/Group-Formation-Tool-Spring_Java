package CSCI5308.GroupFormationTool.UserManager;

import static CSCI5308.GroupFormationTool.ApplicationConstants.guest;

public class User implements IUser {
	private String firstName;
	private String lastName;
	private String bannerId;
	private String emailId;
	private String password;
	private String confirmPassword;
	private String contactNumber;
	private String userType;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public User() {}
	public User(String bannerId,String firstName,String lastName,String emailId,String password,String contactNumber)
	{
		this.bannerId = bannerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
		this.contactNumber = contactNumber;
		this.userType=guest;
	}

    @Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getBannerId() {
		return bannerId;
	}

	@Override
	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	@Override
	public String getEmailId() {
		return emailId;
	}

	@Override
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getConfirmPassword() {
		return confirmPassword;
	}

	@Override
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String getContactNumber() {
		return contactNumber;
	}

	@Override
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
}
