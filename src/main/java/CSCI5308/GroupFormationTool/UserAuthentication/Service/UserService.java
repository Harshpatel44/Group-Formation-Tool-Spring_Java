package CSCI5308.GroupFormationTool.UserAuthentication.Service;

import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserService;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Exceptions.ServiceLayerException;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
	private Pattern pattern;
	private IUserRepository userRepository;
	private IPasswordEncryptor encryptor;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Override
	public boolean createUser(User user) throws ServiceLayerException {
		Boolean success = false;
		userRepository = Injector.instance().getUserRepository();
		encryptor = Injector.instance().getPasswordEncryptor();

		Map<String,String> validationErrors = checkAllValidaations(user);
		if (validationErrors.size() > 0) {
			throw new ServiceLayerException(){{setMapErrors(validationErrors);}};
		}

		user.setPassword(encryptor.encoder(user.getPassword()));

		boolean emailIdExists = userRepository.getUserByEmailId(user);
		

		if (!emailIdExists) {
		 success = userRepository.createUser(user);
		}
		else {
			Map<String,String> errors = new HashMap<String,String>();
			errors.put("emailId", "Email ID already exists");
			throw new ServiceLayerException(){{setMapErrors(errors);}};
		}

		return success;
	}

	private Map<String,String> checkAllValidaations(User user) {

		Map<String,String> errors = new HashMap<String,String>();


		if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
			errors.put("firstName","first name cannot be  null or empty");
		}
		if (user.getLastName() == null || user.getLastName().isEmpty()) {
			errors.put("lastName","last name cannot be null or empty");
		}
		if (!user.getBannerId().isEmpty() && user.getBannerId().length() != 9) {
			errors.put("bannerId","BannerId length is should be equal to 9");
		}
		
		if (user.getEmailId() == null || user.getEmailId().isEmpty()) {
			errors.put("emailId","Email cannot be  null or empty");
		}
		else
			if (!validateEmail(user.getEmailId())){
				errors.put("emailId","Enter valid Email");
			}
		if (user.getPassword() == null || user.getPassword().isEmpty()) {
			errors.put("password","Password cannot be null or empty");
		}
		if (user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
			errors.put("confirmPassword","Confirm Password cannot be  null or empty");
		}
		if(!user.getPassword().equals(user.getConfirmPassword()))
		{
			errors.put("confirmPassword","Passwords and confirm password Doesnt match");
		}

		return errors;
	}
	private boolean validateEmail(final String email) {

		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
