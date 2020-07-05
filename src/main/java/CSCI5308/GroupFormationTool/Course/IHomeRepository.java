package CSCI5308.GroupFormationTool.Course;

import java.util.List;

public interface IHomeRepository {
    List<ICourse> getCourseFromBannerID(String bannerID, boolean GuestOrNot) throws Exception;

}
