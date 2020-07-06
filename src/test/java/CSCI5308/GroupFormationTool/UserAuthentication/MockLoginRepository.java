package CSCI5308.GroupFormationTool.UserAuthentication;

public class MockLoginRepository implements ILoginRepository {

    @Override
    public boolean checkIfUserIsAuthenticated(String bannerid, String password)
    {
        if(bannerid.equals("B00123456") && password.equals("12345"))
        {
            return true;
        }
        return false;
    }

}
