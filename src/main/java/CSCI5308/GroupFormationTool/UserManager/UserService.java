package CSCI5308.GroupFormationTool.UserManager;

import CSCI5308.GroupFormationTool.ApplicationConstants;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Exceptions.ServiceLayerException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import CSCI5308.GroupFormationTool.UserAuthentication.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.PasswordManager.UserPasswordPolicy;
import CSCI5308.GroupFormationTool.PasswordManager.UserPasswordPolicyStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
	private Pattern pattern;
	private IUserRepository userRepository;
	private IPasswordEncryptor iPasswordEncryptor;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = ApplicationConstants.emailPattern;

	@Override
	public boolean createUser(IUser user) throws ServiceLayerException{
		Boolean success;
		userRepository = Injector.instance().getUserRepository();
		iPasswordEncryptor = Injector.instance().getPasswordEncryptor();
		boolean bannerIdExists = userRepository.checkIfUserExists(user.getBannerId());

		Map<String, String> validationErrors = checkAllValidations(user);
		if (validationErrors.size() > 0) {
			throw new ServiceLayerException() {
				{
					setMapErrors(validationErrors);
				}
			};
		}
		user.setPassword(iPasswordEncryptor.encoder(user.getPassword()));
		if (bannerIdExists==false) {
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

	private Map<String, String> checkAllValidations(IUser user) {
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
		if (user.getContactNumber() == null || user.getContactNumber().isEmpty()) {
			errors.put("contactNumber", "Contact Number cannot be  null or empty");
		} else if (user.getContactNumber().length() != 10) {
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
				Injector.instance().getUserPasswordPolicyService().checkPasswordValidation(user.getPassword(), errors);
			}
		}
		return errors;
	}

	private boolean validateEmail(final String email) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public boolean setCurrentUserByBannerID(String BannerID){
		try{
			IUser iUser = new User();
			iUser = Injector.instance().getUserRepository().setUserByBannerId(BannerID,iUser);
			CurrentUser.instance().setBannerId(iUser.getBannerId());
			CurrentUser.instance().setFirstName(iUser.getFirstName());
			CurrentUser.instance().setLastName(iUser.getLastName());
			return true;
		}
		catch (Exception e){
			return false;
		}
	}
}
