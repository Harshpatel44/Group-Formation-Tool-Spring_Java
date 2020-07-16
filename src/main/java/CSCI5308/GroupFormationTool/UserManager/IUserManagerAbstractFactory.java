package CSCI5308.GroupFormationTool.UserManager;

public interface IUserManagerAbstractFactory {
    CurrentUser getCurrentUser();
    IInstructor getInstructor();
    IUser getUser();
}
