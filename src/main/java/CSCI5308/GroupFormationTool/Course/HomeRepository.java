package CSCI5308.GroupFormationTool.Course;

import java.sql. ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.Injector;

import static CSCI5308.GroupFormationTool.ApplicationConstants.guest;


public class HomeRepository implements IHomeRepository{
	private List<ICourse> CourseList = new ArrayList<ICourse>();

	@Override
	public boolean checkRole(IUserRole userRole) {
		boolean result = true;//Guest
		StoredProcedure role = null;
		try {
			role = new StoredProcedure("CheckGuest(?)");
			role.setParameter(1, userRole.getUserId());
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

	@Override
	public List<ICourse> getcourse(IUserRole userRole) throws Exception {
		boolean result = checkRole(userRole);
		StoredProcedure storedProcedure = null;
		try {
			CourseList.clear();
			//for all courses, if result is true(if user is guest).
			if(result) {
				storedProcedure = new StoredProcedure("AllCourses");
				ResultSet rs = storedProcedure.executeWithResults();
				while(rs.next()) {
					ICourse course = Injector.instance().getCourse();
					course.setCourseId(rs.getString("courseId"));
					course.setCourseName(rs.getString("courseName"));
					course.setRole(guest);
					CourseList.add(course);
				}
				storedProcedure.cleanup();
			}
			// for courses if user is not guest
			else{
				storedProcedure = new StoredProcedure("Courses(?)");
				storedProcedure.setParameter(1, userRole.getUserId());
				ResultSet rs = storedProcedure.executeWithResults();
				while(rs.next())
				{
					ICourse course = Injector.instance().getCourse();
					storedProcedure = new StoredProcedure("courseName(?)");
					storedProcedure.setParameter(1,rs.getString("courseId"));
					ResultSet rs1 = storedProcedure.executeWithResults();
					storedProcedure = new StoredProcedure("userType(?)");
					storedProcedure.setParameter(1,rs.getInt("roleId"));
					ResultSet rs2 = storedProcedure.executeWithResults();
					if(rs1.next() && rs2.next())
					{
						course.setCourseId(rs.getString("courseId"));
						course.setCourseName(rs1.getString("courseName"));
						course.setRole(rs2.getString("roleType"));
						CourseList.add(course);
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
