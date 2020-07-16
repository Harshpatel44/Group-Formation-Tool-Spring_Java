package CSCI5308.GroupFormationTool.Course;

import java.sql. ResultSet;
import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.Injector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static CSCI5308.GroupFormationTool.ApplicationConstants.guest;

public class HomeRepository implements IHomeRepository {
	private List<ICourse> CourseList = new ArrayList<>();

	private static final Logger LOG = LogManager.getLogger();

	@Override
	public List<ICourse> getCourseFromBannerID(String bannerID, boolean GuestOrNot){
		StoredProcedure storedProcedure = null;
		try {
			CourseList.clear();
			//for all courses, if result is true(if user is guest).
			if (GuestOrNot) {
				try {
					storedProcedure = new StoredProcedure("AllCourses");
					ResultSet rs = storedProcedure.executeWithResults();
					while (rs.next()) {
						ICourse course = new Course();
						course.setCourseId(rs.getString("courseId"));
						course.setCourseName(rs.getString("courseName"));
						course.setRole(guest);
						CourseList.add(course);
						LOG.info("Operation = Get courses from banner id "+bannerID+" when guest function, Status = Success");
					}
				} catch (Exception e) {
					LOG.error("Operation = Get courses from banner id "+bannerID+" when guest function, Status = Fail, Error Message="+e.getMessage());
					e.printStackTrace();
				} finally {
					if (storedProcedure != null) {
						storedProcedure.cleanup();
					}
				}
			}
			// for courses if user is not guest
			else {
				try {
					storedProcedure = new StoredProcedure("Courses(?)");
					storedProcedure.setParameter(1, bannerID);
					ResultSet rs = storedProcedure.executeWithResults();
					while (rs.next()) {
						ICourse course = new Course();
						storedProcedure = new StoredProcedure("courseName(?)");
						storedProcedure.setParameter(1, rs.getString("courseId"));
						ResultSet rs1 = storedProcedure.executeWithResults();
						storedProcedure = new StoredProcedure("userRole(?)");
						storedProcedure.setParameter(1, rs.getInt("roleId"));
						ResultSet rs2 = storedProcedure.executeWithResults();
						if (rs1.next() && rs2.next()) {
							course.setCourseId(rs.getString("courseId"));
							course.setCourseName(rs1.getString("courseName"));
							course.setRole(rs2.getString("roleType"));
							CourseList.add(course);
						}
					}
					LOG.info("Operation = Get courses from banner id "+bannerID+" when not guest function, Status = Success");
				} catch (Exception e) {
					LOG.error("Operation = Get courses from banner id "+bannerID+" when not guest function, Status = Fail, Error Message="+e.getMessage());
					e.printStackTrace();
				} finally {
					if (storedProcedure != null) {
						storedProcedure.cleanup();
					}
				}
			}
			LOG.info("Operation = Get courses from banner id "+bannerID+", Status = Success");
			return CourseList;
		} catch (Exception e) {
			LOG.error("Operation = Get courses from banner id "+bannerID+", Status = Fail, Error Message="+e.getMessage());
			e.printStackTrace();
		}
		return CourseList;
	}
}
