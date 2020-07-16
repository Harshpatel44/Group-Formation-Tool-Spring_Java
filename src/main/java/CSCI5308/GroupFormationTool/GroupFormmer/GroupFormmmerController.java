package CSCI5308.GroupFormationTool.GroupFormmer;

import CSCI5308.GroupFormationTool.AnswerSurvey.ISurveyQuestionOptionsModel;
import CSCI5308.GroupFormationTool.Course.CurrentCourse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class GroupFormmmerController {
    private IGroupFormmerService groupFormmerService;

    @PostMapping("/formgroups/saveGroupFormula")
    public ModelAndView getSurveyAnswers(HttpServletRequest req) {
        groupFormmerService = GroupFormmerAbstractFactory.instance().getGroupFormmerService();
        IGroupFilter groupFilter = groupFormmerService.createGroupFilterHashMap(req);
        groupFormmerService.saveGroupFormula(groupFilter, CurrentCourse.instance().getCurrentCourseId());
        ModelAndView mv = new ModelAndView("course");
        mv.addObject("courseId", CurrentCourse.instance().getCurrentCourseId());
        mv.addObject("courseName", CurrentCourse.instance().getCurrentCourseName());
        mv.addObject("userRole", CurrentCourse.instance().getCurrentCourseUserRole());
        mv.addObject("checkRole", true);
        return mv;
    }

    @PostMapping("/formgroups/getFormulaQuestions")
    public ModelAndView getFormulaQuestions() {
        ModelAndView mv = new ModelAndView("formgroups");
        groupFormmerService = GroupFormmerAbstractFactory.instance().getGroupFormmerService();
        List<ISurveyQuestionOptionsModel> surveyQuestions = groupFormmerService.getSurveyQuestionsForGroupFormula(CurrentCourse.instance().getCurrentCourseId());
        mv.addObject("surveyGroupQuestions", surveyQuestions);
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
        groupFormmerService = GroupFormmerAbstractFactory.instance().getGroupFormmerService();
        String courseId = CurrentCourse.instance().getCurrentCourseId();
        System.out.println(teamSize + courseId);
        HashMap<Integer, ArrayList<String>> teamsWithBannerId = groupFormmerService.FormGroups(courseId, teamSize);
        ModelAndView mv = new ModelAndView("algorithmresults");
        mv.addObject("teamsresults", teamsWithBannerId);
        mv.addObject("courseId", courseId);
        mv.addObject("courseName", CurrentCourse.instance().getCurrentCourseName());
        mv.addObject("userRole", CurrentCourse.instance().getCurrentCourseUserRole());
        return mv;
    }
}
