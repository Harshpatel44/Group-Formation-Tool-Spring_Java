package CSCI5308.GroupFormationTool.UserManager;

public interface ICurrentUser {
    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getBannerId();

    void setBannerId(String bannerId);

}
