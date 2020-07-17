package CSCI5308.GroupFormationTool.SurveyManager;

import CSCI5308.GroupFormationTool.Course.CurrentCourse;
import CSCI5308.GroupFormationTool.UserManager.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SurveyController {
    private ISurveyManagerService surveyManagerService;
    private final CurrentCourse currentCourse = CurrentCourse.instance();

    @RequestMapping("/surveyQuestion")
    public ModelAndView surveyManager() throws Exception {
        String courseId = CurrentCourse.instance().getCurrentCourseId();
        String bannerId = CurrentUser.instance().getBannerId();
        surveyManagerService = SurveyManagerAbstractFactory.instance().getSurveyManagerService();
        if (surveyManagerService.checkPublish()) {
            ModelAndView model = new ModelAndView("alreadyPublished");
            model.setViewName("alreadyPublished");
            return model;
        } else {
            surveyManagerService.getSurveyQuestions();
            ModelAndView model = new ModelAndView("surveyQuestion");
            model.addObject("userId", bannerId);
            model.addObject("courseId", courseId);
            model.addObject("AlreadyAddedSurveyQuestions", surveyManagerService.AlreadyAddedSurveyQuestions());
            model.addObject("NotAddedSurveyQuestions", surveyManagerService.NotAddedSurveyQuestions());
            model.setViewName("surveyQuestion");
            return model;
        }
    }

    @RequestMapping("/AddQuestionToSurvey")
    public ModelAndView AddQuestionToSurvey(@RequestParam(name = "questionId") Integer questionId) throws Exception {
        String courseId = CurrentCourse.instance().getCurrentCourseId();
        String bannerId = CurrentUser.instance().getBannerId();
        surveyManagerService = SurveyManagerAbstractFactory.instance().getSurveyManagerService();
        surveyManagerService.AddQuestionToSurvey(questionId);
        ModelAndView model = new ModelAndView("surveyQuestion");
        model.addObject("userId", bannerId);
        model.addObject("courseId", courseId);
        model.addObject("AlreadyAddedSurveyQuestions", surveyManagerService.AlreadyAddedSurveyQuestions());
        model.addObject("NotAddedSurveyQuestions", surveyManagerService.NotAddedSurveyQuestions());
        model.setViewName("redirect:/surveyQuestion");
        return model;
    }

    @RequestMapping("/RemoveQuestionFromSurvey")
    public ModelAndView RemoveQuestionFromSurvey(@RequestParam(name = "questionId") Integer questionId) throws Exception {
        String courseId = CurrentCourse.instance().getCurrentCourseId();
        String bannerId = CurrentUser.instance().getBannerId();
        surveyManagerService = SurveyManagerAbstractFactory.instance().getSurveyManagerService();
        surveyManagerService.RemoveQuestionFromSurvey(questionId);
        ModelAndView model = new ModelAndView("surveyQuestion");
        model.addObject("userId", bannerId);
        model.addObject("courseId", courseId);
        model.addObject("AlreadyAddedSurveyQuestions", surveyManagerService.AlreadyAddedSurveyQuestions());
        model.addObject("NotAddedSurveyQuestions", surveyManagerService.NotAddedSurveyQuestions());
        model.setViewName("redirect:/surveyQuestion");
        return model;
    }

    @RequestMapping("/publishSurvey")
    public ModelAndView PublishSurvey() throws Exception {
        surveyManagerService = SurveyManagerAbstractFactory.instance().getSurveyManagerService();
        surveyManagerService.PublishSurvey();
        String userId = CurrentUser.instance().getBannerId();
        ModelAndView model = new ModelAndView("questionManager");
        model.addObject("userId", userId);
        model.setViewName("redirect:/questionManager");
        return model;
    }

    @RequestMapping("/unpublishSurvey")
    public ModelAndView UnpublishSurvey() throws Exception {
        surveyManagerService = SurveyManagerAbstractFactory.instance().getSurveyManagerService();
        surveyManagerService.UnpublishSurvey();
        ModelAndView model = new ModelAndView("surveyQuestion");
        model.setViewName("redirect:/surveyQuestion");
        return model;
    }
}
