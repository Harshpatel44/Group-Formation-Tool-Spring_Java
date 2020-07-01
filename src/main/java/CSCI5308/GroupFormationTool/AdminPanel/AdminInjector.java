package CSCI5308.GroupFormationTool.AdminPanel;

public class AdminInjector {
    private static AdminInjector instance = null;
    private IAdminService adminService;
    private IInstructorAdminRepository adminRepository;

    private AdminInjector() throws Exception {
        adminService = new AdminService();
        adminRepository = new InstructorAdminRepository();
    }

    public static AdminInjector instance() throws Exception {
        if(instance==null){
            instance = new AdminInjector();
        }
        return instance;
    }


    public IAdminService getAdminService() {
        return adminService;
    }

    public void setAdminService(IAdminService adminService) {
        this.adminService = adminService;
    }

    public IInstructorAdminRepository getAdminRepository() {
        return adminRepository;
    }

    public void setAdminRepository(IInstructorAdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
}
