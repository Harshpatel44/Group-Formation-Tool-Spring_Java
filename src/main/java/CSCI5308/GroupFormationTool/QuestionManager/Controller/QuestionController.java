package CSCI5308.GroupFormationTool.QuestionManager.Controller;

import CSCI5308.GroupFormationTool.Course.Model.UserId;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.QuestionManager.AccessControl.IQuestionManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class QuestionController {
   private IQuestionManagerService questionManagerService;
    UserId user = new UserId();
    String sortType = "unsorted";
    @RequestMapping("/questionManager")
    public ModelAndView questionManager(@RequestParam(name="courseId") String courseId,
                                        @RequestParam(name="userId") String userId,
                                        @RequestParam(name="userType") String userType,
                                        @RequestParam(name="courseName") String courseName
    ){

        ModelAndView model=new ModelAndView("questionManager");
        model.addObject("courseId",courseId);
        model.addObject("userId",userId);
        model.addObject("userType",userType);
        model.addObject("courseName",courseName);
        model.setViewName("questionManager");
        return model;
    }

    @RequestMapping("/questionList")
    public ModelAndView questionList(@RequestParam(name="courseId") String courseId,
                                     @RequestParam(name="userId") String userId,
                                     @RequestParam(name="userType") String userType,
                                     @RequestParam(name="courseName") String courseName){
        ModelAndView model=new ModelAndView("questionList");
        questionManagerService = Injector.instance().getQuestionManagerService();
        user.setUserId(userId);
        model.addObject("userId",userId);
        model.addObject("courseId",courseId);
        model.addObject("userType",userType);
        model.addObject("courseName",courseName);
        model.addObject("questions",questionManagerService.getQuestions(user,sortType));
        return model;
    }

    @RequestMapping("/sortQuestionByDate")
    public ModelAndView sortByDate(@RequestParam(name="courseId") String courseId,
                                   @RequestParam(name="userId") String userId,
                                   @RequestParam(name="userType") String userType,
                                   @RequestParam(name="courseName") String courseName){
        ModelAndView model=new ModelAndView("questionList");
        sortType="sortByDate";
        model.addObject("userId",userId);
        model.addObject("courseId",courseId);
        model.addObject("userType",userType);
        model.addObject("courseName",courseName);
        model.setViewName("redirect:/questionList");
        return model;
    }

    @RequestMapping("/sortQuestionByTopic")
    public ModelAndView sortByTopic(@RequestParam(name="courseId") String courseId,
                                    @RequestParam(name="userId") String userId,
                                    @RequestParam(name="userType") String userType,
                                    @RequestParam(name="courseName") String courseName){
        ModelAndView model=new ModelAndView("questionList");
        sortType="sortByTopic";
        model.addObject("userId",userId);
        model.addObject("courseId",courseId);
        model.addObject("userType",userType);
        model.addObject("courseName",courseName);
        model.setViewName("redirect:/questionList");
        return model;
    }

    @RequestMapping("/deleteQuestion")
    public ModelAndView deleteQuestion(@RequestParam(name="questionId") Integer questionId,
                                       @RequestParam(name="courseId") String courseId,
                                       @RequestParam(name="userId") String userId,
                                       @RequestParam(name="userType") String userType,
                                       @RequestParam(name="courseName") String courseName){
        ModelAndView model=new ModelAndView("questionList");
        questionManagerService = Injector.instance().getQuestionManagerService();
        model.addObject("userId",userId);
        model.addObject("courseId",courseId);
        model.addObject("userType",userType);
        model.addObject("courseName",courseName);
        questionManagerService.deleteQuestion(questionId,userId);
        user.setUserId(userId);
        model.addObject("questions",questionManagerService.getQuestions(user, sortType));
        model.setViewName("redirect:/questionList");
        return model;
    }
}
