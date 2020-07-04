package CSCI5308.GroupFormationTool.UserManager;
import java.util.List;

public interface IUserRepository {
    boolean createUser(IUser user);

    boolean checkIfUserExists(String bannerID);

    IUser setUserByBannerId(String bannerID, IUser iUser);

    List<String> getAllBannerIds();

}
