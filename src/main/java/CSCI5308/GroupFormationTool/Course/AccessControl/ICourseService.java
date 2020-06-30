package CSCI5308.GroupFormationTool.Course.AccessControl;

import CSCI5308.GroupFormationTool.Course.Model.CreateCourse;
import CSCI5308.GroupFormationTool.Course.Model.DeleteCourse;

import java.sql.SQLException;
import java.util.Dictionary;

public interface ICourseService {

    String addTa(String taId, String courseId);

    boolean checkRole(String userType);

    boolean checkUserType(String userType);

    Dictionary CoursesWithIdForDropdown() throws SQLException;

    boolean CreateCourseService(CreateCourse createCourse) throws Exception;

    boolean DeleteCourseService(DeleteCourse deleteCourse) throws Exception;
}
