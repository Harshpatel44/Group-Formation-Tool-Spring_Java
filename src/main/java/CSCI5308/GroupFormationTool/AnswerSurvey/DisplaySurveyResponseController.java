package CSCI5308.GroupFormationTool.AnswerSurvey;

import CSCI5308.GroupFormationTool.Course.CurrentCourse;
import CSCI5308.GroupFormationTool.Injector;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DisplaySurveyResponseController {
    private IDisplaySurveyResponseService service = Injector.instance().getDisplaySurveyResponseService();

    @GetMapping("/displayResponse")
    public ModelAndView displayResponse() {
        String courseId = CurrentCourse.instance().getCurrentCourseId();
        List<String> users = service.getUsersWhoAnsweredSurvey(courseId);
        ModelAndView model = new ModelAndView("displayResponse");
        model.addObject("courseId",courseId);
        model.addObject("bannerIds",users);
        model.addObject("userResponse",service.getSurveyResponse(users,courseId));
        return model;
    }
    @GetMapping("/displayResponse/{bannerId}")
    public ModelAndView displayIndividualResponse(@PathVariable ("bannerId") String bannerId)
    {
        String courseId = CurrentCourse.instance().getCurrentCourseId();
        List<String> users = new ArrayList<>();
        users.add(bannerId);
        ModelAndView model = new ModelAndView("displayResponse");
        model.addObject("response",service.getSurveyResponse(users,courseId));
        return model;
    }
}
