package CSCI5308.GroupFormationTool.PasswordManager;

import java.util.List;

public interface IForgetPasswordRepository {
    String getEmailByBannerID(String bannerid);

    boolean updatePassword(String bannerid, String newPassword) throws Exception;

    int getPasswordPolicyNumber();

    List<String> getPasswordByBannerID(String bannerid, int passNumber) throws Exception;

    boolean insertToForgetPassword(String bannerid, String passKey);

    String getBannerIDByPassKey(String passKey) throws Exception;
}
