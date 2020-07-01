package CSCI5308.GroupFormationTool.AdminPanel;

import CSCI5308.GroupFormationTool.Injector;

public class AdminService implements IAdminService {

    public AdminService() throws Exception {}

    public AdminService(InstructorAdminRepository adminRepository) throws Exception {
        Injector.instance().setInstructorAdminRepository(adminRepository);
    }

    @Override
    public boolean AssignInstructorService(IInstructor instructor) throws Exception {
        if(Injector.instance().getInstructorAdminRepository().assignInstructorRepo(instructor)){
            instructor.setInstructorAssignMessage("Instructor assigned");
            return true;
        }
        else{
            instructor.setInstructorAssignMessage("User does not exist or already an instructor");
            return false;
        }
    }
}
