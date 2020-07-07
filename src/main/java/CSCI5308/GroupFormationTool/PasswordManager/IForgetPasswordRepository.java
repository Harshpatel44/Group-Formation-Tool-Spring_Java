package CSCI5308.GroupFormationTool.PasswordManager;

import java.util.List;

public interface IForgetPasswordRepository {
    public String getEmailByBannerID(String bannerid);
    public boolean updatePassword(String bannerid, String newPassword) throws Exception;
    public int getPasswordPolicyNumber();
    public List<String> getPasswordByBannerID(String bannerid, int passNumber) throws Exception;
    public boolean insertToForgetPassword(String bannerid, String passKey);
    public String getBannerIDByPassKey(String passKey) throws Exception;
}
