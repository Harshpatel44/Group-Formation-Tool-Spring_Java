package CSCI5308.GroupFormationTool.Course.Repository;

import java.sql. ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Course.AccessControl.IHomeRepository;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.Course.Model.Course;
import CSCI5308.GroupFormationTool.Course.Model.UserId;
//Dhruvesh Patel
public class HomeRepository implements IHomeRepository{
	private List<Course> CourseList = new ArrayList<Course>();

	@Override
	public boolean checkRole(UserId user) {
		boolean result = true;//Guest
		StoredProcedure role = null;
		try {
			role = new StoredProcedure("CheckGuest(?)");
			role.setParameter(1,user.getUserId());
			ResultSet rs = role.executeWithResults();
			if(rs.next())//if data present, then not guest
			{
				result =false;//Not Guest
			}
			role.cleanup();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Course> getcourse(UserId user) {
		boolean result = checkRole(user);
		StoredProcedure storedProcedure = null;
		try {
			CourseList.clear();
			//for all courses, if result is true(if user is guest).
			if(result) {
				storedProcedure = new StoredProcedure("AllCourses");
				ResultSet rs = storedProcedure.executeWithResults();
				while(rs.next()) {
					Course temp = new Course();
					temp.setCourseId(rs.getString("courseId"));
					temp.setCourseName(rs.getString("courseName"));
					temp.setRole("Guest");
					CourseList.add(temp);
				}
				storedProcedure.cleanup();
			}
			// for courses if user is not guest
			else{
				storedProcedure = new StoredProcedure("Courses(?)");
				storedProcedure.setParameter(1, user.getUserId());
				ResultSet rs = storedProcedure.executeWithResults();
				while(rs.next())
				{
					Course temp = new Course();
					storedProcedure = new StoredProcedure("courseName(?)");
					storedProcedure.setParameter(1,rs.getString("courseId"));
					ResultSet rs1 = storedProcedure.executeWithResults();
					storedProcedure = new StoredProcedure("userType(?)");
					storedProcedure.setParameter(1,rs.getInt("roleId"));
					ResultSet rs2 = storedProcedure.executeWithResults();
					if(rs1.next() && rs2.next())
					{
						temp.setCourseId(rs.getString("courseId"));
						temp.setCourseName(rs1.getString("courseName"));
						temp.setRole(rs2.getString("roleType"));
						CourseList.add(temp);
					}
					storedProcedure.cleanup();
					storedProcedure.cleanup();
				}
				storedProcedure.cleanup();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return CourseList;	
	}







}
