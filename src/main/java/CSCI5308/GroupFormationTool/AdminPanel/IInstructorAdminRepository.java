package CSCI5308.GroupFormationTool.AdminPanel;


import CSCI5308.GroupFormationTool.UserManager.IInstructor;

public interface IInstructorAdminRepository {
    boolean assignInstructorRepo(IInstructor instructor) throws Exception;
}
