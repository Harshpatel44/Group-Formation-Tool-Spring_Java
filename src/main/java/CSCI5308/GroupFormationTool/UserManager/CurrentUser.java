package CSCI5308.GroupFormationTool.UserManager;

public class CurrentUser implements ICurrentUser {
    private static ICurrentUser instance = null;
    private String firstName;
    private String lastName;
    private String bannerId;

    public static ICurrentUser instance(){
        if(instance==null){
            return new CurrentUser();
        }
        return instance;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getBannerId() {
        return bannerId;
    }

    @Override
    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

}
