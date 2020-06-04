package CSCI5308.GroupFormationTool.Login.Service;

import CSCI5308.GroupFormationTool.Login.AccessControl.ILoginService;
import CSCI5308.GroupFormationTool.Login.Repository.LoginRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.Security.BCryptEncryption;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class LoginService implements ILoginService {
    public boolean checkLogin(String bannerid, String password)
    {
        LoginRepository repo = new LoginRepository();
        return repo.checkLogin(bannerid,password);
    }

    @Override
    public boolean isUser(String bannerid) {
        LoginRepository repo = new LoginRepository();
        return repo.isUser(bannerid);
    }

    @Override
    public String getEmailByBannerid(String bannerid) {
        LoginRepository repo = new LoginRepository();
        return repo.getEmailByBannerid(bannerid);
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
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("sdcmaster09@gmail.com", "sdc.master");
                }
            });
            Message msg = new MimeMessage(session);
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            msg.setSubject("Password Reset Link");
            resetLink ="localhost:8080/updateNewPassword?passKey="+passKey;
            msg.setContent(resetLink, "text/html");
            Transport.send(msg);
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean insertToForgetPassword(String bannerid, String passKey)
    {
        LoginRepository repo = new LoginRepository();
        return repo.insertToForgetPassword(bannerid,passKey);
    }

    @Override
    public boolean comparePassword(String newPassword, String confirmPassword) {
        if(newPassword.equals(confirmPassword))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public String getBannerIdByPassKey(String passKey) {
        LoginRepository repo = new LoginRepository();
        return repo.getBannerIdByPassKey(passKey);
    }

    @Override
    public boolean updatePassword(String bannerid, String newPassword) {
        LoginRepository repo = new LoginRepository();
        BCryptEncryption encryption = new BCryptEncryption();
        return repo.updatePassword(bannerid,encryption.encoder(newPassword));
    }
}
