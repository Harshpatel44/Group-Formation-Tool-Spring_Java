package CSCI5308.GroupFormationTool.UserAuthentication;

import java.util.List;

public interface IUserRepository {
    boolean createUser(IUser user);

    boolean getUserByEmailId(IUser user) throws Exception;

    boolean getUserByBannerId(IUser user);

    List<String> getAllBannerIds() throws Exception;

    IUser loadUserByID(String bannerId);

    UserPasswordPolicy getUserPasswordPolicy() throws Exception;

    UserPasswordPolicyStatus getUserPasswordPolicyStatus();
}
