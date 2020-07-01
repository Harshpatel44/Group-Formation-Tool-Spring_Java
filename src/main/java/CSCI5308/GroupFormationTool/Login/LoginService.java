package CSCI5308.GroupFormationTool.Login;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Login.ILoginService;
import CSCI5308.GroupFormationTool.Login.LoginRepository;

public class LoginService implements ILoginService {

	public LoginService() {
	}

	public LoginService(LoginRepository loginRepository){
		Injector.instance().setLoginRepository(loginRepository);
	}

	public boolean checkLogin(String bannerid, String password) {
		return Injector.instance().getLoginRepository().checkLogin(bannerid, password);
	}

	@Override
	public boolean isUser(String bannerid) {
		return Injector.instance().getLoginRepository().isUser(bannerid);
	}
}
