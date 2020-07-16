package CSCI5308.GroupFormationTool.UserManager;


public class UserManagerAbstractConcrete extends UserManagerAbstractFactory {
    private IUserRepository userRepository;
    private IUserService userService;

    @Override
    public IInstructor getInstructor(){
        return new Instructor();
    }

    @Override
    public IUser getUser(){
        return new User();
    }

    @Override
    public IUserRepository getUserRepository() {
        if (userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    @Override
    public IUserService getUserService() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

	@Override
	public void setUserRepository(IUserRepository userRepository) {
		this.userRepository = userRepository;
		
	}
}
