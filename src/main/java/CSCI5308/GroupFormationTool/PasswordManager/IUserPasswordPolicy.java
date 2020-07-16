package CSCI5308.GroupFormationTool.PasswordManager;

public interface IUserPasswordPolicy {

	public Integer getMinLength();

	public void setMinLength(Integer minLength);

	public Integer getMinUpperCaseLetter();

	public void setMinUpperCaseLetter(Integer minUpperCaseLetter);

	public Integer getMaxLength();

	public void setMaxLength(Integer maxLength);

	public Integer getMinLowerCaseLetter();

	public void setMinLowerCaseLetter(Integer minLowerCaseLetter);

	public Integer getMinNoOfSymbols();

	public void setMinNoOfSymbols(Integer minNoOfSymbols);

	public String getNotAllowedCharacters();

	public void setNotAllowedCharacters(String notAllowedCharacters);
}
