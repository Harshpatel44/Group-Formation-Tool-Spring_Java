package CSCI5308.GroupFormationTool.Login;

public interface ILoginRepository {
    public boolean checkLogin(String bannerid, String password);
    public boolean isUser(String bannerid);
}