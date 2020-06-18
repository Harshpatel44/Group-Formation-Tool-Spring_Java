package CSCI5308.GroupFormationTool.AdminPanel;


import CSCI5308.GroupFormationTool.AdminPanel.AccessControl.IAdminController;
import CSCI5308.GroupFormationTool.AdminPanel.AccessControl.IInstructorAdminRepository;
import CSCI5308.GroupFormationTool.AdminPanel.AccessControl.IAdminService;
import CSCI5308.GroupFormationTool.AdminPanel.Controller.AdminController;
import CSCI5308.GroupFormationTool.AdminPanel.Repository.InstructorAdminRepository;
import CSCI5308.GroupFormationTool.AdminPanel.Service.AdminService;

public class AdminInjector {
    private static AdminInjector instance = null;

    private IAdminController adminController;
    private IAdminService adminService;
    private IInstructorAdminRepository adminRepository;

    private AdminInjector() throws Exception {
        adminController = new AdminController();
        adminService = new AdminService();
        adminRepository = new InstructorAdminRepository();
    }

    public static AdminInjector instance() throws Exception {
        if(instance==null){
            instance = new AdminInjector();
        }
        return instance;
    }


    public IAdminController getAdminController() {
        return adminController;
    }

    public void setAdminController(IAdminController adminController) {
        this.adminController = adminController;
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
