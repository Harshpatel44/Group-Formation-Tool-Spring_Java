package CSCI5308.GroupFormationTool.Login.Repository;

import CSCI5308.GroupFormationTool.Database.ConnectionManager;
import CSCI5308.GroupFormationTool.Database.DBConfiguration;
import jdk.nashorn.internal.codegen.CompilerConstants;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository {
    public boolean checkLogin(String bannerid, String password)
    {
        try
        {
            Connection connection = ConnectionManager.instance().getDBConnection();
            CallableStatement st = connection.prepareCall("{CALL GetCredentials(?,?)}");
            st.setString(1,bannerid);
            st.setString(2,password);
            ResultSet result = st.executeQuery();
            if(result.next())
            {
               return true;
            }
            else
            {
                return false;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return true;
    }

}
