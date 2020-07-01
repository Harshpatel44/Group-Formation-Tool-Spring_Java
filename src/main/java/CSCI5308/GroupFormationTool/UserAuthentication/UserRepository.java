package CSCI5308.GroupFormationTool.UserAuthentication;

import CSCI5308.GroupFormationTool.Database.StoredProcedure;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

	@Override
	public boolean createUser(IUser user) {
		Boolean success = false;
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = new StoredProcedure("spCreateUser(?, ?, ?, ?, ?,?)");
			storedProcedure.setParameter(1, user.getBannerId());
			storedProcedure.setParameter(2, user.getPassword());
			storedProcedure.setParameter(3, user.getFirstName());
			storedProcedure.setParameter(4, user.getLastName());
			storedProcedure.setParameter(5, user.getEmailId());
			storedProcedure.setParameter(6, user.getContactNumber());
			storedProcedure.execute();

			success = true;
			storedProcedure.cleanup();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean getUserByEmailId(IUser user) throws Exception {
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = new StoredProcedure("userByEmailID(?)");
			storedProcedure.setParameter(1, user.getEmailId());
			ResultSet results = storedProcedure.executeWithResults();
			if (results != null) {
				if (results.next()) {
					return true;
				}
			}
			storedProcedure.cleanup();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean getUserByBannerId(IUser user) {
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = new StoredProcedure("userByBannerID(?)");
			storedProcedure.setParameter(1, user.getBannerId());
			ResultSet results = storedProcedure.executeWithResults();
			if (results != null) {
				if (results.next()) {
					return true;
				}
			}
			storedProcedure.cleanup();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<String> getAllBannerIds() throws Exception {
		List<String> bannerIds = new ArrayList<String>();
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = new StoredProcedure("spGetAllUserIDs");
			ResultSet results = storedProcedure.executeWithResults();
			if (null != results) {
				while (results.next()) {
					bannerIds.add(results.getString(1));
				}
			}
			storedProcedure.cleanup();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bannerIds;
	}

	@Override
	public IUser loadUserByID(String BannerId) {
		StoredProcedure proc = null;
		IUser user = null;
		try {
			proc = new StoredProcedure("spLoadUser(?)");
			proc.setParameter(1, BannerId);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					String bannerID = results.getString(1);
					String password = results.getString(2);
					String firstName = results.getString(3);
					String lastName = results.getString(4);
					String email = results.getString(5);
					String contactNo = results.getString(6);
					user = new User() {
						{
							setFirstName(firstName);
							setLastName(lastName);
							setEmailId(email);
							setContactNumber(contactNo);
							setBannerId(bannerID);
							setPassword(password);
						}
					};
				}
				proc.cleanup();
			}

		} catch (SQLException e) { } catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public UserPasswordPolicy getUserPasswordPolicy() throws Exception {
		StoredProcedure proc = null;
		UserPasswordPolicy passwordPolicy = null;
		try {
			proc = new StoredProcedure("spGetPasswordPolicy()");
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {

					Integer minLength = results.getInt(1);
					Integer maxLength = results.getInt(2);
					Integer minUpperCaseLetter = results.getInt(3);
					Integer minLowerCaseLetter = results.getInt(4);
					Integer minNoOfSymbols = results.getInt(5);
					String notAllowedCharacters = results.getString(6);

					passwordPolicy = UserPasswordPolicy.setInstance(minLength, maxLength, minUpperCaseLetter, minLowerCaseLetter, minNoOfSymbols, notAllowedCharacters);
					proc.cleanup();
				}
			}

		} catch (SQLException e) { };
		return passwordPolicy;
	}
	@Override
	public UserPasswordPolicyStatus getUserPasswordPolicyStatus() {
		StoredProcedure proc = null;
		UserPasswordPolicyStatus passwordPolicy = null;
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

					passwordPolicy = UserPasswordPolicyStatus.setInstance(minLength, maxLength, minUpperCaseLetter, minLowerCaseLetter, minNoOfSymbols, notAllowedCharacters);
					proc.cleanup();
				}
			}

		} catch (SQLException e) { } catch (Exception e) {
			e.printStackTrace();
		}
		;
		return passwordPolicy;
	}

}
