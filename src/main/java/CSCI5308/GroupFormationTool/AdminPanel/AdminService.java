package CSCI5308.GroupFormationTool.AdminPanel;

import CSCI5308.GroupFormationTool.AdminPanel.IAdminService;
import CSCI5308.GroupFormationTool.AdminPanel.AdminInjector;
import CSCI5308.GroupFormationTool.AdminPanel.Instructor;

import CSCI5308.GroupFormationTool.AdminPanel.InstructorAdminRepository;

public class AdminService implements IAdminService {

    public AdminService() throws Exception {}

    public AdminService(InstructorAdminRepository adminRepository) throws Exception {
        AdminInjector.instance().setAdminRepository(adminRepository);
    }

    @Override
    public boolean AssignInstructorService(Instructor instructor) throws Exception {
        if(AdminInjector.instance().getAdminRepository().assignInstructorRepo(instructor)){
            instructor.setInstructorAssignMessage("Instructor assigned");
            return true;
        }
        else{
            instructor.setInstructorAssignMessage("User does not exist or already an instructor");
            return false;
        }
    }
}
