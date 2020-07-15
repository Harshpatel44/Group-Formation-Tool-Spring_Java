package CSCI5308.GroupFormationTool.Database;

public class DatabaseAbstractFactory implements IDatabaseAbstractFactory {

    @Override
    public StoredProcedure createStoredProcedure(String procedureName) throws Exception {
        return new StoredProcedure(procedureName);
    }

    @Override
    public IDBConfiguration createDBConfiguration(){
        return new DBConfiguration();
    }
}
