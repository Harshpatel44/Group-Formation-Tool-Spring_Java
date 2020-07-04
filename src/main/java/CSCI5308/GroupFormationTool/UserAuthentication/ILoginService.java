package CSCI5308.GroupFormationTool.UserAuthentication;

public interface ILoginService {
    public boolean checkLogin(String bannerid, String password) throws Exception;
}
