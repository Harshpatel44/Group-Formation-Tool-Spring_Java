package CSCI5308.GroupFormationTool.Login.Repository;

import CSCI5308.GroupFormationTool.Database.ConnectionManager;
import CSCI5308.GroupFormationTool.Login.AccessControl.ILoginRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.Security.BCryptEncryption;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginRepository implements ILoginRepository {

    @Override
    public boolean checkLogin(String bannerid, String password)
    {
        System.out.println("inside");
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
        }
        return isValid;
    }

    @Override
    public boolean isUser(String bannerid) {
        System.out.println("inside");
        boolean isUser = false;
        try {
            Connection connection = ConnectionManager.instance().getDBConnection();
            CallableStatement st = connection.prepareCall("{CALL userByBannerID(?)}");
            st.setString(1, bannerid);
            ResultSet result = st.executeQuery();
            if(result.next())
            {
                isUser = true;
            }
            else
            {
                isUser = false;
            }
            connection.close();
        }
        catch (SQLException e)
        { e.printStackTrace(); }
        return isUser;
    }

    @Override
    public String getEmailByBannerid(String bannerid) {
        String email = new String();
        try {
            Connection connection = ConnectionManager.instance().getDBConnection();
            CallableStatement st = connection.prepareCall("{CALL GetEmailByBannerID(?)}");
            st.setString(1, bannerid);
            ResultSet result = st.executeQuery();
            if(result.next())
            {
                email = result.getString(1);
            }
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return email;
    }

    @Override
    public boolean updatePassword(String bannerid, String newPassword) {
        System.out.print("inside update password");
        try {
            Connection connection = ConnectionManager.instance().getDBConnection();
            CallableStatement st = connection.prepareCall("{CALL updatePassword(?,?)}");
            st.setString(1, bannerid);
            st.setString(2,newPassword);
            ResultSet result = st.executeQuery();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<String> getPasswordByBannerId(String bannerid) {
        List<String> passwords = new ArrayList<String>();
        try {
            Connection connection = ConnectionManager.instance().getDBConnection();
            CallableStatement st = connection.prepareCall("{CALL GetPasswordByBannerID(?)}");
            st.setString(1,bannerid);
            ResultSet result = st.executeQuery();
            while (result.next())
            {
                passwords.add(result.getString(1));
            }
            st.close();
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return passwords;
    }

    @Override
    public boolean insertToForgetPassword(String bannerid, String passKey) {
        try {
            Connection connection = ConnectionManager.instance().getDBConnection();
            CallableStatement st = connection.prepareCall("{CALL InsertPasswordKey(?,?)}");
            st.setString(1, bannerid);
            st.setString(2,passKey);
            ResultSet result = st.executeQuery();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String getBannerIdByPassKey(String passKey) {
        String bannerid = "";
        try {
            Connection connection = ConnectionManager.instance().getDBConnection();
            CallableStatement st = connection.prepareCall("{CALL BannerIdByKey(?)}");
            st.setString(1, passKey);
            ResultSet result = st.executeQuery();
            if(result.next())
            {
                bannerid = result.getString(1);
            }
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return  bannerid;
    }



}
