package CSCI5308.GroupFormationTool.Course.Repository;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import CSCI5308.GroupFormationTool.Course.Model.CreateCourse;
import CSCI5308.GroupFormationTool.Course.Model.DeleteCourse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Course.AccessControl.ICourseRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControll.UserMockDB;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;



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
	public void getUserDetailsOnCourse() {
		User user = UserMockDB.setDefault();
		when(courseRepository.getUserDetailsOnCourse(user, "CSCI10")).thenReturn(false);
		assertEquals(false, courseRepository.getUserDetailsOnCourse(user, "CSCI10"));
	}

	@Test
	public void getEnrollCourse() {
		User user = UserMockDB.setDefault();
		when(courseRepository.enrollStudentForCourse(user, "CSCI10")).thenReturn(true);
		assertEquals(true, courseRepository.enrollStudentForCourse(user, "CSCI10"));
	}

	@Test
	public void getEnrollCourseFails() {
		User user = UserMockDB.setDefault();
		when(courseRepository.enrollStudentForCourse(user, "CSCI10")).thenReturn(false);
		assertEquals(false, courseRepository.enrollStudentForCourse(user, "CSCI10"));
	}

	@Test
	void createCourseRepo() throws SQLException {
		CreateCourse createCourse = new CreateCourse();
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
		DeleteCourse deleteCourse = new DeleteCourse("test");
		deleteCourse.setSelectedCourseId("testname");
		assertTrue(deleteCourse.getSelectedCourseId().length()<=200);
		assertFalse(deleteCourse.getSelectedCourseId().isEmpty());
		assertTrue(deleteCourse.getSelectedCourseId() instanceof String);
	}
}
