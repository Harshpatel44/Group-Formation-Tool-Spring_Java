package CSCI5308.GroupFormationTool.Database;

public interface IDatabaseAbstractFactory {

    StoredProcedure createStoredProcedure(String procedureName) throws Exception;

    IDBConfiguration createDBConfiguration();
}
