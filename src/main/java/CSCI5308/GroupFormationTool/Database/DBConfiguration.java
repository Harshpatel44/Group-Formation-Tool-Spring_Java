package CSCI5308.GroupFormationTool.Database;

import CSCI5308.GroupFormationTool.ApplicationConstants;

public class DBConfiguration implements IDBConfiguration {

	private static final String URL = ApplicationConstants.applicationUrl;

	private static final String USER = ApplicationConstants.dbUser;

	private static final String PASSWORD = ApplicationConstants.dbPassword;


	@Override
	public String getDBUserName() {
		return USER;
	}

	@Override
	public String getDBPassword() {
		return PASSWORD;
	}

	@Override
	public String getDBURL() {
		return URL;
	}
}
