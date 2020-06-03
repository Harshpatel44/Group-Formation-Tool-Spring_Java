package CSCI5308.GroupFormationTool.Login.Repository;

import CSCI5308.GroupFormationTool.Login.AccessControl.ILoginRepository;

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
}
