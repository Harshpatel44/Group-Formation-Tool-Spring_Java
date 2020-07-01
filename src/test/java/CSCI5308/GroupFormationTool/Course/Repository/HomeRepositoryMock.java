package CSCI5308.GroupFormationTool.Course.Repository;

import CSCI5308.GroupFormationTool.Course.AccessControl.IUserId;
import CSCI5308.GroupFormationTool.Course.Model.Course;

import java.util.ArrayList;
import java.util.List;

public class HomeRepositoryMock {

    private List<Course> CourseList = new ArrayList<Course>();

    public boolean checkRoleTest(IUserId user) {
        boolean result = true;

        if(user.getUserId().equals("B00123456"))
        {
            result = false;
        }
        return result;
    }

    public List<Course> getcourseTest(IUserId user) {
        List<Course> courseList = new ArrayList<Course>();
        if(user.getUserId().equals("B00123456"))
        {
            Course course = new Course();
            course.setCourseId("CSCI1");
            course.setCourseName("DATA");
            course.setRole("Student");
            courseList.add(course);
        }
        return CourseList;
    }
}
