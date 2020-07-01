package CSCI5308.GroupFormationTool.Login;

import java.util.List;

public interface IForgetPasswordRepository {
    public String getEmailByBannerid(String bannerid);
    public boolean updatePassword(String bannerid, String newPassword);
    public int getPasswordPolicyNumber();
    public List<String> getPasswordByBannerId(String bannerid, int passNumber);
    public boolean insertToForgetPassword(String bannerid, String passKey);
    public String getBannerIdByPassKey(String passKey);
}
