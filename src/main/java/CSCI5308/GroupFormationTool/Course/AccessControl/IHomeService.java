package CSCI5308.GroupFormationTool.Course.AccessControl;

import java.util.List;
import CSCI5308.GroupFormationTool.Course.Model.Course;

public interface IHomeService {
	List<Course> getCourses(IUserId iUserId);

    boolean checkRole(IUserId iUserId);
}
