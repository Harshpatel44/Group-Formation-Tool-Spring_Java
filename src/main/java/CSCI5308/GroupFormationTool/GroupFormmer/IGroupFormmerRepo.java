package CSCI5308.GroupFormationTool.GroupFormmer;

public interface IGroupFormmerRepo {

    boolean saveGroupFormula(IGroupFilter groupFilter, String courseID);

    boolean deleteGroupFormula(String courseID);

    IGroupFilter getGroupFormula(String courseID);
}
