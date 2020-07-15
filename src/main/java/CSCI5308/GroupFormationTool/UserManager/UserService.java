package CSCI5308.GroupFormationTool.UserManager;

import CSCI5308.GroupFormationTool.ApplicationConstants;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Exceptions.ServiceLayerException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import CSCI5308.GroupFormationTool.UserAuthentication.IPasswordEncryptor;
import org.springframework.stereotype.Service;

import static CSCI5308.GroupFormationTool.ApplicationConstants.*;

@Service
public class UserService implements IUserService {
	private Pattern pattern;
	private IUserRepository userRepository;
	private IPasswordEncryptor iPasswordEncryptor;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = ApplicationConstants.emailPattern;

	public UserService(){}
	public UserService(IUserRepository userRepository,CurrentUser currentUser){
		Injector.instance().setUserRepository(userRepository);
		CurrentUser.setInstance(currentUser);
	}

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

	@Override
	public IUser setUser(String bannerId,String firstName,String lastName,String emailId,String password,String contactNumber){
		IUser iUser = new User();
		iUser.setFirstName(firstName);
		iUser.setLastName(lastName);
		iUser.setPassword(password);
		iUser.setBannerId(bannerId);
		iUser.setContactNumber(contactNumber);
		iUser.setEmailId(emailId);
		return iUser;
	}

	@Override
	public boolean checkIfUserExists(String bannerID){
		if(Injector.instance().getUserRepository().checkIfUserExists(bannerID)){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public IUser setUserByBannerId(String bannerId, IUser iUser){
		return Injector.instance().getUserRepository().setUserByBannerId(bannerId,iUser);
	}

	@Override
	public List<String> getAllBannerIds(){
		return Injector.instance().getUserRepository().getAllBannerIds();
	}

	@Override
	public String checkUserRoleForCourse(String bannerID, String courseID){
		return Injector.instance().getUserRepository().checkUserRoleForCourse(bannerID,courseID);
	}

	@Override
	public boolean checkIfUserIsGuest(String bannerID){
		return Injector.instance().getUserRepository().checkIfUserIsGuest(bannerID);
	}

	@Override
	public void setCurrentUserByBannerID(String bannerID){
		if(bannerID.equals(admin)){
			CurrentUser.instance().setBannerId(admin);
			CurrentUser.instance().setFirstName(admin);
			CurrentUser.instance().setLastName(admin);
		}else{
				IUser iUser = new User();
				iUser = Injector.instance().getUserRepository().setUserByBannerId(bannerID,iUser);
				CurrentUser.instance().setBannerId(iUser.getBannerId());
				CurrentUser.instance().setFirstName(iUser.getFirstName());
				CurrentUser.instance().setLastName(iUser.getLastName());
		}
	}
}
