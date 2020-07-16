package CSCI5308.GroupFormationTool.GroupFormmer;

import CSCI5308.GroupFormationTool.UserManager.UserManagerAbstractConcrete;
import CSCI5308.GroupFormationTool.UserManager.UserManagerAbstractFactory;

public abstract class GroupFormmerAbstractFactory {

    private static GroupFormmerAbstractFactory instance = null;

    public static GroupFormmerAbstractFactory instance(){
        if (instance == null) {
            instance = new GroupFormmerAbstractConcrete();
        }
        return instance;
    }

    public abstract IGroupFormmerService getGroupFormmerService();
    public abstract IGroupFormmerRepo getGroupFormmerRepo();
    public abstract IGroupFilter getGroupFilter();

}
