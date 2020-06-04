package CSCI5308.GroupFormationTool.UserAuthentication.AccessControl;

import java.util.List;

import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;

public interface IUserRepository {

    public boolean createUser(User user);
    public boolean getUserByEmailId(User user);
    public boolean getUserByBannerId(User user);
    public boolean getUserDetailsOnCourse(User user,String courseId);
    public boolean enrollStudentForCourse(User user,String courseId);
    public List<String> getAllBannerIds();
    public User loadUserByID(String bannerId);

}
