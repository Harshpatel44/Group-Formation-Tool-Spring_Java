package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.UserManager.IInstructor;
import CSCI5308.GroupFormationTool.UserManager.IUser;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ICourseRepository {

    boolean assignInstructorForCourse(IInstructor instructor);

    String addTaForCourse(String taId, String courseId) throws Exception;

    boolean getUserDetailsOnCourse(IUser user, String courseId) throws Exception;

    boolean enrollStudentForCourse(IUser user, String courseId);

    ArrayList<ArrayList<String>> getAllCourses() throws Exception;

    boolean createCourseRepo(ICreateCourse createCourse) throws SQLException;

    boolean deleteCourseRepo(IDeleteCourse deleteCourse);
}
