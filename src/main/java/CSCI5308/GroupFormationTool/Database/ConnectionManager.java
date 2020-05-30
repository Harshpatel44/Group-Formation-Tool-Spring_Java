package CSCI5308.GroupFormationTool.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import CSCI5308.GroupFormationTool.Injector;


public class ConnectionManager
{
	private static ConnectionManager instance = null;
	private IDBConfiguration dbConfiguration;

	private String url;
	private String userName;
	private String password;


	private ConnectionManager() {
		dbConfiguration = Injector.instance().getDbConfiguration();	
		url = dbConfiguration.getDBURL();
		userName = dbConfiguration.getDBUserName();
		password = dbConfiguration.getDBPassword();
	}

	public static ConnectionManager instance()
	{
		if (null == instance)
		{
			instance = new ConnectionManager();
		}
		return instance;
	} 

	public Connection getDBConnection() throws SQLException
	{
		System.out.println("In connectionManager");
		return DriverManager.getConnection(url, userName, password);
	}
}
