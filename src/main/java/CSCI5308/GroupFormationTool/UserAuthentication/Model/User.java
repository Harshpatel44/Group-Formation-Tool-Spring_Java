package CSCI5308.GroupFormationTool.UserAuthentication.Model;

import CSCI5308.GroupFormationTool.UserAuthentication.Validators.PasswordMatches;
import CSCI5308.GroupFormationTool.UserAuthentication.Validators.ValidEmail;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@PasswordMatches
public class User {

    private long id;
    
    @NotNull
    @NotEmpty
    private String firstName;
    
    @NotNull
    @NotEmpty
    private String lastName;
    
    @NotNull
    @NotEmpty
    @Size(min = 9, max = 9)
    private String bannerId;
    
    @NotNull
    @NotEmpty
    @ValidEmail
    private String emailId;
    
    @Size(min = 8)
    @NotNull
    @NotEmpty
    private String password;
    
    @NotNull
    @Size(min = 8)
    @NotEmpty
    private String confirmPassword;
    
    private char roleType;

    public char getRoleType() {
		return roleType;
	}

	public void setRoleType(char roleType) {
		this.roleType = roleType;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
    
    
    
}
