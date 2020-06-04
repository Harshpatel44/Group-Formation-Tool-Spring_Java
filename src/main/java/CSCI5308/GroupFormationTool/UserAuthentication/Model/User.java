package CSCI5308.GroupFormationTool.UserAuthentication.Model;




public class User {

	private long id;

	private String firstName;

	private String lastName;

	private String bannerId;


	private String emailId;


	private String password;


	private String confirmPassword;
	
	private Integer contactNumber;



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBannerId() {
		return bannerId;
	}

	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Integer getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Integer contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public User setDefaults() {
		User u = new User() {{setFirstName("Arjun");setLastName("Suresh");setEmailId("arjun14@gmaill.com");setContactNumber(978780);setBannerId("B00854475");setPassword("qwerty12345");}};
		return u;
		
	}



}
