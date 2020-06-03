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
			storedProcedure= new StoredProcedure("spCreateUser(?, ?, ?, ?, ?,?)");
			storedProcedure.setParameter(1, user.getBannerId());
			storedProcedure.setParameter(2, user.getPassword());
			storedProcedure.setParameter(3, user.getFirstName());
			storedProcedure.setParameter(4, user.getLastName());
			storedProcedure.setParameter(5, user.getEmailId());
			storedProcedure.setParameter(6, 944789);
			storedProcedure.execute();
			success= true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (null != storedProcedure)
			{
				storedProcedure.cleanup();
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
			if (results!= null)
			{
				if(results.next())
				{
				 
					return true;
				}
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (null != storedProcedure)
			{
				storedProcedure.cleanup();
			}
		}
		return false;

	}

	@Override
	public boolean getUserByBannerId(User user) {
		// TODO Auto-generated method stub
		StoredProcedure storedProcedure = null; 
		try {
			storedProcedure = new StoredProcedure("userByBannerID(?)");
			storedProcedure.setParameter(1, user.getBannerId());
			ResultSet results = storedProcedure.executeWithResults();
			if (results!= null)
			{
				if(results.next())
				{
				 
					return true;
				}
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (null != storedProcedure)
			{
				storedProcedure.cleanup();
			}
		}
		return false;
	}

	@Override
	public List<String> getAllBannerIds() {
		List<String> bannerIds = new ArrayList<String>();
		StoredProcedure storedProcedure = null; 
		try {
			storedProcedure= new StoredProcedure("spGetAllUserIDs");

			ResultSet results = storedProcedure.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					bannerIds.add(results.getString(1));
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (null != storedProcedure)
			{
				storedProcedure.cleanup();
			}
		}
		return bannerIds;
	}

	@Override
	public boolean getUserDetailsOnCourse(User user,String courseId) {
		StoredProcedure storedProcedure = null; 
		try {
			storedProcedure = new StoredProcedure("userByCourse(?,?)");
			storedProcedure.setParameter(1, user.getBannerId());
			storedProcedure.setParameter(2, courseId);
			ResultSet results = storedProcedure.executeWithResults();
			if (results!= null)
			{
				if(results.next())
				{
				 
					return true;
				}
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (null != storedProcedure)
			{
				storedProcedure.cleanup();
			}
		}
		return false;
	}

	@Override
	public boolean enrollStudentForCourse(User user, String courseId) {
		
		Boolean success = false;
		StoredProcedure storedProcedure = null; 
		try {
			storedProcedure= new StoredProcedure("spEnrollStudentForCourse(?, ?)");
			storedProcedure.setParameter(1, user.getBannerId());
			storedProcedure.setParameter(2, courseId);
			storedProcedure.execute();
			success= true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (null != storedProcedure)
			{
				storedProcedure.cleanup();
			}
		}
		return success;
	}
	

	
	
}
