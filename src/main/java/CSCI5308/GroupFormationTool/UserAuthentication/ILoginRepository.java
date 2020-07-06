package CSCI5308.GroupFormationTool.UserAuthentication;

public interface ILoginRepository {
    public boolean checkIfUserIsAuthenticated(String bannerid, String password);
}
