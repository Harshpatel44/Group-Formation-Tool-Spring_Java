package CSCI5308.GroupFormationTool.Course.Service;



import CSCI5308.GroupFormationTool.Course.Model.Course;
import CSCI5308.GroupFormationTool.Course.Model.UserId;
import CSCI5308.GroupFormationTool.Course.Repository.HomeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HomeServiceTest {
  public HomeRepository homeRepository;
  public HomeService homeService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        homeRepository = mock(HomeRepository.class);
        homeService = new HomeService(homeRepository);
    }

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
        when(homeRepository.getcourse(user)).thenReturn(courseList);
        List<Course> returnedList = homeService.getCourses(user);
        assertEquals(returnedList,courseList);
    }


    @Test //when user is guest
    public void checkRoleTestGuest() {
        UserId user = new UserId();
        user.setUserId("B00103456");//guest
        when(homeRepository.checkRole(user)).thenReturn(true);
        assertFalse(homeService.checkRole(user));
    }


    @Test //when user is Not a guest
    public void checkRoleTestNotGuest() {
        UserId user = new UserId();
        user.setUserId("B00123456");
        when(homeRepository.checkRole(user)).thenReturn(false);
        assertTrue(homeService.checkRole(user));
    }
}
