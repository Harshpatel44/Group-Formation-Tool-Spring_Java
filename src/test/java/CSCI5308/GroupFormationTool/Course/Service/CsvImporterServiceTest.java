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
import CSCI5308.GroupFormationTool.Course.AccessControl.ICourseRepository;
import CSCI5308.GroupFormationTool.Course.AccessControl.ICsvImporter;
import CSCI5308.GroupFormationTool.Course.Repository.CourseRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserNotification;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.UserPasswordPolicy;
import CSCI5308.GroupFormationTool.UserAuthentication.Repository.UserRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.Service.UserNotification;

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
	public void testCsv() throws AddressException, MessagingException {
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
	public void testCsvWhenUserIsNotCreated() throws AddressException, MessagingException {
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
	public void testCsvWhenUserHasMultipleRows() throws AddressException, MessagingException {
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
	public void testCsvWhenUserHasMultipleRowsWithExistingBannerIds() throws AddressException, MessagingException {
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
	public void testCsvWhenUserExistingBannerIds() throws AddressException, MessagingException {
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
