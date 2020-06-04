package CSCI5308.GroupFormationTool.Course.AccessControl;

public interface ICourseRepository {
	String checkRole(String userId, String courseId);

    String addTa(String taId, String courseId);
}
