package CSCI5308.GroupFormationTool.AdminPanel.AccessControl;

import CSCI5308.GroupFormationTool.AdminPanel.Model.AssignInstructor;
import CSCI5308.GroupFormationTool.AdminPanel.Model.CreateCourse;
import CSCI5308.GroupFormationTool.AdminPanel.Model.DeleteCourse;

import java.sql.SQLException;

public interface IAdminService {
    public boolean CreateCourseService(CreateCourse createCourse) throws Exception;
    public boolean DeleteCourseService(DeleteCourse deleteCourse) throws Exception;
    public boolean AssignInstructorService(AssignInstructor assignInstructor) throws Exception;
}
