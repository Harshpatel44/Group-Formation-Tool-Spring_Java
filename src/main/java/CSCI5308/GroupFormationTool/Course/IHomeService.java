package CSCI5308.GroupFormationTool.Course;

import java.util.List;

public interface IHomeService {
	List<Course> getCourses(IUserId iUserId);

    boolean checkRole(IUserId iUserId);
}
