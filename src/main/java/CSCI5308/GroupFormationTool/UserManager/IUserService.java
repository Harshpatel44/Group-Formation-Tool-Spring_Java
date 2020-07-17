package CSCI5308.GroupFormationTool.UserManager;

import CSCI5308.GroupFormationTool.Exceptions.ServiceLayerException;

import java.util.List;

public interface IUserService {
    boolean createUser(IUser user) throws ServiceLayerException;

    IUser setUser(String bannerId, String firstName, String lastName, String emailId, String password, String contactNumber);

    boolean checkIfUserExists(String bannerID);

    IUser setUserByBannerId(String bannerId, IUser iUser);

    List<String> getAllBannerIds();

    String checkUserRoleForCourse(String bannerID, String courseID);

    boolean checkIfUserIsGuest(String bannerID);

    void setCurrentUserByBannerID(String BannerID);
}
