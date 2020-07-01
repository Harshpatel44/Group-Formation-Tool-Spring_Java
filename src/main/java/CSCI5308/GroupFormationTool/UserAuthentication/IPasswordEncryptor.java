package CSCI5308.GroupFormationTool.UserAuthentication;

public interface IPasswordEncryptor {
    String encoder(String password);

    Boolean passwordMatch(String password, String encryptedPassword);

}
