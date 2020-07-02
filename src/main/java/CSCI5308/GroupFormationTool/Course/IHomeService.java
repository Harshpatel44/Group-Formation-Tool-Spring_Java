package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.UserManager.IUserRole;

import java.util.List;

public interface IHomeService {
	List<ICourse> getCourses(IUserRole userRole) throws Exception;
    boolean checkRole(IUserRole userRole) throws Exception;
}
