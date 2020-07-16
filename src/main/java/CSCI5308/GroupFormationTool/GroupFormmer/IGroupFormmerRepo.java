package CSCI5308.GroupFormationTool.GroupFormmer;

public interface IGroupFormmerRepo {
	
	public boolean saveGroupFormula(IGroupFilter groupFilter,String courseID);
	public boolean deleteGroupFormula(String courseID);
	public IGroupFilter getGroupFormula(String courseID);
}
