package CSCI5308.GroupFormationTool.UserAuthentication.Service;

import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserService;
import CSCI5308.GroupFormationTool.ApplicationConstants;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Exceptions.ServiceLayerException;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.UserPasswordPolicy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
	private Pattern pattern;
	private IUserRepository userRepository;
	private IPasswordEncryptor encryptor;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = ApplicationConstants.emailPattern;

	@Override
	public boolean createUser(User user) throws ServiceLayerException {
		Boolean success = false;
		userRepository = Injector.instance().getUserRepository();
		encryptor = Injector.instance().getPasswordEncryptor();

		Map<String, String> validationErrors = checkAllValidaations(user);
		if (validationErrors.size() > 0) {
			throw new ServiceLayerException() {
				{
					setMapErrors(validationErrors);
				}
			};
		}

		user.setPassword(encryptor.encoder(user.getPassword()));

		boolean bannerIdExists = userRepository.getUserByBannerId(user);

		if (!bannerIdExists) {
			success = userRepository.createUser(user);
		} else {
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("bannerId", "Banner ID already exists");
			throw new ServiceLayerException() {
				{
					setMapErrors(errors);
				}
			};
		}

		return success;
	}

	private Map<String, String> checkAllValidaations(User user) {

		Map<String, String> errors = new HashMap<String, String>();

		if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
			errors.put("firstName", "first name cannot be  null or empty");
		}
		if (user.getLastName() == null || user.getLastName().isEmpty()) {
			errors.put("lastName", "last name cannot be null or empty");
		}
		if (user.getBannerId() != null) {
			if (!user.getBannerId().isEmpty() && user.getBannerId().length() != 9) {
				errors.put("bannerId", "BannerId length is should be equal to 9");
			}
		} else {
			errors.put("bannerId", "BannerId cant be null or empty");
		}

		if (user.getEmailId() == null || user.getEmailId().isEmpty()) {
			errors.put("emailId", "Email cannot be  null or empty");
		} else if (!validateEmail(user.getEmailId())) {
			errors.put("emailId", "Enter valid Email");
		}
		
		if(user.getContactNumber() == null || user.getContactNumber().isEmpty())
		{
			errors.put("contactNumber", "Contact Number cannot be  null or empty");
		}
		else if(user.getContactNumber().length() != 10)
		{
			errors.put("contactNumber", "Contact Number should have only 10 digits");
		}
		if (user.getPassword() == null || user.getPassword().isEmpty()) {
			errors.put("password", "Password cannot be null or empty");
		}
		
		if (user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
			errors.put("confirmPassword", "Confirm Password cannot be  null or empty");
		}
		if (user.getPassword() != null) {
			if (!user.getPassword().equals(user.getConfirmPassword())) {
				errors.put("confirmPassword", "Passwords and confirm password Does not match");
			} else {
				checkPasswordValidation(user.getPassword(), errors);
			}
		}

		return errors;
	}

	public List<String> checkPasswordValidation(String password, Map<String, String> errors) {
		List<String> policyErrors = new ArrayList<String>();
		UserPasswordPolicy passwordPolicy = UserPasswordPolicy.getInstance();
		if (password.length() < passwordPolicy.getMinLength()) {
			policyErrors.add("Password should have minimum " + + passwordPolicy.getMinLength() +" letters" );
		}
		if (password.length() > passwordPolicy.getMaxLength()) {
			policyErrors.add("Password should have maximum "+ passwordPolicy.getMaxLength()+" letters" );
		}

		if (password.chars().filter((s) -> Character.isUpperCase(s)).count() < passwordPolicy.getMinUpperCaseLetter()) {

			policyErrors.add("Password should have minimum "+ + passwordPolicy.getMinUpperCaseLetter()+" uppercaseLetters ");
		}

		if (password.chars().filter((s) -> Character.isLowerCase(s)).count() < passwordPolicy.getMinLowerCaseLetter()) {

			policyErrors
					.add("Password should have minimum " + passwordPolicy.getMinLowerCaseLetter()+" lowerrcaseLetters" );
		}

		if (password.split("[!@#$%^&*()\\[\\]|;',./{}\\\\:\"<>?]", -1).length - 1 < passwordPolicy
				.getMinNoOfSymbols()) {
			policyErrors.add("Password should have minimum "+ + passwordPolicy.getMinNoOfSymbols()+" symbols" );
		}
		if (StringUtils.containsAny(passwordPolicy.getNotAllowedCharacters(), password)) {
			policyErrors.add(
					"Following characters are not allowed in password " + passwordPolicy.getNotAllowedCharacters());
		}
		if (policyErrors.size() > 0) {
			errors.put("confirmPassword", String.join(";;", policyErrors));
		}
		return policyErrors;
	}

	private boolean validateEmail(final String email) {

		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

	@Override
	public UserPasswordPolicy getUserPasswordPolicy() {
		// TODO Auto-generated method stub
		userRepository = Injector.instance().getUserRepository();
		return userRepository.getUserPasswordPolicy();
	}
}
