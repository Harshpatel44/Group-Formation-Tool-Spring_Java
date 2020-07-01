package CSCI5308.GroupFormationTool.Course;

import java.util.List;

public interface IHomeRepository {
    List<ICourse> getcourse(IUserRole userRole);

    boolean checkRole(IUserRole userRole);
}
