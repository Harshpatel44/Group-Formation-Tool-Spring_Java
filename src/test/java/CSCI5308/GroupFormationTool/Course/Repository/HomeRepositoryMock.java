package CSCI5308.GroupFormationTool.Course.Repository;

import CSCI5308.GroupFormationTool.Course.ICourse;
import CSCI5308.GroupFormationTool.Course.Course;
import CSCI5308.GroupFormationTool.UserManager.IUserRole;

import java.util.ArrayList;
import java.util.List;

public class HomeRepositoryMock {

    private List<ICourse> CourseList = new ArrayList<ICourse>();

    public boolean checkRoleTest(IUserRole userRole) {
        boolean result = true;

        if(userRole.getUserId().equals("B00123456"))
        {
            result = false;
        }
        return result;
    }

    public List<ICourse> getcourseTest(IUserRole userRole) {
        List<ICourse> courseList = new ArrayList<ICourse>();
        if(userRole.getUserId().equals("B00123456"))
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
