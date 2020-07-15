package CSCI5308.GroupFormationTool.UserManager;

public class UserManagerAbstractFactory implements IUserManagerAbstractFactory {

    @Override
    public CurrentUser getCurrentUser(){
        return new CurrentUser();
    }

    @Override
    public IInstructor getInstructor(){
        return new Instructor();
    }
}
