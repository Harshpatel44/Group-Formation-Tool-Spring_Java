package CSCI5308.GroupFormationTool.Course;

import java.util.ArrayList;
import java.util.List;

public class HomeRepositoryMock {

    private List<ICourse> CourseList = new ArrayList<ICourse>();


    public List<ICourse> getCourseFromBannerIDTest(String bannerID) {
        List<ICourse> courseList = new ArrayList<ICourse>();
        if(bannerID.equals("B00123456"))
        {
            ICourse course = new Course();
            course.setCourseId("CSCI1");
            course.setCourseName("DATA");
            course.setRole("Student");
            courseList.add(course);
        }
        return CourseList;
    }
}
