package CSCI5308.GroupFormationTool.Course;

import java.util.List;

public interface IHomeRepository {
    List<Course> getcourse(IUserId iUserId);

    boolean checkRole(IUserId iUserId);
}
