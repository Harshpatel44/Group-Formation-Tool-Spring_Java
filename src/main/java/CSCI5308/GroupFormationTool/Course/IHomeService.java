package CSCI5308.GroupFormationTool.Course;

import java.util.List;

public interface IHomeService {
    List<ICourse> getCourseFromBannerID(String bannerID) throws Exception;

    boolean checkRoleOfUser(String bannerID) throws Exception;
}
