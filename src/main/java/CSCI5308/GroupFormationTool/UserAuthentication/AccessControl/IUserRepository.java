package CSCI5308.GroupFormationTool.UserAuthentication.AccessControl;

import java.util.List;

import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.UserPasswordPolicy;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.UserPasswordPolicyStatus;

public interface IUserRepository {
    public boolean createUser(User user);
    public boolean getUserByEmailId(User user);
    public boolean getUserByBannerId(User user);
    public List<String> getAllBannerIds();
    public User loadUserByID(String bannerId);
    public UserPasswordPolicy getUserPasswordPolicy();
    public UserPasswordPolicyStatus getUserPasswordPolicyStatus();
}
