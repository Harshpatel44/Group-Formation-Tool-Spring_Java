package CSCI5308.GroupFormationTool.PasswordManager;

import CSCI5308.GroupFormationTool.Injector;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserPasswordPolicyService implements IUserPasswordPolicyService {
    private IUserPasswordPolicyRepository iUserPasswordPolicyRepository;

    public UserPasswordPolicyService(){}

    public UserPasswordPolicyService(UserPasswordPolicyRepository userPasswordPolicyRepository){
        Injector.instance().setUserPasswordPolicyRepository(userPasswordPolicyRepository);
    }

    @Override
    public List<String> checkPasswordValidation(String password, Map<String, String> errors) {
        List<String> policyErrors = new ArrayList<String>();
        try {
			Injector.instance().getUserPasswordPolicyService().getUserPasswordPolicy();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        IUserPasswordPolicy passwordPolicy = Injector.instance().getUserPasswordPolicyRepository().getUserPasswordPolicy();
        IUserPasswordPolicyStatus passwordPolicyStatus = Injector.instance().getUserPasswordPolicyRepository().getUserPasswordPolicyStatus();
        if (passwordPolicyStatus.getMinLength() == 1) {
            if (password.length() < passwordPolicy.getMinLength()) {
                policyErrors.add("Password should have minimum " + +passwordPolicy.getMinLength() + " letters");
            }
        }
        if (passwordPolicyStatus.getMaxLength() == 1) {
            if (password.length() > passwordPolicy.getMaxLength()) {
                policyErrors.add("Password should have maximum " + passwordPolicy.getMaxLength() + " letters");
            }
        }
        if (passwordPolicyStatus.getMinUpperCaseLetter() == 1) {
            if (password.chars().filter((s) -> Character.isUpperCase(s)).count() < passwordPolicy
                    .getMinUpperCaseLetter()) {
                policyErrors.add("Password should have minimum " + +passwordPolicy.getMinUpperCaseLetter()
                        + " uppercaseLetters ");
            }
        }
        if (passwordPolicyStatus.getMinLowerCaseLetter() == 1) {
            if (password.chars().filter((s) -> Character.isLowerCase(s)).count() < passwordPolicy
                    .getMinLowerCaseLetter()) {
                policyErrors.add("Password should have minimum " + passwordPolicy.getMinLowerCaseLetter()
                        + " lowerrcaseLetters");
            }
        }
        if (passwordPolicyStatus.getMinNoOfSymbols() == 1) {
            if (password.split("[!_\\-@#$%^&*()\\[\\]|;',./{}\\\\:\"<>?]", -1).length - 1 < passwordPolicy
                    .getMinNoOfSymbols()) {
                policyErrors.add("Password should have minimum " + +passwordPolicy.getMinNoOfSymbols() + " symbols");
            }
        }
        if (passwordPolicyStatus.getNotAllowedCharacters() == 1) {
            if (StringUtils.containsAny(passwordPolicy.getNotAllowedCharacters(), password)) {
                policyErrors.add(
                        "Following characters are not allowed in password " + passwordPolicy.getNotAllowedCharacters());
            }
        }
        if (policyErrors.size() > 0) {
            errors.put("confirmPassword", String.join(";;", policyErrors));
        }
        return policyErrors;
    }

    @Override
    public IUserPasswordPolicy getUserPasswordPolicy(){
        iUserPasswordPolicyRepository = Injector.instance().getUserPasswordPolicyRepository();
        return iUserPasswordPolicyRepository.getUserPasswordPolicy();
    }


    @Override
    public IUserPasswordPolicyStatus getUserPasswordPolicyStatus(){
        iUserPasswordPolicyRepository =  Injector.instance().getUserPasswordPolicyRepository();;
        return iUserPasswordPolicyRepository.getUserPasswordPolicyStatus();
    }
}
