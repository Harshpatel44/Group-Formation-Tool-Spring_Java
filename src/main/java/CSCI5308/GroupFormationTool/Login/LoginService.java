package CSCI5308.GroupFormationTool.Login;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Login.ILoginService;
import CSCI5308.GroupFormationTool.Login.LoginRepository;

public class LoginService implements ILoginService {

	public LoginService() {
	}

	public LoginService(LoginRepository loginRepository) throws Exception {
		Injector.instance().setLoginRepository(loginRepository);
	}

	public boolean checkLogin(String bannerid, String password) throws Exception {
		return Injector.instance().getLoginRepository().checkLogin(bannerid, password);
	}

	@Override
	public boolean isUser(String bannerid) throws Exception {
		return Injector.instance().getLoginRepository().isUser(bannerid);
	}
}
