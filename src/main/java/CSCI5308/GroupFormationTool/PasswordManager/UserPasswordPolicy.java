package CSCI5308.GroupFormationTool.PasswordManager;

public class UserPasswordPolicy{

	private static UserPasswordPolicy instance = null;
	private Integer minLength;
	private Integer maxLength;
	private Integer minUpperCaseLetter;
	private Integer minLowerCaseLetter;
	private Integer minNoOfSymbols;
	private String notAllowedCharacters;

	private UserPasswordPolicy(Integer minLength, Integer maxLength, Integer minUpperCaseLetter,
			Integer minLowerCaseLetter, Integer minNoOfSymbols, String notAllowedCharacters) {
		this.minLength = minLength;
		this.maxLength = maxLength;
		this.minLowerCaseLetter = minLowerCaseLetter;
		this.minUpperCaseLetter = minUpperCaseLetter;
		this.minNoOfSymbols = minNoOfSymbols;
		this.notAllowedCharacters = notAllowedCharacters;
	}

	public static UserPasswordPolicy setInstance(Integer minLength, Integer maxLength, Integer minUpperCaseLetter,
										   Integer minLowerCaseLetter, Integer minNoOfSymbols, String notAllowedCharacters) {

		if (UserPasswordPolicy.instance == null) {
			UserPasswordPolicy.instance = new UserPasswordPolicy(minLength, maxLength, minUpperCaseLetter, minLowerCaseLetter,
					minNoOfSymbols, notAllowedCharacters);
		}
		else
		{
			UserPasswordPolicy.instance.minLength = minLength;
			UserPasswordPolicy.instance.maxLength = maxLength;
			UserPasswordPolicy.instance.minLowerCaseLetter = minLowerCaseLetter;
			UserPasswordPolicy.instance.minUpperCaseLetter = minUpperCaseLetter;
			UserPasswordPolicy.instance.minNoOfSymbols = minNoOfSymbols;
			UserPasswordPolicy.instance.notAllowedCharacters = notAllowedCharacters;
		}
		return UserPasswordPolicy.instance;

	}

	public static UserPasswordPolicy getInstance()
	{
		return UserPasswordPolicy.instance;
	}

	public Integer getMinLength() {
		return minLength;
	}


	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}


	public Integer getMinUpperCaseLetter() {
		return minUpperCaseLetter;
	}


	public void setMinUpperCaseLetter(Integer minUpperCaseLetter) {
		this.minUpperCaseLetter = minUpperCaseLetter;
	}


	public Integer getMaxLength() {
		return maxLength;
	}


	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}


	public Integer getMinLowerCaseLetter() {
		return minLowerCaseLetter;
	}


	public void setMinLowerCaseLetter(Integer minLowerCaseLetter) {
		this.minLowerCaseLetter = minLowerCaseLetter;
	}


	public Integer getMinNoOfSymbols() {
		return minNoOfSymbols;
	}


	public void setMinNoOfSymbols(Integer minNoOfSymbols) {
		this.minNoOfSymbols = minNoOfSymbols;
	}


	public String getNotAllowedCharacters() {
		return notAllowedCharacters;
	}


	public void setNotAllowedCharacters(String notAllowedCharacters) {
		this.notAllowedCharacters = notAllowedCharacters;
	}

}
