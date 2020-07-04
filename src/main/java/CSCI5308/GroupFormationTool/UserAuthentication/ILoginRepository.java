package CSCI5308.GroupFormationTool.UserAuthentication;

public interface ILoginRepository {
    public boolean checkLogin(String bannerid, String password);
}
