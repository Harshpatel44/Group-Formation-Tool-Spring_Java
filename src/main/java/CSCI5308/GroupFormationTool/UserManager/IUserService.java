package CSCI5308.GroupFormationTool.UserManager;
import CSCI5308.GroupFormationTool.Exceptions.ServiceLayerException;

import java.util.List;

public interface IUserService {
	boolean createUser(IUser user) throws ServiceLayerException;

	boolean checkIfUserExists(String bannerID);

	IUser setUserByBannerId(String bannerId, IUser iUser);

	List<String> getAllBannerIds();

	String checkUserRoleForCourse(String bannerID, String courseID);

	boolean checkIfUserIsGuest(String bannerID);

	ICurrentUser setCurrentUserByBannerID(String BannerID);

	boolean AssignInstructor(IInstructor instructor);
}
