package CSCI5308.GroupFormationTool.Login;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface IForgetPasswordService {
    public String getEmailByBannerid(String bannerid);
    public boolean insertToForgetPassword(String bannerid, String passKey);
    public String getBannerIdByPassKey(String passKey);
    public boolean updatePassword(String bannerid, String password);
    public int getPasswordPolicyNumber();
    public List<String> getPasswordByBannerId(String bannerid, int passNumber);
    public boolean comparePassword(@NotNull String newPassword, String confirmPassword);
    public String generatePassKey();
}
