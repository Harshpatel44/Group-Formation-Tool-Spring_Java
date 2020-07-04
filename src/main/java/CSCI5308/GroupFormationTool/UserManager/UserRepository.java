package CSCI5308.GroupFormationTool.UserManager;

import CSCI5308.GroupFormationTool.Database.StoredProcedure;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.PasswordManager.UserPasswordPolicy;
import CSCI5308.GroupFormationTool.PasswordManager.UserPasswordPolicyStatus;
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
	public boolean checkIfUserExists(String bannerID) {
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = new StoredProcedure("userByBannerID(?)");
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
	public IUser setUserByBannerId(String BannerID, IUser iUser) {
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = new StoredProcedure("userByBannerID(?)");
			storedProcedure.setParameter(1, BannerID);
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
			storedProcedure = new StoredProcedure("spGetAllUserIDs");
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

	public String checkUserRoleForCourse(String bannerID, String courseID){
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = new StoredProcedure("CheckGuest(?,?)");
			storedProcedure.setParameter(1, bannerID);
			storedProcedure.setParameter(2, courseID);
			ResultSet rs = storedProcedure.executeWithResults();
			if(rs.next())//if data present, then not guest
			{
				return rs.getString("roleId");
			}
			else{
				return "guest";
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
			role = new StoredProcedure("CheckGuest(?)");
			role.setParameter(1, bannerID);
			ResultSet rs = role.executeWithResults();
			if(rs.next())//if data present, then not guest
			{
				result =false;//Not Guest
			}
			role.cleanup();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



//	@Override
//	public IUser setUserByBannerID(String BannerId) {
//		StoredProcedure proc = null;
//		IUser user = null;
//		try {
//			proc = new StoredProcedure("spLoadUser(?)");
//			proc.setParameter(1, BannerId);
//			ResultSet results = proc.executeWithResults();
//			if (null != results) {
//				while (results.next()) {
//					String bannerID = results.getString(1);
//					String password = results.getString(2);
//					String firstName = results.getString(3);
//					String lastName = results.getString(4);
//					String email = results.getString(5);
//					String contactNo = results.getString(6);
//					user =  Injector.instance().getUser();
//					user.setFirstName(firstName);
//					user.setLastName(lastName);
//					user.setEmailId(email);
//					user.setContactNumber(contactNo);
//					user.setBannerId(bannerID);
//					user.setPassword(password);
//				}
//				proc.cleanup();
//			}
//
//		} catch (SQLException e) { } catch (Exception e) {
//			e.printStackTrace();
//		}
//		return user;
//	}

	//	@Override
//	public boolean getUserByEmailId(IUser user) throws Exception {
//		StoredProcedure storedProcedure = null;
//		try {
//			storedProcedure = new StoredProcedure("userByEmailID(?)");
//			storedProcedure.setParameter(1, user.getEmailId());
//			ResultSet results = storedProcedure.executeWithResults();
//			if (results != null) {
//				if (results.next()) {
//					return true;
//				}
//			}
//			storedProcedure.cleanup();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}

}
