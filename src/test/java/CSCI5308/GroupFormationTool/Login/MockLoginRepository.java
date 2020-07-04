package CSCI5308.GroupFormationTool.Login;

import CSCI5308.GroupFormationTool.UserAuthentication.ILoginRepository;

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
}
