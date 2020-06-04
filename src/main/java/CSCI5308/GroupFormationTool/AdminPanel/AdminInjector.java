package CSCI5308.GroupFormationTool.AdminPanel;


import CSCI5308.GroupFormationTool.AdminPanel.AccessControl.IAdminController;
import CSCI5308.GroupFormationTool.AdminPanel.AccessControl.IAdminRepository;
import CSCI5308.GroupFormationTool.AdminPanel.AccessControl.IAdminService;
import CSCI5308.GroupFormationTool.AdminPanel.Controller.AdminController;
import CSCI5308.GroupFormationTool.AdminPanel.Model.AssignInstructor;
import CSCI5308.GroupFormationTool.AdminPanel.Model.CreateCourse;
import CSCI5308.GroupFormationTool.AdminPanel.Model.DeleteCourse;
import CSCI5308.GroupFormationTool.AdminPanel.Repository.AdminRepository;
import CSCI5308.GroupFormationTool.AdminPanel.Service.AdminService;

import java.sql.SQLException;

public class AdminInjector {
    private static AdminInjector instance = null;

    private IAdminController adminController;
    private IAdminService adminService;
    private IAdminRepository adminRepository;

    private AdminInjector() throws Exception {
        adminController = new AdminController();
        adminService = new AdminService();
        adminRepository = new AdminRepository();
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

    public IAdminRepository getAdminRepository() {
        return adminRepository;
    }

    public void setAdminRepository(IAdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
}
