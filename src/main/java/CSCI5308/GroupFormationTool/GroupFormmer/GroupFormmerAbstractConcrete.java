package CSCI5308.GroupFormationTool.GroupFormmer;

public class GroupFormmerAbstractConcrete extends GroupFormmerAbstractFactory {

    private IGroupFilter groupFilter;
    private IGroupFormmerRepo groupFormmerRepo;
    private IGroupFormmerService groupFormmerService;


    @Override
    public IGroupFilter getGroupFilter() {
        if (groupFilter == null) {
            groupFilter = new GroupFilter();
        }
        return groupFilter;
    }

    @Override
    public IGroupFormmerRepo getGroupFormmerRepo() {
        if (groupFormmerRepo == null) {
            groupFormmerRepo = new GroupFormmerRepo();
        }
        return groupFormmerRepo;
    }

    @Override
    public IGroupFormmerService getGroupFormmerService() {
        if (groupFormmerService == null) {
            groupFormmerService = new GroupFormmerService();
        }
        return groupFormmerService;
    }

	@Override
	public void setGroupFormerRepo(IGroupFormmerRepo formmerRepo) {
		this.groupFormmerRepo = formmerRepo;	
	}

}
