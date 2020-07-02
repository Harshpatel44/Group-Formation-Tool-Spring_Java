package CSCI5308.GroupFormationTool.AdminPanel;

import CSCI5308.GroupFormationTool.UserManager.IInstructor;

public interface IAdminService {
    public boolean AssignInstructorService(IInstructor instructor) throws Exception;
}
