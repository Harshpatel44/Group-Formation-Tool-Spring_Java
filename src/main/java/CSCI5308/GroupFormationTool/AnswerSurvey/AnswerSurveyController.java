package CSCI5308.GroupFormationTool.AnswerSurvey;

import CSCI5308.GroupFormationTool.Course.CurrentCourse;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.UserManager.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

@Controller
public class AnswerSurveyController {
    private IAnswerSurveyService service = Injector.instance().getAnswerSurveyService();
    private List<ISurveyQuestionOptionsModel> questionsAndOptions = null;
    @GetMapping("/survey")
    public ModelAndView displaySurvey() {
        String courseId = CurrentCourse.instance().getCurrentCourseId();
        String bannerId = CurrentUser.instance().getBannerId();
        ModelAndView model=new ModelAndView("survey");
        model.addObject("courseId",courseId);
        model.addObject("bannerId",bannerId);
        questionsAndOptions = service.getSurveyQuestionsAndOptions(courseId);
        model.addObject("questions",questionsAndOptions);
        return model;
    }

    @PostMapping("/survey")
    public void getSurveyAnswers(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HashMap<Integer,ArrayList<String>> surveyResponses = new HashMap<>();
        Enumeration e = req.getParameterNames();
        while(e.hasMoreElements()){
            String name = (String) e.nextElement();
            if(name.equals("_csrf")==false){
                String[] value = req.getParameterValues(name);
                ArrayList<String> tempList = new ArrayList<>();
                if(value.length>1){
                    for(int i=0;i<value.length;i++){
                        tempList.add(value[i]);
                    }
                    surveyResponses.put(Integer.valueOf(name),tempList);
                }
                else{
                    tempList.add(value[0]);
                    surveyResponses.put(Integer.valueOf(name),tempList);
                }
            }
        }
        Injector.instance().getAnswerSurveyService().storeSurveyResponses(surveyResponses);
    }
}
