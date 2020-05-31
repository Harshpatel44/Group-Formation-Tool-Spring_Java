package CSCI5308.GroupFormationTool.AdminPanel.AccessControl;


import CSCI5308.GroupFormationTool.AdminPanel.Model.AssignInstructor;
import CSCI5308.GroupFormationTool.AdminPanel.Model.CreateCourse;
import CSCI5308.GroupFormationTool.AdminPanel.Model.DeleteCourse;

public interface IAdminRepository {
    public Boolean createCourseRepo(CreateCourse createCourse);
    public Boolean deleteCourseRepo(DeleteCourse deleteCourse);
    public Boolean assignInstructorRepo(AssignInstructor assignInstructor);
}
