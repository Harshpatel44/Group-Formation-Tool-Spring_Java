package CSCI5308.GroupFormationTool.Login.AccessControl;

import java.util.List;

import javax.validation.constraints.NotNull;

public interface ILoginService {
    public boolean checkLogin(String bannerid, String password);
    public boolean isUser(String bannerid);
    public String getEmailByBannerid(String bannerid);
    public boolean insertToForgetPassword(String bannerid, String passKey);
    public String getBannerIdByPassKey(String passKey);
    public boolean updatePassword(String bannerid, String password);
    public int getPasswordPolicyNumber();
    public List<String> getPasswordByBannerId(String bannerid, int passNumber);
    public boolean comparePassword(@NotNull String newPassword, String confirmPassword);
    public String generatePassKey();
}
