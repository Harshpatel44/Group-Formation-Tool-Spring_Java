package CSCI5308.GroupFormationTool.Course;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Course.ICourseRepository;
import CSCI5308.GroupFormationTool.Course.CreateCourse;
import CSCI5308.GroupFormationTool.Course.DeleteCourse;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.UserAuthentication.User;

public class CourseRepository implements ICourseRepository {

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
				result = "Already user has different role for courseId:"+courseId+".";
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
					}
			}
		}
		else
		{
			result = "No user exist with Id:"+taId+" present in system.";
		}
		return result;
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
			e.printStackTrace();
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
			storedProcedure.cleanup();
			success = true;
		} catch (SQLException e) {

			e.printStackTrace();
		} 
		return success;
	}

	@Override
	public ArrayList<ArrayList<String>> getAllCourses() throws SQLException{
		ArrayList<String> courseNamesList = new ArrayList<>();
		ArrayList<String> courseIdsList = new ArrayList<>();
		ArrayList<ArrayList<String>> courseNamesWithIdsList = new ArrayList<>();
		StoredProcedure storedProcedure = new StoredProcedure("AllCourses");
		ResultSet result = storedProcedure.executeWithResults();
		while(result.next()){
			courseNamesList.add(result.getString("courseName"));
			courseIdsList.add(result.getString("courseId"));
		}
		storedProcedure.cleanup();
		courseNamesWithIdsList.add(courseIdsList);
		courseNamesWithIdsList.add(courseNamesList);
		return courseNamesWithIdsList;
	}


	@Override
	public boolean createCourseRepo(CreateCourse createCourse) throws SQLException {
		System.out.println("inside");
		try {
			StoredProcedure storedProcedure = new StoredProcedure("CreateCourse(?,?)");
			storedProcedure.setParameter("cId", createCourse.getCourseId());
			storedProcedure.setParameter("cName", createCourse.getCourseName());
			storedProcedure.execute();
			storedProcedure.cleanup();
			return true;
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteCourseRepo(DeleteCourse deleteCourse) {
		try {
			System.out.println(deleteCourse.getSelectedCourseId());
			StoredProcedure storedProcedure = new StoredProcedure("DeleteCourse(?)");
			storedProcedure.setParameter("cId", deleteCourse.getSelectedCourseId());
			storedProcedure.execute();
			storedProcedure.cleanup();
			return true;
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
