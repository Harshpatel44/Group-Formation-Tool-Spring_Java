package CSCI5308.GroupFormationTool.Course.AccessControl;

import java.util.List;

import CSCI5308.GroupFormationTool.Course.Model.Course;
import CSCI5308.GroupFormationTool.Course.Model.UserId;

public interface IHomeService {
	public List<Course> getCourses(UserId user);

    public boolean checkRole(UserId user);
}
