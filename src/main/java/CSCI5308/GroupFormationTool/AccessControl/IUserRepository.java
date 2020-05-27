package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.Model.User;

public interface IUserRepository {

    public boolean createUser(User user);
    public boolean getUserByEmailId(User user);
    public boolean getUserByBannerId(User user);

}
