package CSCI5308.GroupFormationTool.Login;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface IForgetPasswordService {
    public String getEmailByBannerid(String bannerid) throws Exception;
    public boolean insertToForgetPassword(String bannerid, String passKey) throws Exception;
    public String getBannerIdByPassKey(String passKey) throws Exception;
    public boolean updatePassword(String bannerid, String password) throws Exception;
    public int getPasswordPolicyNumber() throws Exception;
    public List<String> getPasswordByBannerId(String bannerid, int passNumber) throws Exception;
    public boolean comparePassword(@NotNull String newPassword, String confirmPassword);
    public String generatePassKey();
}
