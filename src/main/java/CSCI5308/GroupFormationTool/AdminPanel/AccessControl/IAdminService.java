package CSCI5308.GroupFormationTool.AdminPanel.AccessControl;

import CSCI5308.GroupFormationTool.AdminPanel.Model.Instructor;

public interface IAdminService {
    public boolean AssignInstructorService(Instructor assignInstructor) throws Exception;
}
