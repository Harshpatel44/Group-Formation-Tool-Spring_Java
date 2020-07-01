package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.UserAuthentication.User;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ICourseRepository {

    String addTa(String taId, String courseId) throws Exception;

    boolean getUserDetailsOnCourse(User user, String courseId) throws Exception;

    boolean enrollStudentForCourse(User user, String courseId);

    ArrayList<ArrayList<String>> getAllCourses() throws Exception;

    boolean createCourseRepo(ICreateCourse createCourse) throws SQLException;

    boolean deleteCourseRepo(IDeleteCourse deleteCourse);

}
