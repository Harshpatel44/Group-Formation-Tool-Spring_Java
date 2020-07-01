package CSCI5308.GroupFormationTool.Course.Service;



import CSCI5308.GroupFormationTool.Course.*;
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
        IUserRole userRole = new UserRole();
        List<ICourse> courseList = new ArrayList<ICourse>();
        ICourse course = new Course();
        course.setCourseId("CSCI1");
        course.setCourseName("DATA");
        course.setRole("Student");
        courseList.add(course);
        userRole.setUserId("B00123456");
        when(homeRepository.getcourse(userRole)).thenReturn(courseList);
        List<ICourse> returnedList = homeService.getCourses(userRole);
        assertEquals(returnedList,courseList);
    }


    @Test //when user is guest
    public void checkRoleTestGuest() {
        IUserRole userRole = new UserRole();
        userRole.setUserId("B00103456");//guest
        when(homeRepository.checkRole(userRole)).thenReturn(true);
        assertFalse(homeService.checkRole(userRole));
    }


    @Test //when user is Not a guest
    public void checkRoleTestNotGuest() {
        IUserRole userRole = new UserRole();
        userRole.setUserId("B00123456");
        when(homeRepository.checkRole(userRole)).thenReturn(false);
        assertTrue(homeService.checkRole(userRole));
    }
}
