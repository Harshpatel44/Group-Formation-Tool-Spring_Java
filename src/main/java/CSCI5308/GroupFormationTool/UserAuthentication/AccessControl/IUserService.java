package CSCI5308.GroupFormationTool.UserAuthentication.AccessControl;
import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.Exceptions.ServiceLayerException;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.UserPasswordPolicy;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.UserPasswordPolicyStatus;

public interface IUserService {
	boolean createUser(User user) throws ServiceLayerException;

	UserPasswordPolicy getUserPasswordPolicy();

	List<String> checkPasswordValidation(String password, Map<String, String> errors);

	UserPasswordPolicyStatus getUserPasswordPolicyStatus();
}
