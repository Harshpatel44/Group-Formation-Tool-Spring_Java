package CSCI5308.GroupFormationTool.UserAuthentication.AccessControl;
import CSCI5308.GroupFormationTool.Exceptions.ServiceLayerException;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.UserPasswordPolicy;

public interface IUserService {

	public boolean createUser(User user) throws ServiceLayerException;
	public UserPasswordPolicy getUserPasswordPolicy();
}
