package CSCI5308.GroupFormationTool.QuestionManager;

import CSCI5308.GroupFormationTool.Injector;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionController {
   private IQuestionManagerService questionManagerService;
    String sortType = "unsorted";
    @RequestMapping("/questionManager")
    public ModelAndView questionManager(){
        ModelAndView model=new ModelAndView("questionManager");
        model.setViewName("questionManager");
        return model;
    }

    @RequestMapping("/questionList")
    public ModelAndView questionList(@RequestParam(name="courseId") String courseId,
                                     @RequestParam(name="userId") String userId,
                                     @RequestParam(name="userRole") String userRole,
                                     @RequestParam(name="courseName") String courseName) throws Exception {
        ModelAndView model=new ModelAndView("questionList");
        questionManagerService = Injector.instance().getQuestionManagerService();
        model.addObject("userId",userId);
        model.addObject("courseId",courseId);
        model.addObject("userRole",userRole);
        model.addObject("courseName",courseName);
        model.addObject("prompt",false);
        model.addObject("questions",questionManagerService.getQuestions(userId,sortType));
        return model;
    }

    @RequestMapping("/sortQuestionByDate")
    public ModelAndView sortByDate(@RequestParam(name="courseId") String courseId,
                                   @RequestParam(name="userId") String userId,
                                   @RequestParam(name="userRole") String userRole,
                                   @RequestParam(name="courseName") String courseName){
        ModelAndView model=new ModelAndView("questionList");
        sortType="sortByDate";
        model.addObject("userId",userId);
        model.addObject("courseId",courseId);
        model.addObject("userRole",userRole);
        model.addObject("courseName",courseName);
        model.addObject("prompt",false);
        model.setViewName("redirect:/questionList");
        return model;
    }

    @RequestMapping("/sortQuestionByTopic")
    public ModelAndView sortByTopic(@RequestParam(name="courseId") String courseId,
                                    @RequestParam(name="userId") String userId,
                                    @RequestParam(name="userRole") String userRole,
                                    @RequestParam(name="courseName") String courseName){
        ModelAndView model=new ModelAndView("questionList");
        sortType="sortByTopic";
        model.addObject("userId",userId);
        model.addObject("courseId",courseId);
        model.addObject("userRole",userRole);
        model.addObject("courseName",courseName);
        model.addObject("prompt",false);
        model.setViewName("redirect:/questionList");
        return model;
    }

    @RequestMapping("/deleteQuestion")
    public ModelAndView deleteQuestion(@RequestParam(name="selectedQuestionId") Integer questionId,
                                       @RequestParam(name="courseId") String courseId,
                                       @RequestParam(name="userId") String userId,
                                       @RequestParam(name="userRole") String userRole,
                                       @RequestParam(name="courseName") String courseName) throws Exception {
        ModelAndView model=new ModelAndView("questionList");
        questionManagerService = Injector.instance().getQuestionManagerService();
        model.addObject("userId",userId);
        model.addObject("courseId",courseId);
        model.addObject("userRole",userRole);
        model.addObject("courseName",courseName);
        questionManagerService.deleteQuestion(questionId,userId);
        model.addObject("questions",questionManagerService.getQuestions(userId, sortType));
        model.setViewName("redirect:/questionList");
        return model;
    }

    @RequestMapping("/checkResponses")
    public ModelAndView checkResponses(@RequestParam(name="questionId") Integer questionId,
                                       @RequestParam(name="courseId") String courseId,
                                       @RequestParam(name="userId") String userId,
                                       @RequestParam(name="userRole") String userRole,
                                       @RequestParam(name="courseName") String courseName) throws Exception {
        ModelAndView model=new ModelAndView("questionList");
        model.addObject("userId",userId);
        model.addObject("courseId",courseId);
        model.addObject("userRole",userRole);
        model.addObject("courseName",courseName);
        model.addObject("selectedQuestionId",questionId);
        boolean prompt = Injector.instance().getQuestionResponsesService().checkIfResponsesPresentService(questionId);
        if(prompt==false){
            model.setViewName("redirect:/deleteQuestion");
        }
        else{
            model.addObject("prompt",prompt);
            model.setViewName("questionList");
        }
        model.addObject("questions",questionManagerService.getQuestions(userId, sortType));
        return model;
    }
}
