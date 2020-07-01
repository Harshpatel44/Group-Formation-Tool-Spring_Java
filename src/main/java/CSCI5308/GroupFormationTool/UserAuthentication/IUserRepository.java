package CSCI5308.GroupFormationTool.UserAuthentication;

import java.util.List;

public interface IUserRepository {
    boolean createUser(User user);

    boolean getUserByEmailId(User user) throws Exception;

    boolean getUserByBannerId(User user);

    List<String> getAllBannerIds() throws Exception;

    User loadUserByID(String bannerId);

    UserPasswordPolicy getUserPasswordPolicy() throws Exception;

    UserPasswordPolicyStatus getUserPasswordPolicyStatus();
}
