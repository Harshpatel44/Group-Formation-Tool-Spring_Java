package CSCI5308.GroupFormationTool.PasswordManager;

public class UserPasswordPolicy implements IUserPasswordPolicy {

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
			UserPasswordPolicy.instance = new UserPasswordPolicy(minLength, maxLength, minUpperCaseLetter,
					minLowerCaseLetter, minNoOfSymbols, notAllowedCharacters);
		} else {
			UserPasswordPolicy.instance.minLength = minLength;
			UserPasswordPolicy.instance.maxLength = maxLength;
			UserPasswordPolicy.instance.minLowerCaseLetter = minLowerCaseLetter;
			UserPasswordPolicy.instance.minUpperCaseLetter = minUpperCaseLetter;
			UserPasswordPolicy.instance.minNoOfSymbols = minNoOfSymbols;
			UserPasswordPolicy.instance.notAllowedCharacters = notAllowedCharacters;
		}
		return UserPasswordPolicy.instance;

	}

	public static UserPasswordPolicy getInstance() {
		return UserPasswordPolicy.instance;
	}

	@Override
	public Integer getMinLength() {
		return minLength;
	}

	@Override
	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}

	@Override
	public Integer getMinUpperCaseLetter() {
		return minUpperCaseLetter;
	}

	@Override
	public void setMinUpperCaseLetter(Integer minUpperCaseLetter) {
		this.minUpperCaseLetter = minUpperCaseLetter;
	}

	@Override
	public Integer getMaxLength() {
		return maxLength;
	}

	@Override
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	@Override
	public Integer getMinLowerCaseLetter() {
		return minLowerCaseLetter;
	}

	@Override
	public void setMinLowerCaseLetter(Integer minLowerCaseLetter) {
		this.minLowerCaseLetter = minLowerCaseLetter;
	}

	@Override
	public Integer getMinNoOfSymbols() {
		return minNoOfSymbols;
	}

	@Override

	public void setMinNoOfSymbols(Integer minNoOfSymbols) {
		this.minNoOfSymbols = minNoOfSymbols;
	}

	@Override
	public String getNotAllowedCharacters() {
		return notAllowedCharacters;
	}

	@Override
	public void setNotAllowedCharacters(String notAllowedCharacters) {
		this.notAllowedCharacters = notAllowedCharacters;
	}

}
