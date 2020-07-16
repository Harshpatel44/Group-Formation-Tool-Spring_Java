package CSCI5308.GroupFormationTool.UserAuthentication;

import java.sql.ResultSet;

import CSCI5308.GroupFormationTool.Database.StoredProcedure;

public class UserLoginRepository implements ILoginRepository {

    @Override
    public boolean checkIfUserIsAuthenticated(String bannerID, String password)
    {
        StoredProcedure storedProcedure = null;
        boolean isValid = false;
        String encryptedPassword = "";
        try
        {
            BCryptEncryption encryption = new BCryptEncryption();
            storedProcedure = new StoredProcedure("GetCredentials(?)");
            storedProcedure.setParameter(1, bannerID);
            storedProcedure.executeWithResults();
            ResultSet result = storedProcedure.executeWithResults();
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
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            if(storedProcedure!=null){
                storedProcedure.cleanup();
            }
        }
        return isValid;
    }
}
