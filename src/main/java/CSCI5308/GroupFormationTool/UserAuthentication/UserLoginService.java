package CSCI5308.GroupFormationTool.UserAuthentication;

public class UserLoginService implements ILoginService {

    public UserLoginService() {
    }

    public UserLoginService(UserLoginRepository loginRepository) {
        UserAuthenticationAbstractFactory.instance().setLoginRepository(loginRepository);
    }

    public boolean checkIfUserIsAuthenticated(String bannerid, String password) {
        return UserAuthenticationAbstractFactory.instance().getLoginRepository().checkIfUserIsAuthenticated(bannerid,
                password);
    }

}
