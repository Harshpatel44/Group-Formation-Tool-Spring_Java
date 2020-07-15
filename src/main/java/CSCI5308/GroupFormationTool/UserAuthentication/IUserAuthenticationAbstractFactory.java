package CSCI5308.GroupFormationTool.UserAuthentication;

public interface IUserAuthenticationAbstractFactory {
    IPasswordEncryptor getBCryptEncryption();
}
