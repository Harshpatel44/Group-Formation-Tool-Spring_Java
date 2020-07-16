package CSCI5308.GroupFormationTool.UserManager;

import static CSCI5308.GroupFormationTool.ApplicationConstants.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import CSCI5308.GroupFormationTool.ApplicationConstants;
import CSCI5308.GroupFormationTool.Exceptions.ServiceLayerException;
import CSCI5308.GroupFormationTool.PasswordManager.IUserPasswordPolicyService;
import CSCI5308.GroupFormationTool.PasswordManager.UserPasswordManagerAbstractFactory;
import CSCI5308.GroupFormationTool.UserAuthentication.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.UserAuthentication.UserAuthenticationAbstractFactory;

@Service
public class UserService implements IUserService {
	private static final Logger LOG = LogManager.getLogger();
	private Pattern pattern;
	private Matcher matcher;
	private IUserRepository userRepository;
	private IPasswordEncryptor iPasswordEncryptor;
	private IUserPasswordPolicyService passwordPolicyService;
	private UserManagerAbstractFactory userManagerAbstractFactory;
	private static final String EMAIL_PATTERN = ApplicationConstants.emailPattern;

	public UserService(){}
	public UserService(IUserRepository userRepository,CurrentUser currentUser){
		UserManagerAbstractFactory.instance().setUserRepository(userRepository);
		CurrentUser.setInstance(currentUser);
	}

	@Override
	public boolean createUser(IUser user) throws ServiceLayerException{
		Boolean success;
		userRepository = UserManagerAbstractFactory.instance().getUserRepository();
		iPasswordEncryptor = UserAuthenticationAbstractFactory.instance().getBCryptEncryption();
		boolean bannerIdExists = userRepository.checkIfUserExists(user.getBannerId());

		Map<String, String> validationErrors = checkAllValidations(user);
		if (validationErrors.size() > 0) {
			throw new ServiceLayerException() {
				{
					LOG.warn("Operation = Create user, Status = validation fail ");
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
					LOG.warn("Operation = Create user, Status = banner Id exists ");
					setMapErrors(errors);
				}
			};
		}
		return success;
	}

	private Map<String, String> checkAllValidations(IUser user) {
		passwordPolicyService = UserPasswordManagerAbstractFactory.instance().getPasswordPolicyService();
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
		} else if (validateEmail(user.getEmailId())==false) {
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
			if (user.getPassword().equals(user.getConfirmPassword())==false) {
				errors.put("confirmPassword", "Passwords and confirm password Does not match");
			} else {
				passwordPolicyService.checkPasswordValidation(user.getPassword(), errors);
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
		IUser iUser = UserManagerAbstractFactory.instance().getUser();
		iUser.setFirstName(firstName);
		iUser.setLastName(lastName);
		iUser.setPassword(password);
		iUser.setBannerId(bannerId);
		iUser.setContactNumber(contactNumber);
		iUser.setEmailId(emailId);
		LOG.info("Operation = Set USer, Status = Success ");
		return iUser;
	}

	@Override
	public boolean checkIfUserExists(String bannerID){
		userRepository = UserManagerAbstractFactory.instance().getUserRepository();
		if(userRepository.checkIfUserExists(bannerID)){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public IUser setUserByBannerId(String bannerId, IUser iUser){
		userRepository = UserManagerAbstractFactory.instance().getUserRepository();
		return userRepository.setUserByBannerId(bannerId,iUser);
	}

	@Override
	public List<String> getAllBannerIds(){
		userRepository = UserManagerAbstractFactory.instance().getUserRepository();
		return userRepository.getAllBannerIds();
	}

	@Override
	public String checkUserRoleForCourse(String bannerID, String courseID){
		userRepository= UserManagerAbstractFactory.instance().getUserRepository();
		return userRepository.checkUserRoleForCourse(bannerID,courseID);
	}

	@Override
	public boolean checkIfUserIsGuest(String bannerID){
		userRepository = UserManagerAbstractFactory.instance().getUserRepository();
		return userRepository.checkIfUserIsGuest(bannerID);
	}

	@Override
	public void setCurrentUserByBannerID(String bannerID){
		userRepository = UserManagerAbstractFactory.instance().getUserRepository();
		if(bannerID.equals(admin)){
			CurrentUser.instance().setBannerId(admin);
			CurrentUser.instance().setFirstName(admin);
			CurrentUser.instance().setLastName(admin);
			LOG.info("Operation = SetCurrentUser if admin, Status = Success ");
		}else{
				IUser iUser = UserManagerAbstractFactory.instance().getUser();
				iUser = userRepository.setUserByBannerId(bannerID,iUser);
				CurrentUser.instance().setBannerId(iUser.getBannerId());
				CurrentUser.instance().setFirstName(iUser.getFirstName());
				CurrentUser.instance().setLastName(iUser.getLastName());
			LOG.info("Operation = SetCurrentUser if not admin, Status = Success ");
		}
	}
}
