package CSCI5308.GroupFormationTool.AnswerSurvey;

import CSCI5308.GroupFormationTool.Course.CurrentCourse;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.UserManager.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AnswerSurveyController {
    private IAnswerSurveyService service = Injector.instance().getAnswerSurveyService();
    @GetMapping("/survey")
    public ModelAndView displaySurvey() {
        String courseId = CurrentCourse.instance().getCurrentCourseId();
        String bannerId = CurrentUser.instance().getBannerId();
        ModelAndView model=new ModelAndView("survey");
        model.addObject("courseId",courseId);
        model.addObject("bannerId",bannerId);
        model.addObject("questions",service.getSurveyQuestionsAndOptions(courseId));
        return model;
    }

    @PostMapping("/survey")
    public void getSurveyAnswers(@ModelAttribute  ISurveyQuestionOptionsModel surveyAnswer){
        System.out.println(surveyAnswer);
    }
}
