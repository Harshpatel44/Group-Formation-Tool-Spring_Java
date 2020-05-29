package CSCI5308.GroupFormationTool.AccessControl;

import org.springframework.web.servlet.ModelAndView;

public interface IUserController {

    public boolean createUser();
    public ModelAndView adminPage();
}
