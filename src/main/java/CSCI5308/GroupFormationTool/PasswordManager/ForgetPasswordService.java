package CSCI5308.GroupFormationTool.PasswordManager;

import CSCI5308.GroupFormationTool.ApplicationConstants;
import CSCI5308.GroupFormationTool.UserAuthentication.BCryptEncryption;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ForgetPasswordService implements IForgetPasswordService {

    public ForgetPasswordService() {
    }

    public ForgetPasswordService(ForgetPasswordRepository forgetPasswordRepository) {
        UserPasswordManagerAbstractFactory.instance().setForgetPasswordRepository(forgetPasswordRepository);
    }

    @Override
    public String getEmailByBannerID(String bannerID) {
        return UserPasswordManagerAbstractFactory.instance().getForgetPasswordRepository().getEmailByBannerID(bannerID);
    }

    @Override
    public String generatePassKey() {
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
        return UserPasswordManagerAbstractFactory.instance().getForgetPasswordRepository().insertToForgetPassword(bannerID, passKey);
    }

    @Override
    public boolean comparePassword(@NotNull String newPassword, String confirmPassword) {
        return newPassword.equals(confirmPassword);
    }

    @Override
    public String getBannerIDByPassKey(String passKey) throws Exception {
        return UserPasswordManagerAbstractFactory.instance().getForgetPasswordRepository().getBannerIDByPassKey(passKey);
    }

    @Override
    public boolean updatePassword(String bannerID, String newPassword) throws Exception {
        BCryptEncryption encryption = new BCryptEncryption();
        return UserPasswordManagerAbstractFactory.instance().getForgetPasswordRepository().updatePassword(bannerID, encryption.encoder(newPassword));
    }

    @Override
    public int getPasswordPolicyNumber() {
        return UserPasswordManagerAbstractFactory.instance().getForgetPasswordRepository().getPasswordPolicyNumber();
    }

    @Override
    public List<String> getPasswordByBannerID(String bannerID, int passNumber) throws Exception {
        return UserPasswordManagerAbstractFactory.instance().getForgetPasswordRepository().getPasswordByBannerID(bannerID, passNumber);
    }
}
