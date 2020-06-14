package CSCI5308.GroupFormationTool.Course.AccessControl;

public interface ICourseService {


    String addTa(String taId, String courseId);

    boolean checkRole(String userType);

    boolean checkUserType(String userType);
}
