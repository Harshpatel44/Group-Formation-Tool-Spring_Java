package CSCI5308.GroupFormationTool.AdminPanel.Repository;

import CSCI5308.GroupFormationTool.AdminPanel.AccessControl.IAdminRepository;
import CSCI5308.GroupFormationTool.AdminPanel.Model.AssignInstructor;
import CSCI5308.GroupFormationTool.AdminPanel.Model.CreateCourse;
import CSCI5308.GroupFormationTool.AdminPanel.Model.DeleteCourse;

//  Author: Harsh Patel
public class AdminRepository implements IAdminRepository {
    @Override
    public Boolean createCourseRepo(CreateCourse createCourse) {
        return null;
    }

    @Override
    public Boolean deleteCourseRepo(DeleteCourse deleteCourse) {
        return null;
    }

    @Override
    public Boolean assignInstructorRepo(AssignInstructor assignInstructor) {
        return null;
    }
}
