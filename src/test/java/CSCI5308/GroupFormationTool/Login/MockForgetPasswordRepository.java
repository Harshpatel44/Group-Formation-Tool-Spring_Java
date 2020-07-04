package CSCI5308.GroupFormationTool.Login;

import CSCI5308.GroupFormationTool.PasswordManager.IForgetPasswordRepository;

import java.util.ArrayList;
import java.util.List;

public class MockForgetPasswordRepository implements IForgetPasswordRepository {
    @Override
    public String getEmailByBannerid(String bannerid) {
        String email = new String();
        if(bannerid.equals("B00123456"))
        {
            email = "rutikapatel09@gmail.com";
        }
        return email;
    }

    @Override
    public boolean updatePassword(String bannerid, String newPassword) {
        if(bannerid.equals("B00123456") && newPassword.equals("12345"))
        {
            return true;
        }
        return false;
    }

    @Override
    public int getPasswordPolicyNumber() {
        return 3;
    }

    @Override
    public List<String> getPasswordByBannerId(String bannerid, int passNumber) {
        List<String> password = new ArrayList<>();
        password.add("password");
        if(bannerid.equals("B00835088")){
            return password;
        }
        return null;
    }

    @Override
    public boolean insertToForgetPassword(String bannerid, String passKey) {
        if(bannerid.equals("B00123456") && passKey.equals("e3Twq6Hyip"))
        {
            return true;
        }
        return false;
    }

    @Override
    public String getBannerIdByPassKey(String passKey) {
        String bannerid = new String();
        if(passKey.equals("e3Twq6Hyip"))
        {
            bannerid =  "B00123456";
        }
        return bannerid;
    }
}
