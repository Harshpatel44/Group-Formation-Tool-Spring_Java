package CSCI5308.GroupFormationTool.GroupFormmer;

import CSCI5308.GroupFormationTool.Course.Course;
import CSCI5308.GroupFormationTool.Course.CurrentCourse;
import CSCI5308.GroupFormationTool.UserManager.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AnswerSurvey.ISurveyQuestionOptionsModel;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

@Controller
public class GroupFormmmerController {
	private IGroupFormmerService groupFormmerService;
	
	@PostMapping("/formgroups/saveGroupFormula")
    public ModelAndView getSurveyAnswers(HttpServletRequest req){
		groupFormmerService = Injector.instance().getGroupFormmerService();
		IGroupFilter groupFilter = groupFormmerService.createGroupFilterHashMap(req);
        groupFormmerService.saveGroupFormula(groupFilter, CurrentCourse.instance().getCurrentCourseId());
        ModelAndView mv = new ModelAndView("course");
		mv.addObject("courseId", CurrentCourse.instance().getCurrentCourseId());
		mv.addObject("courseName", CurrentCourse.instance().getCurrentCourseName());
		mv.addObject("userRole", CurrentCourse.instance().getCurrentCourseUserRole());
        mv.addObject("checkRole",true);
        return mv;
    }
	@PostMapping("/formgroups/getFormulaQuestions")
	public ModelAndView getFormulaQuestions()
	{
		ModelAndView mv = new ModelAndView("formgroups");
		groupFormmerService = Injector.instance().getGroupFormmerService();
		List<ISurveyQuestionOptionsModel> surveyQuestions = groupFormmerService.getSurveyQuestionsForGroupFormula(CurrentCourse.instance().getCurrentCourseId());
		mv.addObject("surveyGroupQuestions",surveyQuestions);
		mv.addObject("courseId", CurrentCourse.instance().getCurrentCourseId());
		mv.addObject("courseName", CurrentCourse.instance().getCurrentCourseName());
		mv.addObject("userRole", CurrentCourse.instance().getCurrentCourseUserRole());
		return mv;
	}

	@RequestMapping("/formgroups/runalgorithm")
	public ModelAndView runAlgorithm() {
		ModelAndView mv = new ModelAndView("formationalgorithm");
		mv.addObject("courseId", CurrentCourse.instance().getCurrentCourseId());
		mv.addObject("courseName", CurrentCourse.instance().getCurrentCourseName());
		mv.addObject("userRole", CurrentCourse.instance().getCurrentCourseUserRole());
		return mv;
	}

	@PostMapping("/formgroups/algorithmresults")
	public ModelAndView algorithmResults(@RequestParam("teamSize") int teamSize) {
		groupFormmerService = Injector.instance().getGroupFormmerService();
		String courseId = CurrentCourse.instance().getCurrentCourseId();
		System.out.println(teamSize+courseId);
		HashMap<Integer, ArrayList<String>> teamsWithBannerId = groupFormmerService.FormGroups(courseId, teamSize);
		ModelAndView mv = new ModelAndView("algorithmresults");
		mv.addObject("teamsresults",teamsWithBannerId);
		mv.addObject("courseId", courseId);
		mv.addObject("courseName", CurrentCourse.instance().getCurrentCourseName());
		mv.addObject("userRole", CurrentCourse.instance().getCurrentCourseUserRole());
		return mv;
	}
}
