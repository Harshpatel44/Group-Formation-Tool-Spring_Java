package CSCI5308.GroupFormationTool.UserAuthentication;

import java.util.List;

public interface IUserRepository {
    boolean createUser(IUser user);

    boolean getUserByEmailId(IUser user);

    boolean getUserByBannerId(IUser user);

    List<String> getAllBannerIds();

    IUser loadUserByID(String bannerId);

    UserPasswordPolicy getUserPasswordPolicy();

    UserPasswordPolicyStatus getUserPasswordPolicyStatus();
}
