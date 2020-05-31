package CSCI5308.GroupFormationTool.AdminPanel.AccessControl;

import CSCI5308.GroupFormationTool.AdminPanel.Model.AssignInstructor;
import CSCI5308.GroupFormationTool.AdminPanel.Model.CreateCourse;
import CSCI5308.GroupFormationTool.AdminPanel.Model.DeleteCourse;

public interface IAdminService {
    public Boolean CreateCourseService(CreateCourse createCourse);
    public Boolean DeleteCourseService(DeleteCourse deleteCourse);
    public Boolean AssignInstructorService(AssignInstructor assignInstructor);
}
