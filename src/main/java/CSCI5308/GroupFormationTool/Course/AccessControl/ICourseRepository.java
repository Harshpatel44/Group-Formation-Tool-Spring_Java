package CSCI5308.GroupFormationTool.Course.AccessControl;

import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;

public interface ICourseRepository {

    String addTa(String taId, String courseId);
    public boolean getUserDetailsOnCourse(User user, String courseId);
    public boolean enrollStudentForCourse(User user, String courseId);
}
