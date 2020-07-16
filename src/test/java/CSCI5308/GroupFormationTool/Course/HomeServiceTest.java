package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.UserManager.UserRepository;
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
  public UserRepository userRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        homeRepository = mock(HomeRepository.class);
        userRepository = mock(UserRepository.class);
        homeService = new HomeService(homeRepository,userRepository);
    }

    @Test
    public void getCoursesTest() throws Exception {
        String bannerID = "B23456789";
        List<ICourse> courseList = new ArrayList<ICourse>();
        ICourse course = new Course();
        course.setCourseId("CSCI1");
        course.setCourseName("DATA");
        course.setRole("Student");
        courseList.add(course);
        when(homeRepository.getCourseFromBannerID(bannerID,false)).thenReturn(courseList);
        List<ICourse> returnedList = homeService.getCourseFromBannerID(bannerID);
        assertEquals(returnedList,courseList);
    }

//    @Test
//	public void checkRoleOfUserTest() {
//		String bannerID = "B23445678";
//		when(userRepository.checkIfUserIsGuest(bannerID)).thenReturn(true);
//		assertEquals(false,homeService.checkRoleOfUser(bannerID));
//        when(userRepository.checkIfUserIsGuest(bannerID)).thenReturn(false);
//        assertEquals(true,homeService.checkRoleOfUser(bannerID));
//	}
}
