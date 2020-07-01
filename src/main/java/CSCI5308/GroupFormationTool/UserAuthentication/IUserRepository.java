package CSCI5308.GroupFormationTool.UserAuthentication;

import java.util.List;

public interface IUserRepository {
    boolean createUser(User user);

    boolean getUserByEmailId(User user);

    boolean getUserByBannerId(User user);

    List<String> getAllBannerIds();

    User loadUserByID(String bannerId);

    UserPasswordPolicy getUserPasswordPolicy();

    UserPasswordPolicyStatus getUserPasswordPolicyStatus();
}
