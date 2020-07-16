package CSCI5308.GroupFormationTool.UserAuthentication;

import CSCI5308.GroupFormationTool.Injector;

public class UserLoginService implements ILoginService {

	public UserLoginService() {
	}

	public UserLoginService(UserLoginRepository loginRepository){
		Injector.instance().getAuthenticationAbstractFactory().setLoginRepository(loginRepository);
	}

	public boolean checkIfUserIsAuthenticated(String bannerid, String password){
		return Injector.instance().getAuthenticationAbstractFactory().getLoginRepository().checkIfUserIsAuthenticated(bannerid, password);
	}

}
