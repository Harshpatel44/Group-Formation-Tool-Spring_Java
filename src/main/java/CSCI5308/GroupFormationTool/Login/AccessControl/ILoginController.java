package CSCI5308.GroupFormationTool.Login.AccessControl;

import org.springframework.ui.Model;

import javax.mail.MessagingException;

public interface ILoginController {
    public String displaylogin();
    public String getLoginUser(Model model) throws Exception;
    public String displayNewPassword();
    public String newPassword( String newPassword,String confirmPassword, String passKey, Model model) throws Exception;
    public String displayResetPassword();
    public String resetPassword(String bannerid, Model model) throws Exception;
}
