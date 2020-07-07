package CSCI5308.GroupFormationTool.PasswordManager;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface IForgetPasswordService {
    public String getEmailByBannerID(String bannerID) throws Exception;
    public boolean insertToForgetPassword(String bannerID, String passKey) throws Exception;
    public String getBannerIDByPassKey(String passKey) throws Exception;
    public boolean updatePassword(String bannerID, String password) throws Exception;
    public int getPasswordPolicyNumber() throws Exception;
    public List<String> getPasswordByBannerID(String bannerID, int passNumber) throws Exception;
    public boolean comparePassword(@NotNull String newPassword, String confirmPassword);
    public String generatePassKey();
}
