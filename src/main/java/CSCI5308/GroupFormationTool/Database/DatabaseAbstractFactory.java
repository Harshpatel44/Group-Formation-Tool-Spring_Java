package CSCI5308.GroupFormationTool.Database;

public abstract class DatabaseAbstractFactory {

    public static DatabaseAbstractFactory instance = null;

    public static DatabaseAbstractFactory instance() {
        if (instance == null) {
            instance = new DatabaseAbstractConcrete();
        }
        return instance;
    }

    public abstract StoredProcedure createStoredProcedure(String procedureName) throws Exception;

    public abstract IDBConfiguration createDBConfiguration();
}
