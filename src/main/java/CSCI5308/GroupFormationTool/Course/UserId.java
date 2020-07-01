package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.Course.IUserId;

public class UserId implements IUserId {
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
