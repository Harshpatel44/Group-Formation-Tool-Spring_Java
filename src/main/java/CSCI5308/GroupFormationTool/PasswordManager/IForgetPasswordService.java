package CSCI5308.GroupFormationTool.PasswordManager;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface IForgetPasswordService {
    String getEmailByBannerID(String bannerID) throws Exception;

    boolean insertToForgetPassword(String bannerID, String passKey) throws Exception;

    String getBannerIDByPassKey(String passKey) throws Exception;

    boolean updatePassword(String bannerID, String password) throws Exception;

    int getPasswordPolicyNumber() throws Exception;

    List<String> getPasswordByBannerID(String bannerID, int passNumber) throws Exception;

    boolean comparePassword(@NotNull String newPassword, String confirmPassword);

    String generatePassKey();
}
