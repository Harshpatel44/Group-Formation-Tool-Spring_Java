package CSCI5308.GroupFormationTool.Database;

public abstract class IDatabaseAbstractFactory {

    public static IDatabaseAbstractFactory instance = null;

    public static IDatabaseAbstractFactory instance(){
        if (instance == null) {
            instance = new DatabaseAbstractFactory();
        }
        return instance;
    }

    public abstract StoredProcedure createStoredProcedure(String procedureName) throws Exception;

    public abstract IDBConfiguration createDBConfiguration();
}
