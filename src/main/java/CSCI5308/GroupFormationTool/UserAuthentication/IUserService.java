package CSCI5308.GroupFormationTool.UserAuthentication;
import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.Exceptions.ServiceLayerException;

public interface IUserService {
	boolean createUser(IUser user) throws ServiceLayerException;

	UserPasswordPolicy getUserPasswordPolicy();

	List<String> checkPasswordValidation(String password, Map<String, String> errors);

	UserPasswordPolicyStatus getUserPasswordPolicyStatus();
}
