package CSCI5308.GroupFormationTool.UserManager;
import CSCI5308.GroupFormationTool.UserAuthentication.IPasswordEncryptor;

import java.util.List;

public interface IUserRepository {
    boolean createUser(IUser user);

    boolean checkIfUserExists(String bannerID);

    IUser setUserByBannerId(String bannerID, IUser iUser);

    List<String> getAllBannerIds();

    String checkUserRoleForCourse(String bannerID, String courseID);

    public boolean checkIfUserIsGuest(String bannerID);
}
