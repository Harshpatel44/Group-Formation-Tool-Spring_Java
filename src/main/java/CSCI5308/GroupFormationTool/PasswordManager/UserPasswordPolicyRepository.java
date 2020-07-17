package CSCI5308.GroupFormationTool.PasswordManager;

import CSCI5308.GroupFormationTool.Database.StoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPasswordPolicyRepository implements IUserPasswordPolicyRepository {

    @Override
    public IUserPasswordPolicy getUserPasswordPolicy() {
        StoredProcedure storedProcedure = null;
        IUserPasswordPolicy passwordPolicy = null;
        try {
            storedProcedure = new StoredProcedure("spGetPasswordPolicy()");
            ResultSet results = storedProcedure.executeWithResults();
            if (null != results) {
                while (results.next()) {
                    Integer minLength = results.getInt(1);
                    Integer maxLength = results.getInt(2);
                    Integer minUpperCaseLetter = results.getInt(3);
                    Integer minLowerCaseLetter = results.getInt(4);
                    Integer minNoOfSymbols = results.getInt(5);
                    String notAllowedCharacters = results.getString(6);
                    passwordPolicy = UserPasswordPolicy.setInstance(minLength, maxLength, minUpperCaseLetter,
                            minLowerCaseLetter, minNoOfSymbols, notAllowedCharacters);
                }
            }
        } catch (SQLException e) {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (storedProcedure != null) {
                storedProcedure.cleanup();
            }
        }
        return passwordPolicy;
    }

    @Override
    public IUserPasswordPolicyStatus getUserPasswordPolicyStatus() {
        StoredProcedure proc = null;
        IUserPasswordPolicyStatus passwordPolicy = null;
        try {
            proc = new StoredProcedure("spGetPasswordPolicyStatus()");
            ResultSet results = proc.executeWithResults();
            if (null != results) {
                while (results.next()) {
                    Integer minLength = results.getInt(2);
                    Integer maxLength = results.getInt(3);
                    Integer minUpperCaseLetter = results.getInt(4);
                    Integer minLowerCaseLetter = results.getInt(5);
                    Integer minNoOfSymbols = results.getInt(6);
                    Integer notAllowedCharacters = results.getInt(7);
                    passwordPolicy = UserPasswordPolicyStatus.setInstance(minLength, maxLength, minUpperCaseLetter,
                            minLowerCaseLetter, minNoOfSymbols, notAllowedCharacters);

                }
            }
        } catch (SQLException e) {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (proc != null) {
                proc.cleanup();
            }
        }
        return passwordPolicy;
    }
}
