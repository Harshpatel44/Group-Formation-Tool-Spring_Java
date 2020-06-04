package CSCI5308.GroupFormationTool.AdminPanel.AccessControl;


import CSCI5308.GroupFormationTool.AdminPanel.Model.AssignInstructor;
import CSCI5308.GroupFormationTool.AdminPanel.Model.CreateCourse;
import CSCI5308.GroupFormationTool.AdminPanel.Model.DeleteCourse;


import java.sql.SQLException;
import java.util.ArrayList;

public interface IAdminRepository {
    public boolean createCourseRepo(CreateCourse createCourse) throws SQLException;
    public boolean deleteCourseRepo(DeleteCourse deleteCourse);
    public boolean assignInstructorRepo(AssignInstructor assignInstructor) throws SQLException;
    public ArrayList<ArrayList<String>> getAllCourses() throws SQLException;
}
