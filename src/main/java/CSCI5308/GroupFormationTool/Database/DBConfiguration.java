package CSCI5308.GroupFormationTool.Database;

public class DBConfiguration implements IDBConfiguration {

	private static final String URL = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_9_PRODUCTION?useSSL=false&serverTimezone=UTC";

	private static final String USER = "CSCI5308_9_PRODUCTION_USER";
//	private static final String USER = "root";
	private static final String PASSWORD = "CSCI5308_9_PRODUCTION_9914";
//	private static final String PASSWORD = "H@rsh7797sql";

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
