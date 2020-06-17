package CSCI5308.GroupFormationTool.Course.Service;


import CSCI5308.GroupFormationTool.Course.Model.Course;
import CSCI5308.GroupFormationTool.Course.Model.UserId;
import CSCI5308.GroupFormationTool.Course.Repository.HomeRepositoryMock;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HomeServiceTest {


    @Mock
    private HomeRepositoryMock homeRepositoryMock;

    @Test
    public void getCoursesTest(){
        UserId user = new UserId();
        List<Course> courseList = new ArrayList<Course>();
        Course course = new Course();
        course.setCourseId("CSCI1");
        course.setCourseName("DATA");
        course.setRole("Student");
        courseList.add(course);
        user.setUserId("B00123456");
        when(homeRepositoryMock.getcourseTest(user)).thenReturn(courseList);
        List<Course> returnedList = homeRepositoryMock.getcourseTest(user);
        assertEquals(returnedList,courseList);
    }


    @Test //when user is guest
    public void checkRoleTestGuest() {
        UserId user = new UserId();
        user.setUserId("B00103456");//guest
        when(homeRepositoryMock.checkRoleTest(user)).thenReturn(true);
        assertEquals(true,homeRepositoryMock.checkRoleTest(user));
    }


    @Test //when user is Not a guest
    public void checkRoleTestNotGuest() {
        UserId user = new UserId();
        user.setUserId("B00123456");
        when(homeRepositoryMock.checkRoleTest(user)).thenReturn(false);
        assertEquals(false,homeRepositoryMock.checkRoleTest(user));
    }


}
