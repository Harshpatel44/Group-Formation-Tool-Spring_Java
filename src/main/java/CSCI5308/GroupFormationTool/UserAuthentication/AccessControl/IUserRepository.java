package CSCI5308.GroupFormationTool.UserAuthentication.AccessControl;

import java.util.List;

import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.UserPasswordPolicy;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.UserPasswordPolicyStatus;

public interface IUserRepository {
    boolean createUser(User user);

    boolean getUserByEmailId(User user);

    boolean getUserByBannerId(User user);

    List<String> getAllBannerIds();

    User loadUserByID(String bannerId);

    UserPasswordPolicy getUserPasswordPolicy();

    UserPasswordPolicyStatus getUserPasswordPolicyStatus();
}
