package CSCI5308.GroupFormationTool.UserAuthentication.Repository;

import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository implements IUserRepository {

	@Override
	public boolean createUser(User user) {

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

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != storedProcedure) {
				storedProcedure.cleanup();
			}
		}
		return true;
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

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != storedProcedure) {
				storedProcedure.cleanup();
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

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != storedProcedure) {
				storedProcedure.cleanup();
			}
		}
		return false;

	}

}
