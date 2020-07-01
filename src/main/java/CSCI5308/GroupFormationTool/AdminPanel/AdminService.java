package CSCI5308.GroupFormationTool.AdminPanel;

public class AdminService implements IAdminService {

    public AdminService() throws Exception {}

    public AdminService(InstructorAdminRepository adminRepository) throws Exception {
        AdminInjector.instance().setAdminRepository(adminRepository);
    }

    @Override
    public boolean AssignInstructorService(IInstructor iInstructor) throws Exception {
        if(AdminInjector.instance().getAdminRepository().assignInstructorRepo(iInstructor)){
            iInstructor.setInstructorAssignMessage("Instructor assigned");
            return true;
        }
        else{
            iInstructor.setInstructorAssignMessage("User does not exist or already an instructor");
            return false;
        }
    }
}
