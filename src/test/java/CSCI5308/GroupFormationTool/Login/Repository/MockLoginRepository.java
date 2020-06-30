package CSCI5308.GroupFormationTool.Login.Repository;

import CSCI5308.GroupFormationTool.Login.AccessControl.ILoginRepository;

import java.util.ArrayList;
import java.util.List;

public class MockLoginRepository implements ILoginRepository {


    @Override
    public boolean checkLogin(String bannerid, String password)
    {
        if(bannerid.equals("B00123456") && password.equals("12345"))
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean isUser(String bannerid) {
        if(bannerid.equals("B00123456"))
        {
            return true;
        }
        return false;
    }

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

    @Override
    public int getPasswordPolicyNumber() {
        return 3;
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
    public List<String> getPasswordByBannerId(String bannerid, int passNumber) {
        List<String> password = new ArrayList<>();
        password.add("password");
        if(bannerid.equals("B00835088")){
            return password;
        }
        return null;
    }
}
