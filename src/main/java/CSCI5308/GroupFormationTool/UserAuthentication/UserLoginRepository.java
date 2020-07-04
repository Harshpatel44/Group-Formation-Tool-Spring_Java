package CSCI5308.GroupFormationTool.UserAuthentication;

import CSCI5308.GroupFormationTool.Database.ConnectionManager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginRepository implements ILoginRepository {

    @Override
    public boolean checkLogin(String bannerid, String password)
    {
        boolean isValid = false;
        String encryptedPassword = "";
        try
        {
            BCryptEncryption encryption = new BCryptEncryption();
            Connection connection = ConnectionManager.instance().getDBConnection();
            CallableStatement st = connection.prepareCall("{CALL GetCredentials(?)}");
            st.setString(1,bannerid);
            ResultSet result = st.executeQuery();
            if(result.next())
            {
                encryptedPassword = result.getString(1);
                if(encryption.passwordMatch(password,encryptedPassword))
                {
                    isValid = true;
                }
                else
                {
                    isValid = false;
                }
            }
            else
            {
                isValid = false;
            }
            connection.close();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }

}
