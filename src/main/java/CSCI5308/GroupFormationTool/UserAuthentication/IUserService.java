package CSCI5308.GroupFormationTool.UserAuthentication;
import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.Exceptions.ServiceLayerException;

public interface IUserService {
	boolean createUser(User user) throws Exception;

	UserPasswordPolicy getUserPasswordPolicy() throws Exception;

	List<String> checkPasswordValidation(String password, Map<String, String> errors);

	UserPasswordPolicyStatus getUserPasswordPolicyStatus() throws Exception;
}
