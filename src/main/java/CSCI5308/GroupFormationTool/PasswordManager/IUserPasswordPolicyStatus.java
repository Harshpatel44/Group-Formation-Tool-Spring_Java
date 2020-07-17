package CSCI5308.GroupFormationTool.PasswordManager;

public interface IUserPasswordPolicyStatus {

    Integer getMinLength();

    void setMinLength(Integer minLength);

    Integer getMinUpperCaseLetter();

    void setMinUpperCaseLetter(Integer minUpperCaseLetter);

    Integer getMaxLength();

    void setMaxLength(Integer maxLength);

    Integer getMinLowerCaseLetter();

    void setMinLowerCaseLetter(Integer minLowerCaseLetter);

    Integer getMinNoOfSymbols();

    void setMinNoOfSymbols(Integer minNoOfSymbols);

    Integer getNotAllowedCharacters();

    void setNotAllowedCharacters(Integer notAllowedCharacters);
}
