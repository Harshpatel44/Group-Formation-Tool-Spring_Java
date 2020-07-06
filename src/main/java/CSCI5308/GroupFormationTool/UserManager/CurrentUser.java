package CSCI5308.GroupFormationTool.UserManager;

public class CurrentUser{
    private static CurrentUser instance = null;
    private String firstName;
    private String lastName;
    private String bannerId;


    public static CurrentUser instance(){
        if(instance==null){
            instance = new CurrentUser();
        }
        return instance;
    }

    public static CurrentUser getInstance() {
        return instance;
    }

    public static void setInstance(CurrentUser instance) {
        CurrentUser.instance = instance;
    }

    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getBannerId() {
        return bannerId;
    }


    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

}
