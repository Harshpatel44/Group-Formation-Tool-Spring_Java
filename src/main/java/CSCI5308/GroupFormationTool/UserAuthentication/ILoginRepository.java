package CSCI5308.GroupFormationTool.UserAuthentication;

public interface ILoginRepository {
    boolean checkIfUserIsAuthenticated(String bannerid, String password);
}
