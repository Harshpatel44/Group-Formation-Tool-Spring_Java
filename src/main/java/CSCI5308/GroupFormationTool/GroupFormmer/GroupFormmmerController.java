package CSCI5308.GroupFormationTool.GroupFormmer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AnswerSurvey.ISurveyQuestionOptionsModel;

@Controller
public class GroupFormmmerController {
	private IGroupFormmerService groupFormmerService;
	
	@PostMapping("/saveGroupFormula")
    public void getSurveyAnswers(@ModelAttribute  IGroupFilter groupFilter,@RequestParam("courseID") String courseID){
        groupFormmerService = Injector.instance().getGroupFormmerService();
        groupFormmerService.saveGroupFormula(groupFilter, courseID);
    }
	@GetMapping("/getFormulaQuestions")
	public void getFormulaQuestions(@RequestParam("courseID") String courseID)
	{
		groupFormmerService = Injector.instance().getGroupFormmerService();
		groupFormmerService.getSurveyQuestionsForGroupFormula(courseID);
	}
	@PostMapping("/runAlgorithm")
	public void runAlgorithm(@RequestParam("courseID") String courseID,@RequestParam("teamSize") int teamSize) {
		groupFormmerService = Injector.instance().getGroupFormmerService();
		groupFormmerService.FormGroups(courseID,teamSize);
	}
			
}
