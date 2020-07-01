package CSCI5308.GroupFormationTool.Login.Service;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Login.AccessControl.ILoginService;
import CSCI5308.GroupFormationTool.Login.Repository.LoginRepository;

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
