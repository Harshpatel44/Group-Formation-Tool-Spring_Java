package CSCI5308.GroupFormationTool.Course;

import java.util.List;

public interface IHomeRepository {
    List<ICourse> getcourse(IUserRole userRole) throws Exception;

    boolean checkRole(IUserRole userRole);
}
