package CSCI5308.GroupFormationTool.Database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoredProcedure
{
	private String storedProcedureName;
	private Connection connection;
	private CallableStatement statement;
	public StoredProcedure(String storedProcedureName) throws Exception
	{
		this.storedProcedureName = storedProcedureName;
		connection = null;
		statement = null;
		openConnection();
		createStatement();
	}

	private void createStatement() throws SQLException
	{
		statement = connection.prepareCall("{call " + storedProcedureName + "}");
	}

	private void openConnection() throws Exception
	{
		connection = ConnectionManager.instance().getDBConnection();
		System.out.println(connection);
	}

	public void cleanup(){
//		connection.close();
//		System.out.println("Connection closed");
		try
		{
			if (null != statement)
			{
				statement.close();
			}
			if (null != connection)
			{
				if (!connection.isClosed())
				{
					System.out.println("Connection closed");
					connection.close();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
	}

	public void setParameter(int paramIndex, String value) throws SQLException
	{
		statement.setString(paramIndex, value);
	}

	public void setParameter(String paramIndex, String value) throws SQLException
	{
		statement.setString(paramIndex, value);

	}
	public void registerOutputParameterString(int paramIndex) throws SQLException
	{
		statement.registerOutParameter(paramIndex, java.sql.Types.VARCHAR);
	}

	public void setParameter(int paramIndex, long value) throws SQLException
	{
		statement.setLong(paramIndex, value);
	}

	public void registerOutputParameterLong(int paramIndex) throws SQLException
	{
		statement.registerOutParameter(paramIndex, java.sql.Types.BIGINT);
	}

	public ResultSet executeWithResults() throws SQLException
	{
		if (statement.execute())
		{
			return statement.getResultSet();
		}
		return null;
	}

	public void execute() throws SQLException
	{
		statement.execute();
	}
}