package CSCI5308.GroupFormationTool.PasswordManager;

public class UserPasswordPolicyStatus implements IUserPasswordPolicyStatus{
	private static UserPasswordPolicyStatus instance = null;
	private Integer minLength;
	private Integer maxLength;
	private Integer minUpperCaseLetter;
	private Integer minLowerCaseLetter;
	private Integer minNoOfSymbols;
	private Integer notAllowedCharacters;
	
	
	UserPasswordPolicyStatus(Integer minLength, Integer maxLength, Integer minUpperCaseLetter,
							 Integer minLowerCaseLetter, Integer minNoOfSymbols, Integer notAllowedCharacters) {
		this.minLength = minLength;
		this.maxLength = maxLength;
		this.minLowerCaseLetter = minLowerCaseLetter;
		this.minUpperCaseLetter = minUpperCaseLetter;
		this.minNoOfSymbols = minNoOfSymbols;
		this.notAllowedCharacters = notAllowedCharacters;
	}
	public static UserPasswordPolicyStatus setInstance(Integer minLength, Integer maxLength, Integer minUpperCaseLetter,
			Integer minLowerCaseLetter, Integer minNoOfSymbols, Integer notAllowedCharacters) {

		if (instance == null) {
			instance = new UserPasswordPolicyStatus(minLength, maxLength, minUpperCaseLetter, minLowerCaseLetter,
					minNoOfSymbols, notAllowedCharacters);
		}
		else 
		{
			instance.minLength = minLength;
			instance.maxLength = maxLength;
			instance.minLowerCaseLetter = minLowerCaseLetter;
			instance.minUpperCaseLetter = minUpperCaseLetter;
			instance.minNoOfSymbols = minNoOfSymbols;
			instance.notAllowedCharacters = notAllowedCharacters;
		}
		return instance;
		
	}
	public static UserPasswordPolicyStatus getInstance()
	{
		return instance;
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
	public Integer getMaxLength() {
		return maxLength;
	}
	@Override
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
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
	public Integer getNotAllowedCharacters() {
		return notAllowedCharacters;
	}
	@Override
	public void setNotAllowedCharacters(Integer notAllowedCharacters) {
		this.notAllowedCharacters = notAllowedCharacters;
	}
	
}
