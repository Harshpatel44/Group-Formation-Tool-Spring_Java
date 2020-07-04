package CSCI5308.GroupFormationTool.UserAuthentication;

import CSCI5308.GroupFormationTool.Injector;

public class UserLoginService implements ILoginService {

	public UserLoginService() {
	}

	public UserLoginService(UserLoginRepository loginRepository) throws Exception {
		Injector.instance().setLoginRepository(loginRepository);
	}

	public boolean checkLogin(String bannerid, String password) throws Exception {
		return Injector.instance().getLoginRepository().checkLogin(bannerid, password);
	}

}
