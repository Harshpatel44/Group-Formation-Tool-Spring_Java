package CSCI5308.GroupFormationTool.Course;

import java.sql.SQLException;
import java.util.Dictionary;

public interface ICourseService {
    String addTa(String taId, String courseId);
    boolean checkRole(String userType);
    boolean checkUserType(String userType);
    Dictionary CoursesWithIdForDropdown() throws SQLException;
    boolean CreateCourseService(ICreateCourse createCourse) throws Exception;
    boolean DeleteCourseService(IDeleteCourse deleteCourse) throws Exception;
}
