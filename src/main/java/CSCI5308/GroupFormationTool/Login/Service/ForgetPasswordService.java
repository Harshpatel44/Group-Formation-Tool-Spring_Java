package CSCI5308.GroupFormationTool.Login.Service;

import CSCI5308.GroupFormationTool.ApplicationConstants;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Login.AccessControl.IForgetPasswordService;
import CSCI5308.GroupFormationTool.Login.Repository.ForgetPasswordRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.Security.BCryptEncryption;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ForgetPasswordService implements IForgetPasswordService {

    public ForgetPasswordService() {
    }

    public ForgetPasswordService(ForgetPasswordRepository forgetPasswordRepository) throws Exception {
        Injector.instance().setForgetPasswordRepository(forgetPasswordRepository);
    }

    @Override
    public String getEmailByBannerid(String bannerid) {
        return Injector.instance().getForgetPasswordRepository().getEmailByBannerid(bannerid);

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
    public boolean insertToForgetPassword(String bannerid, String passKey) {
        return Injector.instance().getForgetPasswordRepository().insertToForgetPassword(bannerid, passKey);
    }

    @Override
    public boolean comparePassword(@NotNull String newPassword, String confirmPassword) {
        if (newPassword.equals(confirmPassword)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getBannerIdByPassKey(String passKey) {
        return Injector.instance().getForgetPasswordRepository().getBannerIdByPassKey(passKey);
    }

    @Override
    public boolean updatePassword(String bannerid, String newPassword) {
        BCryptEncryption encryption = new BCryptEncryption();
        return Injector.instance().getForgetPasswordRepository().updatePassword(bannerid, encryption.encoder(newPassword));
    }
    @Override
    public int getPasswordPolicyNumber() {
        return Injector.instance().getForgetPasswordRepository().getPasswordPolicyNumber();
    }

    @Override
    public List<String> getPasswordByBannerId(String bannerid, int passNumber) {
        return Injector.instance().getForgetPasswordRepository().getPasswordByBannerId(bannerid,passNumber);
    }
}
