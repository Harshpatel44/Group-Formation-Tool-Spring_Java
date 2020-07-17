package CSCI5308.GroupFormationTool.UserAuthentication;

public interface ILoginService {
    boolean checkIfUserIsAuthenticated(String bannerid, String password) throws Exception;
}
