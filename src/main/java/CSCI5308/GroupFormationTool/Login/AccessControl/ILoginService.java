package CSCI5308.GroupFormationTool.Login.AccessControl;

public interface ILoginService {
    public boolean checkLogin(String bannerid, String password);
    public boolean isUser(String bannerid);

}
