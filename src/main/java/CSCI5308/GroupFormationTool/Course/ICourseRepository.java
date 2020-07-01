package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.UserAuthentication.User;
import CSCI5308.GroupFormationTool.Course.Model.CreateCourse;
import CSCI5308.GroupFormationTool.Course.Model.DeleteCourse;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ICourseRepository {

    String addTa(String taId, String courseId);

    boolean getUserDetailsOnCourse(User user, String courseId);

    boolean enrollStudentForCourse(User user, String courseId);

    ArrayList<ArrayList<String>> getAllCourses() throws SQLException;

    boolean createCourseRepo(CreateCourse createCourse) throws SQLException;

    boolean deleteCourseRepo(DeleteCourse deleteCourse);

}
