package CSCI5308.GroupFormationTool.Login.AccessControl;

import java.util.List;

public interface ILoginRepository {
    public boolean checkLogin(String bannerid, String password);
    public boolean isUser(String bannerid);
    public String getEmailByBannerid(String bannerid);
    public boolean insertToForgetPassword(String bannerid, String passKey);
    public String getBannerIdByPassKey(String passKey);
    public int getPasswordPolicyNumber();
    public boolean updatePassword(String bannerid,String newPassword);
    public List<String> getPasswordByBannerId(String bannerid,int passNumber);

}
