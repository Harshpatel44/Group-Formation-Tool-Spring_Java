package CSCI5308.GroupFormationTool.Login.AccessControl;

import javax.mail.MessagingException;

public interface ILoginService {
    public boolean checkLogin(String bannerid, String password);
    public boolean isUser(String bannerid);
    public String getEmailByBannerid(String bannerid);
    public String generatePassKey();
    public boolean sendMail(String email, String passKey) throws MessagingException;
    public boolean insertToForgetPassword(String bannerid, String passKey);
    public boolean comparePassword(String newPassword,String confirmPassword);
    public String getBannerIdByPassKey(String passKey);
    public boolean updatePassword(String bannerid, String password);
}
