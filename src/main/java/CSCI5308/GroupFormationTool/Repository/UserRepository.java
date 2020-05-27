package CSCI5308.GroupFormationTool.Repository;

import CSCI5308.GroupFormationTool.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.Model.User;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IUserRepository {
	
	@Override
	public boolean createUser(User user) {

		
		try {
			StoredProcedure storedProcedure = new StoredProcedure("sp_select_user");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean getUserByEmailId(User user) {
		// TODO Auto-generated method stub

		if("arjunstar14@gmail.com".equals(user.getEmailId()))
			return false;
		else
			return true;
	}

	@Override
	public boolean getUserByBannerId(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
