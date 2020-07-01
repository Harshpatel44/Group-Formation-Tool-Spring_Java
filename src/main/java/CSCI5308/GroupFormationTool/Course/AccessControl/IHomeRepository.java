package CSCI5308.GroupFormationTool.Course.AccessControl;

import java.util.List;

import CSCI5308.GroupFormationTool.Course.Model.Course;

public interface IHomeRepository {
    List<Course> getcourse(IUserId iUserId);

    boolean checkRole(IUserId iUserId);
}
