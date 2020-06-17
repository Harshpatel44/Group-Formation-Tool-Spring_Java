package CSCI5308.GroupFormationTool.Login.Service;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Login.AccessControl.ILoginRepository;
import CSCI5308.GroupFormationTool.Login.AccessControl.ILoginService;
import CSCI5308.GroupFormationTool.Login.Repository.LoginRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IEmailConfiguration;
import CSCI5308.GroupFormationTool.UserAuthentication.Security.BCryptEncryption;
import CSCI5308.GroupFormationTool.UserAuthentication.Service.UserService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Properties;

public class LoginService implements ILoginService {

    public LoginService(){}

    public LoginService(LoginRepository loginRepository) throws Exception{
        Injector.instance().setLoginRepository(loginRepository);
    }

    private IEmailConfiguration emailConfiguration;

    public boolean checkLogin(String bannerid, String password) {
        return Injector.instance().getLoginRepository().checkLogin(bannerid,password);
    }

    @Override
    public boolean isUser(String bannerid) {
        return Injector.instance().getLoginRepository().isUser(bannerid);
    }

    @Override
    public String getEmailByBannerid(String bannerid) {
        return Injector.instance().getLoginRepository().getEmailByBannerid(bannerid);

    }

    @Override
    public String generatePassKey()
    {
        // Code taken from geeksforgeeks
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++)
        {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    @Override
    public boolean sendMail(String email, String passKey) {
        String resetLink="";
        try {
            emailConfiguration = Injector.instance().getEmailConfiguration();
            MimeMessage msg = emailConfiguration.getMessageCredentials();
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            msg.setSubject("Password Reset Link");
            resetLink ="http://localhost:8080/updateNewPassword?passKey="+passKey;

//            resetLink ="https://group9-develop.herokuapp.com/updateNewPassword?passKey="+passKey;
            msg.setContent(resetLink, "text/html");
            Transport.send(msg);
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean insertToForgetPassword(String bannerid, String passKey)
    {
        return Injector.instance().getLoginRepository().insertToForgetPassword(bannerid,passKey);

    }

    @Override
    public boolean comparePassword(@NotNull String newPassword, String confirmPassword) {
        if(newPassword.equals(confirmPassword)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String getBannerIdByPassKey(String passKey) {
        return Injector.instance().getLoginRepository().getBannerIdByPassKey(passKey);
    }

    @Override
    public boolean updatePassword(String bannerid, String newPassword) {
        BCryptEncryption encryption = new BCryptEncryption();
        return Injector.instance().getLoginRepository().updatePassword(bannerid,encryption.encoder(newPassword));
    }

    @Override
    public List<String> getPasswordByBannerId(String bannerid) {
        return Injector.instance().getLoginRepository().getPasswordByBannerId(bannerid);
    }
}
