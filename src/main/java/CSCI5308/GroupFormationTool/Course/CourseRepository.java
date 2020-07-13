package CSCI5308.GroupFormationTool.Course;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.UserManager.IInstructor;
import CSCI5308.GroupFormationTool.UserManager.IUser;

public class CourseRepository implements ICourseRepository {

	private boolean checkIfAlreadyTaInCourse(String taId, String courseId) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean assignInstructorForCourse(IInstructor instructor){
		StoredProcedure storedProcedure = null;
		StoredProcedure storedProcedure1 = null;
		try {
			storedProcedure = new StoredProcedure("GetInstructorRole");
			ResultSet resultSet = storedProcedure.executeWithResults();
			resultSet.next();
			int instructorRoleId = resultSet.getInt("roleId");
			try {
				storedProcedure1 = new StoredProcedure("AssignInstructor(?,?,?)");
				storedProcedure1.setParameter(1, instructor.getInstructorId());
				storedProcedure1.setParameter(2, String.valueOf(instructorRoleId));
				storedProcedure1.setParameter(3, instructor.getSelectedInstructorCourseId());
				storedProcedure1.execute();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			finally {
				if(storedProcedure1 != null){
					storedProcedure1.cleanup();
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
		finally {
			if(storedProcedure!=null){
				storedProcedure.cleanup();
			}
		}
	}

	@Override
	public String addTaForCourse(String taId, String courseId){
		String result=null;
		StoredProcedure storedProcedure = null;
		if(checkIfAlreadyTaInCourse(taId,courseId)){
			result = "Already user has different role for courseId:"+courseId+".";
		}
		else{
				try {
					storedProcedure = new StoredProcedure("InsertTa(?,?)");
					storedProcedure.setParameter(1, courseId);
					storedProcedure.setParameter(2, taId);
					storedProcedure.execute();
					result = "user with Id:"+taId+" is add as a TA for courseId"+courseId+".";
					storedProcedure.cleanup();
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					if(storedProcedure!=null){
						storedProcedure.cleanup();
					}
				}
		}
		return result;
	}


	@Override
	public boolean getUserDetailsOnCourse(IUser iUser, String courseId){
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = new StoredProcedure("userByCourse(?,?)");
			storedProcedure.setParameter(1, iUser.getBannerId());
			storedProcedure.setParameter(2, courseId);
			ResultSet results = storedProcedure.executeWithResults();
			if (results != null) {
				if (results.next()) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(storedProcedure!=null){
				storedProcedure.cleanup();
			}
		}
		return false;
	}

	@Override
	public boolean enrollStudentForCourse(IUser user, String courseId) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public ArrayList<ArrayList<String>> getAllCourses(){
		ArrayList<String> courseNamesList = new ArrayList<>();
		ArrayList<String> courseIdsList = new ArrayList<>();
		ArrayList<ArrayList<String>> courseNamesWithIdsList = new ArrayList<>();
		StoredProcedure storedProcedure = null;
		try{
			storedProcedure = new StoredProcedure("AllCourses");
			ResultSet result = storedProcedure.executeWithResults();
			while(result.next()){
				courseNamesList.add(result.getString("courseName"));
				courseIdsList.add(result.getString("courseId"));
			}
			storedProcedure.cleanup();
			courseNamesWithIdsList.add(courseIdsList);
			courseNamesWithIdsList.add(courseNamesList);
			return courseNamesWithIdsList;
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			if(storedProcedure!=null){
				storedProcedure.cleanup();
			}
		}
		return courseNamesWithIdsList;
	}


	@Override
	public boolean createCourseRepo(ICreateCourse createCourse){
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
	public boolean deleteCourseRepo(IDeleteCourse deleteCourse) {
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

	@Override
	public ArrayList<String> getCoursesOfSpecificUserRole(String userId, int roleId){
		ArrayList<String> courseIdList = new ArrayList<>();
		StoredProcedure storedProcedure = null;
		try{
			storedProcedure = new StoredProcedure("GetCoursesOfSpecificRole(?,?)");
			storedProcedure.setParameter("uId",userId);
			storedProcedure.setParameter("rId",String.valueOf(roleId));
			ResultSet result = storedProcedure.executeWithResults();
			while(result.next()){
				courseIdList.add(result.getString("courseId"));
			}
			return courseIdList;
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			if(storedProcedure!=null){
				storedProcedure.cleanup();
			}
		}
		return courseIdList;
	}
}
