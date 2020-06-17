package CSCI5308.GroupFormationTool.Course.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Course.AccessControl.ICourseRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControll.UserMockDB;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.UserPasswordPolicy;
import CSCI5308.GroupFormationTool.UserAuthentication.Repository.UserRepository;

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

}
