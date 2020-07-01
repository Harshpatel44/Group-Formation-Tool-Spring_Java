package CSCI5308.GroupFormationTool.Course.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Course.ICourseRepository;
import CSCI5308.GroupFormationTool.Course.ICsvImporter;
import CSCI5308.GroupFormationTool.Course.CourseRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.UserAuthentication.IUserNotification;
import CSCI5308.GroupFormationTool.UserAuthentication.IUserRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.User;
import CSCI5308.GroupFormationTool.UserAuthentication.UserPasswordPolicy;
import CSCI5308.GroupFormationTool.UserAuthentication.UserRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.UserNotification;

@SpringBootTest
public class CsvImporterServiceTest {
	private IUserRepository userRepository;
	private ICourseRepository courseRepository;
	private IPasswordEncryptor encryptor;
	private IUserNotification userNotification;
	private ICsvImporter csvImporterService;

	@BeforeEach
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		userRepository = mock(UserRepository.class);
		courseRepository = mock(CourseRepository.class);
		userNotification = mock(UserNotification.class);
		Injector.instance().setUserRepository(userRepository);
		Injector.instance().setCourseRepository(courseRepository);
		Injector.instance().setUserNotification(userNotification);
		UserPasswordPolicy.setInstance(2, 23, 1, 1, 1, "@#");
	}

	@Test
	public void testCsv() throws Exception {
		csvImporterService = Injector.instance().getCsvImporter();
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
				.thenReturn(false);
		when(courseRepository.enrollStudentForCourse(Matchers.any(User.class), Matchers.any(String.class)))
				.thenReturn(true);
		assertEquals(csvImporterService.StudentsEnrolledForCourse("CSCI101", CsvMockFile.getFile()).get(1).size(), 9);
	}

	@Test
	public void testCsvWhenUserIsNotCreated() throws Exception {
		csvImporterService = Injector.instance().getCsvImporter();
		List<String> bannerIds = new ArrayList<String>() {
			{
				add("B00854475");
				add("B00854476");
			}
		};
		when(userRepository.getAllBannerIds()).thenReturn(bannerIds);
		when(userRepository.createUser(Matchers.any(User.class))).thenReturn(false);
		assertEquals(csvImporterService.StudentsEnrolledForCourse("CSCI101", CsvMockFile.getFile()).get(2).size(), 3);
	}

	@Test
	public void testCsvWhenUserHasMultipleRows() throws Exception {
		csvImporterService = Injector.instance().getCsvImporter();
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
		assertEquals(csvImporterService.StudentsEnrolledForCourse("CSCI101", CsvMockFile.getFile()).get(1).size(), 6);
		assertEquals(csvImporterService.StudentsEnrolledForCourse("CSCI101", CsvMockFile.getFile()).get(2).size(), 3);
	}

	@Test
	public void testCsvWhenUserHasMultipleRowsWithExistingBannerIds() throws Exception {
		csvImporterService = Injector.instance().getCsvImporter();
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
		assertEquals(csvImporterService.StudentsEnrolledForCourse("CSCI101", CsvMockFile.getFile()).get(2).size(), 3);
	}

	@Test
	public void testCsvWhenUserExistingBannerIds() throws Exception {
		csvImporterService = Injector.instance().getCsvImporter();
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
		assertEquals(csvImporterService.StudentsEnrolledForCourse("CSCI101", CsvMockFile.getFile()).get(1).size(), 3);
		assertEquals(csvImporterService.StudentsEnrolledForCourse("CSCI101", CsvMockFile.getFile()).get(2).size(), 0);
	}
}
