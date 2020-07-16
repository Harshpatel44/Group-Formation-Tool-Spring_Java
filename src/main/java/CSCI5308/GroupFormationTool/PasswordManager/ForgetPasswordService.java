package CSCI5308.GroupFormationTool.PasswordManager;

import CSCI5308.GroupFormationTool.ApplicationConstants;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.UserAuthentication.BCryptEncryption;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ForgetPasswordService implements IForgetPasswordService {

    public ForgetPasswordService() {
    }

    public ForgetPasswordService(ForgetPasswordRepository forgetPasswordRepository){
        Injector.instance().getPasswordManagerAbstractFactory().setForgetPasswordRepository(forgetPasswordRepository);
    }

    @Override
    public String getEmailByBannerID(String bannerID){
        return Injector.instance().getPasswordManagerAbstractFactory().getForgetPasswordRepository().getEmailByBannerID(bannerID);
    }

    @Override
    public String generatePassKey() {
        // Code taken from geeksforgeeks
        String AlphaNumericString = ApplicationConstants.randomKeyForLink;
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    @Override
    public boolean insertToForgetPassword(String bannerID, String passKey) throws Exception {
        return Injector.instance().getPasswordManagerAbstractFactory().getForgetPasswordRepository().insertToForgetPassword(bannerID, passKey);
    }

    @Override
    public boolean comparePassword(@NotNull String newPassword, String confirmPassword) {
        if(newPassword.equals(confirmPassword)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getBannerIDByPassKey(String passKey) throws Exception {
        return Injector.instance().getPasswordManagerAbstractFactory().getForgetPasswordRepository().getBannerIDByPassKey(passKey);
    }

    @Override
    public boolean updatePassword(String bannerID, String newPassword) throws Exception {
        BCryptEncryption encryption = new BCryptEncryption();
        return Injector.instance().getPasswordManagerAbstractFactory().getForgetPasswordRepository().updatePassword(bannerID, encryption.encoder(newPassword));
    }

    @Override
    public int getPasswordPolicyNumber(){
        return Injector.instance().getPasswordManagerAbstractFactory().getForgetPasswordRepository().getPasswordPolicyNumber();
    }

    @Override
    public List<String> getPasswordByBannerID(String bannerID, int passNumber) throws Exception {
        return Injector.instance().getPasswordManagerAbstractFactory().getForgetPasswordRepository().getPasswordByBannerID(bannerID,passNumber);
    }
}
