package CSCI5308.GroupFormationTool.UserManager;
import CSCI5308.GroupFormationTool.Exceptions.ServiceLayerException;
import CSCI5308.GroupFormationTool.UserAuthentication.IPasswordEncryptor;

import java.util.List;

public interface IUserService {
	boolean createUser(IUser user) throws ServiceLayerException;

	IUser setUser(String bannerId,String firstName,String lastName,String emailId,String password,String contactNumber);

	boolean checkIfUserExists(String bannerID);

	boolean checkIfUserIsAuthenticated(String bannerID, String Password, IPasswordEncryptor iPasswordEncryptor);

	IUser setUserByBannerId(String bannerId, IUser iUser);

	List<String> getAllBannerIds();

	String checkUserRoleForCourse(String bannerID, String courseID);

	boolean checkIfUserIsGuest(String bannerID);

	ICurrentUser setCurrentUserByBannerID(String BannerID);

	boolean AssignInstructor(IInstructor instructor);
}
