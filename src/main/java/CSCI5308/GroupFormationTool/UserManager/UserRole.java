package CSCI5308.GroupFormationTool.UserManager;

public class UserRole implements IUserRole {
	private String userId;

	private String courseId;

	private String role;

	@Override
	public String getUserId() {
		return userId;
	}

	@Override
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String getCourseId() {
		return courseId;
	}

	@Override
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@Override
	public String getRole() {
		return role;
	}

	@Override
	public void setRole(String role) {
		this.role = role;
	}
}
