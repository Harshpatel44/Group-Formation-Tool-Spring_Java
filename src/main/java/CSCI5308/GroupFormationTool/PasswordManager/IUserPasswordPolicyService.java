package CSCI5308.GroupFormationTool.PasswordManager;

import java.util.List;
import java.util.Map;

public interface IUserPasswordPolicyService {
    List<String> checkPasswordValidation(String password, Map<String, String> errors);

    IUserPasswordPolicy getUserPasswordPolicy() throws Exception;

    IUserPasswordPolicyStatus getUserPasswordPolicyStatus() throws Exception;
}
