package CSCI5308.GroupFormationTool.Login.Service;

import CSCI5308.GroupFormationTool.Login.AccessControl.ILoginService;
import CSCI5308.GroupFormationTool.Login.Repository.LoginRepository;

public class LoginService implements ILoginService {
    public boolean checkLogin(String bannerid, String password)
    {
        LoginRepository repo = new LoginRepository();
        return repo.checkLogin(bannerid,password);
    }
}
