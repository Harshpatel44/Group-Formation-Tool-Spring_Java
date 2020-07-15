package CSCI5308.GroupFormationTool.UserAuthentication;

public class UserAuthenticationAbstractFactory implements IUserAuthenticationAbstractFactory {

    @Override
    public IPasswordEncryptor getBCryptEncryption(){
        return new BCryptEncryption();
    }
}
