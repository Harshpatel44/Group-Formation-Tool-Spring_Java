package CSCI5308.GroupFormationTool.Login.Service;

import CSCI5308.GroupFormationTool.Injector;

import CSCI5308.GroupFormationTool.Login.AccessControl.ILoginService;
import CSCI5308.GroupFormationTool.Login.Repository.LoginRepository;

import CSCI5308.GroupFormationTool.UserAuthentication.Security.BCryptEncryption;

import javax.validation.constraints.NotNull;
import java.util.List;

public class LoginService implements ILoginService {

	public LoginService() {
	}

	public LoginService(LoginRepository loginRepository) throws Exception {
		Injector.instance().setLoginRepository(loginRepository);
	}

	public boolean checkLogin(String bannerid, String password) {
		return Injector.instance().getLoginRepository().checkLogin(bannerid, password);
	}

	@Override
	public boolean isUser(String bannerid) {
		return Injector.instance().getLoginRepository().isUser(bannerid);
	}

	@Override
	public String getEmailByBannerid(String bannerid) {
		return Injector.instance().getLoginRepository().getEmailByBannerid(bannerid);

	}
	@Override
	public String generatePassKey() {
		// Code taken from geeksforgeeks
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		StringBuilder sb = new StringBuilder(10);
		for (int i = 0; i < 10; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	@Override
	public boolean insertToForgetPassword(String bannerid, String passKey) {
		return Injector.instance().getLoginRepository().insertToForgetPassword(bannerid, passKey);

	}
	
	@Override
	public boolean comparePassword(@NotNull String newPassword, String confirmPassword) {
		if (newPassword.equals(confirmPassword)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getBannerIdByPassKey(String passKey) {
		return Injector.instance().getLoginRepository().getBannerIdByPassKey(passKey);
	}

	@Override
	public boolean updatePassword(String bannerid, String newPassword) {
		BCryptEncryption encryption = new BCryptEncryption();
		return Injector.instance().getLoginRepository().updatePassword(bannerid, encryption.encoder(newPassword));
	}

	@Override
	public List<String> getPasswordByBannerId(String bannerid) {
		return Injector.instance().getLoginRepository().getPasswordByBannerId(bannerid);
	}
}
