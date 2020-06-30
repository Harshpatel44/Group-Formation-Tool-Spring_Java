package CSCI5308.GroupFormationTool.Course.AccessControl;

import java.util.List;

import CSCI5308.GroupFormationTool.Course.Model.Course;
import CSCI5308.GroupFormationTool.Course.Model.UserId;

public interface IHomeRepository {
    List<Course> getcourse(UserId userId);

    boolean checkRole(UserId userId);
}
