package CSCI5308.GroupFormationTool.Course.Model;

public class UserId implements CSCI5308.GroupFormationTool.Course.AccessControl.IUserId {
 	private String userId;
    @Override
	public String getUserId() {
		return userId;
	}
    @Override
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
