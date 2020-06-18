package CSCI5308.GroupFormationTool.Login.AccessControl;

import java.util.List;

public interface ILoginService {
    public boolean checkLogin(String bannerid, String password);
    public boolean isUser(String bannerid);
    public String getEmailByBannerid(String bannerid);
    public boolean insertToForgetPassword(String bannerid, String passKey);
    public String getBannerIdByPassKey(String passKey);
    public boolean updatePassword(String bannerid, String password);
    public List<String> getPasswordByBannerId(String bannerid);
}
