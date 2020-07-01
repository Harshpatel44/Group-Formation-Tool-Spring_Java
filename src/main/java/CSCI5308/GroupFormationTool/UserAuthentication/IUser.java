package CSCI5308.GroupFormationTool.UserAuthentication;

public interface IUser {
    String getFirstName();
    void setFirstName(String firstName);
    String getLastName();
    void setLastName(String lastName);
    String getBannerId();
    void setBannerId(String bannerId);
    String getEmailId();
    void setEmailId(String emailId);
    String getPassword();
    void setPassword(String password);
    String getConfirmPassword();
    void setConfirmPassword(String confirmPassword);
    String getContactNumber();
    void setContactNumber(String contactNumber);
}
