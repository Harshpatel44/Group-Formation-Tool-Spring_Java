package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.UserManager.UserManagerAbstractFactory;
import CSCI5308.GroupFormationTool.UserManager.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService implements IHomeService {
    private static final Logger LOG = LogManager.getLogger();
    private IHomeRepository homeRepository;

    public HomeService() {
    }

    public HomeService(HomeRepository homeRepository, UserRepository userRepository) {
        CourseAbstractFactory.instance().setHomeRepository(homeRepository);
        UserManagerAbstractFactory.instance().setUserRepository(userRepository);
    }

    public List<ICourse> getCourseFromBannerID(String bannerID) throws Exception {
        homeRepository = CourseAbstractFactory.instance().getHomeRepository();
        boolean GuestOrNot = UserManagerAbstractFactory.instance().getUserService().checkIfUserIsGuest(bannerID);
        List<ICourse> courseList = homeRepository.getCourseFromBannerID(bannerID, GuestOrNot);
        LOG.info("Operation = Get courses from banner id " + bannerID + ", Status = Success");
        return courseList;
    }

    @Override
    public boolean checkRoleOfUser(String bannerID) {
        boolean result = UserManagerAbstractFactory.instance().getUserService().checkIfUserIsGuest(bannerID);
        if (result) {
            LOG.warn("Operation = Check if user " + bannerID + " is a guest, Status = Fail");
            return false;
        } else {
            LOG.info("Operation = Check if user " + bannerID + " is a guest, Status = Success");
            return true;
        }
    }
}
	
	
	


