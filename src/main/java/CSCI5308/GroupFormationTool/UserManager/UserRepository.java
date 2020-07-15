package CSCI5308.GroupFormationTool.UserManager;

import CSCI5308.GroupFormationTool.Database.DatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.PasswordManager.UserPasswordPolicy;
import CSCI5308.GroupFormationTool.PasswordManager.UserPasswordPolicyStatus;
import CSCI5308.GroupFormationTool.UserAuthentication.BCryptEncryption;
import CSCI5308.GroupFormationTool.UserAuthentication.IPasswordEncryptor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static CSCI5308.GroupFormationTool.ApplicationConstants.guest;

@Repository
public class UserRepository implements IUserRepository {

	private IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();

	@Override
	public boolean createUser(IUser user) {
		Boolean success = false;
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = databaseAbstractFactory.createStoredProcedure("spCreateUser(?, ?, ?, ?, ?,?)");
			storedProcedure.setParameter(1, user.getBannerId());
			storedProcedure.setParameter(2, user.getPassword());
			storedProcedure.setParameter(3, user.getFirstName());
			storedProcedure.setParameter(4, user.getLastName());
			storedProcedure.setParameter(5, user.getEmailId());
			storedProcedure.setParameter(6, user.getContactNumber());
			storedProcedure.execute();

			success = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(storedProcedure!=null){
				storedProcedure.cleanup();
			}
		}
		return success;
	}


	@Override
	public boolean checkIfUserExists(String bannerID) {
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = databaseAbstractFactory.createStoredProcedure("userByBannerID(?)");
			storedProcedure.setParameter(1, bannerID);
			ResultSet results = storedProcedure.executeWithResults();
			if (results != null) {
				if (results.next()) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(storedProcedure != null){
				storedProcedure.cleanup();
			}
		}
		return false;
	}

	@Override
	public IUser setUserByBannerId(String bannerID, IUser iUser) {
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = databaseAbstractFactory.createStoredProcedure("userByBannerID(?)");
			storedProcedure.setParameter(1, bannerID);
			ResultSet results = storedProcedure.executeWithResults();
			if (results != null) {
				if (results.next()) {
					iUser.setBannerId(results.getString("userId"));
					iUser.setFirstName(results.getString("firstName"));
					iUser.setLastName(results.getString("lastName"));
					iUser.setPassword(results.getString("passwd"));
					iUser.setEmailId(results.getString("email"));
					iUser.setContactNumber(results.getString("contactNo"));
					return iUser;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(storedProcedure!=null){
				storedProcedure.cleanup();
			}
		}
		return iUser;
	}

	@Override
	public List<String> getAllBannerIds(){
		List<String> bannerIds = new ArrayList<String>();
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = databaseAbstractFactory.createStoredProcedure("spGetAllUserIDs");
			ResultSet results = storedProcedure.executeWithResults();
			if (null != results) {
				while (results.next()) {
					bannerIds.add(results.getString(1));
				}
			}
			return bannerIds;
		} catch (Exception e) {
			e.printStackTrace();
			return bannerIds;
		} finally {
			if(storedProcedure!=null){
					storedProcedure.cleanup();
			}
		}
	}

	@Override
	public String checkUserRoleForCourse(String bannerID, String courseID){
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = databaseAbstractFactory.createStoredProcedure("CheckGuest(?,?)");
			storedProcedure.setParameter(1, bannerID);
			storedProcedure.setParameter(2, courseID);
			ResultSet rs = storedProcedure.executeWithResults();
			if(rs.next())//if data present, then not guest
			{
				return rs.getString("roleId");
			}
			else{
				return guest;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(storedProcedure!=null){
				storedProcedure.cleanup();
			}
		}
		return null;
	}

	public boolean checkIfUserIsGuest(String bannerID){
		boolean result = true;  //Guest
		StoredProcedure role = null;
		try {
			role = databaseAbstractFactory.createStoredProcedure("CheckGuest(?)");
			role.setParameter(1, bannerID);
			ResultSet rs = role.executeWithResults();
			if(rs.next())//if data present, then not guest
			{
				result =false;//Not Guest
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(role!=null){
				role.cleanup();
			}
		}
		return result;
	}

	public int getUserRoleIdFromRoleType(String userType){
		StoredProcedure storedProcedure = null;
		try{
			storedProcedure = databaseAbstractFactory.createStoredProcedure("getRoleIdFromRoleType(?)");
			storedProcedure.setParameter("rType",userType.toLowerCase());
			ResultSet rs = storedProcedure.executeWithResults();
			while(rs.next()){
				return rs.getInt("roleId");
			}
		}catch (Exception e){
			e.printStackTrace();
			return 0;
		}finally {
			if(storedProcedure!=null){
				storedProcedure.cleanup();
			}
		}
		return 0;
	}
}
