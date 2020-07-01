package CSCI5308.GroupFormationTool.Login;

public interface ILoginService {
    public boolean checkLogin(String bannerid, String password) throws Exception;
    public boolean isUser(String bannerid) throws Exception;

}
