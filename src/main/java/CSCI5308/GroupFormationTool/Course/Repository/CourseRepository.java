package CSCI5308.GroupFormationTool.Course.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import CSCI5308.GroupFormationTool.Course.AccessControl.ICourseRepository;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
//Dhruvesh Patel
public class CourseRepository implements ICourseRepository {

	@Override
	public String checkRole(String userId, String courseId) {
		String role = "Guest";
		System.out.println(userId);
		System.out.println(courseId);

		try {
			StoredProcedure rolecheck;
			rolecheck = new StoredProcedure("StudentRole(?,?)");
			rolecheck.setParameter(1, userId);
			rolecheck.setParameter(2,courseId);
			ResultSet rs = rolecheck.executeWithResults();
			if(rs.next()) {

						role = "Student";
			}
			rolecheck.cleanup();

			rolecheck = new StoredProcedure("TaRole(?,?)");
			rolecheck.setParameter(1, userId);
			rolecheck.setParameter(2,courseId);
			ResultSet rs1 = rolecheck.executeWithResults();
			if(rs1.next()) {

						role = "TA";
			}
			rolecheck.cleanup();

			rolecheck = new StoredProcedure("InstructorRole(?,?)");
			rolecheck.setParameter(1, userId);
			rolecheck.setParameter(2,courseId);
			ResultSet rs2 = rolecheck.executeWithResults();
			if(rs2.next()){

						role = "Instructor";
			}
			rolecheck.cleanup();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(role);

		return role;
	}

	public boolean checkIfUserPresent(String taId){
		StoredProcedure checkPresence = null;
		boolean result = false;
		try {
			checkPresence = new StoredProcedure("CheckUser(?)");
			checkPresence.setParameter(1, taId);
			ResultSet rs = checkPresence.executeWithResults();
			if(rs.next()){
					result = true;
			
			}
			checkPresence.cleanup();

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}finally{
			
		}
        return result;
	}

	private boolean checkIfAlreadyTa(String taId, String courseId) {
		boolean result = false;
		StoredProcedure checkPresence = null;
		try {
			checkPresence = new StoredProcedure("AlreadyTa(?,?)");
			checkPresence.setParameter(1, courseId);
			checkPresence.setParameter(2, taId);
			ResultSet rs = checkPresence.executeWithResults();
			{
				if(rs.next()){
					result = true;
				}
			}
			checkPresence.cleanup();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}finally{
//			checkPresence.cleanup();
		}
		return result;

	}


	@Override
	public String addTa(String taId, String courseId) {
		String result=null;
		StoredProcedure addTa = null;
		if(checkIfUserPresent(taId))
		{
            if(checkIfAlreadyTa(taId,courseId)){
				result = "Already user is TA of courseId:"+courseId+".";
			}
            else{

					try {
						addTa = new StoredProcedure("InsertTa(?,?)");
						addTa.setParameter(1, courseId);
						addTa.setParameter(2, taId);
						addTa.execute();
						result = "user with Id:"+taId+" is add as a TA for courseId"+courseId+".";
						addTa.cleanup();
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}finally{
						
					}
			}
		}
		else
		{
			result = "No user exist with Id:"+taId+" present in system.";
		}
		return result;
	}





}
