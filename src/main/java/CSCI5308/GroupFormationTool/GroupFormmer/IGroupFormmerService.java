package CSCI5308.GroupFormationTool.GroupFormmer;

import java.util.List;

import CSCI5308.GroupFormationTool.AnswerSurvey.ISurveyQuestionOptionsModel;

public interface IGroupFormmerService {

	public void FormGroups(String courseID,int teamSize);
	public boolean saveGroupFormula(IGroupFilter groupFilter,String courseID);
	public List<ISurveyQuestionOptionsModel> getSurveyQuestionsForGroupFormula(String courseId);
}
