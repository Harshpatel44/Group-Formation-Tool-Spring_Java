package CSCI5308.GroupFormationTool.Database;

public class DatabaseAbstractConcrete extends DatabaseAbstractFactory {

    private DBConfiguration dbConfiguration;

    public StoredProcedure createStoredProcedure(String procedureName) throws Exception {
        return new StoredProcedure(procedureName);
    }

    public IDBConfiguration createDBConfiguration() {
        if (null == dbConfiguration) {
            dbConfiguration = new DBConfiguration();
        }
        return dbConfiguration;
    }
}
