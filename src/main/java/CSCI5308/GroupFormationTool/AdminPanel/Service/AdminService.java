package CSCI5308.GroupFormationTool.AdminPanel.Service;

import CSCI5308.GroupFormationTool.AdminPanel.AccessControl.IAdminService;
import CSCI5308.GroupFormationTool.AdminPanel.AdminInjector;
import CSCI5308.GroupFormationTool.AdminPanel.Model.AssignInstructor;
import CSCI5308.GroupFormationTool.AdminPanel.Model.CreateCourse;
import CSCI5308.GroupFormationTool.AdminPanel.Model.DeleteCourse;

import CSCI5308.GroupFormationTool.AdminPanel.Repository.AdminRepository;

import java.util.ArrayList;

//  Author: Harsh Patel
public class AdminService implements IAdminService {

    public AdminService() throws Exception {}

    public AdminService(AdminRepository adminRepository) throws Exception {
        AdminInjector.instance().setAdminRepository(adminRepository);
    }


    @Override
    public boolean CreateCourseService(CreateCourse createCourse) throws Exception {
        ArrayList<String> allCourseNames = AdminInjector.instance().getAdminRepository().getAllCourses().get(1);
        for(int i=0;i<allCourseNames.size();i++){
            if(allCourseNames.get(i).equals(createCourse.getCourseName())){
                createCourse.setCourseCreateMessage("Course name exists");
                return false;
            }
        }
        if(AdminInjector.instance().getAdminRepository().createCourseRepo(createCourse)){
            createCourse.setCourseCreateMessage("Course created");
            return true;
        }
        else{
            createCourse.setCourseCreateMessage("Course id exists");
            return false;
        }
    }

    @Override
    public boolean DeleteCourseService(DeleteCourse deleteCourse) throws Exception {
        if(AdminInjector.instance().getAdminRepository().deleteCourseRepo(deleteCourse)){
            deleteCourse.setCourseDeleteMessage("Course deleted");
            deleteCourse.setAllCourseIds(AdminInjector.instance().getAdminRepository().getAllCourses().get(0));
            deleteCourse.setAllCourseNames(AdminInjector.instance().getAdminRepository().getAllCourses().get(1));
            return true;
        }
        else{
            deleteCourse.setCourseDeleteMessage("Course does not exist");
            return false;
        }
    }

    @Override
    public boolean AssignInstructorService(AssignInstructor assignInstructor) throws Exception {
        if(AdminInjector.instance().getAdminRepository().assignInstructorRepo(assignInstructor)){
            assignInstructor.setInstructorAssignMessage("Instructor assigned");
            return true;
        }
        else{
            assignInstructor.setInstructorAssignMessage("User does not exist or already an instructor");
            return false;
        }

    }
}
