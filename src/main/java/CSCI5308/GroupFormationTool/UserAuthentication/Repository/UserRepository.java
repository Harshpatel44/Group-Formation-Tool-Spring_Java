package CSCI5308.GroupFormationTool.UserAuthentication.Repository;

import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

	@Override
	public boolean createUser(User user) {

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
		} finally {
			if (null != storedProcedure) {

			}
		}
		return success;
	}

	@Override
	public boolean getUserByEmailId(User user) {
		// TODO Auto-generated method stub
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
		} finally {
			if (null != storedProcedure) {

			}
		}
		return false;

	}

	@Override
	public boolean getUserByBannerId(User user) {

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != storedProcedure) {

			}

		}
		return false;
	}

	@Override
	public List<String> getAllBannerIds() {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != storedProcedure) {

			}
		}
		return bannerIds;
	}

	@Override
	public boolean getUserDetailsOnCourse(User user, String courseId) {
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = new StoredProcedure("userByCourse(?,?)");
			storedProcedure.setParameter(1, user.getBannerId());
			storedProcedure.setParameter(2, courseId);
			ResultSet results = storedProcedure.executeWithResults();
			if (results != null) {
				if (results.next()) {

					return true;
				}

			}
			storedProcedure.cleanup();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != storedProcedure) {

			}
		}
		return false;

	}
	

	@Override
	public boolean enrollStudentForCourse(User user, String courseId) {

		Boolean success = false;
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = new StoredProcedure("spEnrollStudentForCourse(?, ?)");
			storedProcedure.setParameter(1, user.getBannerId());
			storedProcedure.setParameter(2, courseId);
			storedProcedure.execute();
			success = true;
			storedProcedure.cleanup();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (null != storedProcedure) {

			}
		}
		return success;
	}

	@Override
	public User loadUserByID(String BannerId) {

		StoredProcedure proc = null;
		User user = null;
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
					Integer contactNo = results.getInt(6);
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
		} catch (SQLException e) {

		} finally {
			if (null != proc) {
				
			}

		}
		return user;
	}

}
