package CSCI5308.GroupFormationTool.Database;

import org.springframework.stereotype.Service;

public class DBConfiguration implements IDBConfiguration
{
	private static final String URL = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_9_DEVINT?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//	private static final String URL = "jdbc:mysql://localhost/test_asdc?useSSL=false";
	private static final String USER = "CSCI5308_9_DEVINT_USER";
//	private static final String USER = "root";
	private static final String PASSWORD = "CSCI5308_9_DEVINT_9017";
//	private static final String PASSWORD = "H@rsh7797sql";

	@Override
	public String getDBUserName()
	{
		return USER;
	}

	@Override
	public String getDBPassword()
	{
		return PASSWORD;
	}
	
	@Override
	public String getDBURL()
	{
		return URL;
	}
}
