package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.PasswordManager.UserPasswordPolicy;
import CSCI5308.GroupFormationTool.UserAuthentication.IUserNotification;
import CSCI5308.GroupFormationTool.UserAuthentication.UserAuthenticationAbstractFactory;
import CSCI5308.GroupFormationTool.UserAuthentication.UserNotification;
import CSCI5308.GroupFormationTool.UserManager.IUserRepository;
import CSCI5308.GroupFormationTool.UserManager.User;
import CSCI5308.GroupFormationTool.UserManager.UserManagerAbstractFactory;
import CSCI5308.GroupFormationTool.UserManager.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
public class CsvImporterServiceTest {
    private IUserRepository userRepository;
    private ICourseRepository courseRepository;
    private IUserNotification userNotification;
    private ICsvImporter csvImporterService;


    @BeforeEach
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        userRepository = mock(UserRepository.class);
        courseRepository = mock(CourseRepository.class);
        userNotification = mock(UserNotification.class);
        UserManagerAbstractFactory.instance().setUserRepository(userRepository);
        CourseAbstractFactory.instance().setCourseRepository(courseRepository);
        UserAuthenticationAbstractFactory.instance().setUserNotification(userNotification);
        csvImporterService = CourseAbstractFactory.instance().getCsvImporter();
        UserPasswordPolicy.setInstance(2, 23, 1, 1, 1, "@#");
    }


    @Test
    public void testCsv() throws Exception {
        List<String> bannerIds = new ArrayList<String>() {
        };
        bannerIds.add("B00854475");
        bannerIds.add("B00854476");
        when(userRepository.getAllBannerIds()).thenReturn(bannerIds);
        when(userRepository.createUser(Matchers.any(User.class))).thenReturn(true);
        when(userNotification.sendUserCredentials(Matchers.any(User.class))).thenReturn(true);
        when(courseRepository.getUserDetailsOnCourse(Matchers.any(User.class), Matchers.any(String.class)))
                .thenReturn(false);
        when(courseRepository.enrollStudentForCourse(Matchers.any(User.class), Matchers.any(String.class)))
                .thenReturn(true);
        assertEquals(csvImporterService.StudentsEnrolledForCourse("CSCI101", CsvMockFile.getFile()).get(1).size(), 0);
    }


    @Test
    public void testCsvWhenUserIsNotCreated() throws Exception {
        List<String> bannerIds = new ArrayList<String>() {
            {
                add("B00854475");
                add("B00854476");
            }
        };
        when(userRepository.getAllBannerIds()).thenReturn(bannerIds);
        when(userRepository.createUser(Matchers.any(User.class))).thenReturn(false);
        assertEquals(csvImporterService.StudentsEnrolledForCourse("CSCI101", CsvMockFile.getFile()).get(2).size(), 1);
    }


    @Test
    public void testCsvWhenUserHasMultipleRows() throws Exception {
        List<String> bannerIds = new ArrayList<String>() {
            {
                add("B00854475");
                add("B00854476");
            }
        };
        when(userRepository.getAllBannerIds()).thenReturn(bannerIds);
        when(userRepository.createUser(Matchers.any(User.class))).thenReturn(true);
        when(userNotification.sendUserCredentials(Matchers.any(User.class))).thenReturn(true);
        when(courseRepository.getUserDetailsOnCourse(Matchers.any(User.class), Matchers.any(String.class)))
                .thenReturn(true);
        assertEquals(csvImporterService.StudentsEnrolledForCourse("CSCI101", CsvMockFile.getFile()).get(1).size(), 0);
        assertEquals(csvImporterService.StudentsEnrolledForCourse("CSCI101", CsvMockFile.getFile()).get(2).size(), 1);
    }


    @Test
    public void testCsvWhenUserHasMultipleRowsWithExistingBannerIds() throws Exception {
        List<String> bannerIds = new ArrayList<String>() {
            {
                add("B00854462");
                add("B00854463");
                add("B00854464");
            }
        };
        when(userRepository.getAllBannerIds()).thenReturn(bannerIds);
        when(courseRepository.getUserDetailsOnCourse(Matchers.any(User.class), Matchers.any(String.class)))
                .thenReturn(true);
        assertEquals(csvImporterService.StudentsEnrolledForCourse("CSCI101", CsvMockFile.getFile()).get(1).size(), 0);
        assertEquals(csvImporterService.StudentsEnrolledForCourse("CSCI101", CsvMockFile.getFile()).get(2).size(), 1);
    }


    @Test
    public void testCsvWhenUserExistingBannerIds() throws Exception {
        List<String> bannerIds = new ArrayList<String>() {
            {
                add("B00854462");
                add("B00854463");
                add("B00854464");
            }
        };
        when(userRepository.getAllBannerIds()).thenReturn(bannerIds);
        when(courseRepository.getUserDetailsOnCourse(Matchers.any(User.class), Matchers.any(String.class)))
                .thenReturn(false);
        when(courseRepository.enrollStudentForCourse(Matchers.any(User.class), Matchers.any(String.class)))
                .thenReturn(true);
        assertEquals(csvImporterService.StudentsEnrolledForCourse("CSCI101", CsvMockFile.getFile()).get(1).size(), 0);
        assertEquals(csvImporterService.StudentsEnrolledForCourse("CSCI101", CsvMockFile.getFile()).get(2).size(), 1);
    }
}