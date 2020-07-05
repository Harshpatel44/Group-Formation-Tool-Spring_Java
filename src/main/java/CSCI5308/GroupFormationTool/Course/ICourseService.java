package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.UserManager.IInstructor;

import java.util.Dictionary;

public interface ICourseService {

    boolean assignInstructorForCourse(IInstructor instructor);

    boolean roleAllowInstructorAndTA(String userType);

    boolean roleAllowInstructor(String userType);

    String addTAForCourse(String taId, String courseId) throws Exception;

    Dictionary coursesWithIdForDropdown() throws Exception;

    boolean createCourse(ICreateCourse createCourse) throws Exception;

    boolean deleteCourse(IDeleteCourse deleteCourse) throws Exception;
}
