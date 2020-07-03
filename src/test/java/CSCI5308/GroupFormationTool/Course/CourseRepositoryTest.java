package CSCI5308.GroupFormationTool.Course;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import CSCI5308.GroupFormationTool.UserAuthentication.IUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.UserAuthentication.UserMockDB;


import java.sql.SQLException;

@SpringBootTest
public class CourseRepositoryTest {

	private ICourseRepository courseRepository;

	@BeforeEach
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		courseRepository = mock(CourseRepository.class);
		Injector.instance().setCourseRepository(courseRepository);
	}

	@Test
	public void getUserDetailsOnCourse() throws Exception {
		IUser user = UserMockDB.setDefault();
		when(courseRepository.getUserDetailsOnCourse(user, "CSCI10")).thenReturn(false);
		assertEquals(false, courseRepository.getUserDetailsOnCourse(user, "CSCI10"));
	}

	@Test
	public void getEnrollCourse() {
		IUser iUser = UserMockDB.setDefault();
		when(courseRepository.enrollStudentForCourse(iUser, "CSCI10")).thenReturn(true);
		assertEquals(true, courseRepository.enrollStudentForCourse(iUser, "CSCI10"));
	}

	@Test
	public void getEnrollCourseFails() {
		IUser iUser = UserMockDB.setDefault();
		when(courseRepository.enrollStudentForCourse(iUser, "CSCI10")).thenReturn(false);
		assertEquals(false, courseRepository.enrollStudentForCourse(iUser, "CSCI10"));
	}

	@Test
	void createCourseRepo() throws SQLException {
		ICreateCourse createCourse = new CreateCourse();
		createCourse.setCourseName("testname");
		createCourse.setCourseId("testid");
		assertTrue(createCourse.getCourseName().length()<200);
		assertTrue(createCourse.getCourseId().length()<10);
		assertFalse(createCourse.getCourseName().isEmpty());
		assertFalse(createCourse.getCourseId().isEmpty());
		assertTrue(createCourse.getCourseId() instanceof String);
		assertTrue(createCourse.getCourseName() instanceof String);
	}

	@Test
	void deleteCourseRepo() throws Exception {
		IDeleteCourse deleteCourse = new DeleteCourse();
		deleteCourse.setSelectedCourseId("testname");
		assertTrue(deleteCourse.getSelectedCourseId().length()<=200);
		assertFalse(deleteCourse.getSelectedCourseId().isEmpty());
		assertTrue(deleteCourse.getSelectedCourseId() instanceof String);
	}
}
