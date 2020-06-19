package CSCI5308.GroupFormationTool.Course.Service;

import CSCI5308.GroupFormationTool.Course.Model.CreateCourse;
import CSCI5308.GroupFormationTool.Course.Model.DeleteCourse;

import CSCI5308.GroupFormationTool.Course.Repository.CourseRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//Dhruvesh Patel
@SpringBootTest
public class CourseServiceTest {
	public CourseService courseService;
	public CourseRepository courseRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		courseRepository = mock(CourseRepository.class);
		courseService = new CourseService(courseRepository);
	}

	@Test
	public void addTaTestIfAlready() {
		String taId, courseId, result;
		taId = "B00123456";
		courseId = "CSCI1";
		result = "Already user is TA of courseId:" + courseId + ".";
		when(courseRepository.addTa(taId, courseId)).thenReturn(result);
		String returned = courseService.addTa(taId, courseId);
		assertEquals(result, returned);
	}

	@Test
	public void addTaTest() {
		String taId, courseId, result;
		taId = "B00123456";
		courseId = "CSCI2";
		result = "user with Id:" + taId + " is add as a TA for courseId" + courseId + ".";
		when(courseRepository.addTa(taId, courseId)).thenReturn(result);
		String returned = courseService.addTa(taId, courseId);
		assertEquals(result, returned);
	}

	@Test
	public void addTaTestNotUser() {
		String taId, courseId, result;
		taId = "B00103456";
		courseId = "CSCI1";
		result = "No user exist with Id:" + taId + " present in system.";
		when(courseRepository.addTa(taId, courseId)).thenReturn(result);
		String retruned = courseService.addTa(taId, courseId);
		assertEquals(result, retruned);
	}

	@Test
	public void CheckRoleTestTAorInstructor() {
		String userType;
		userType = "TA";
		assertTrue(courseService.checkRole(userType));
	}

	@Test
	public void CheckRoleTestStudentOrGuest() {
		String userType;
		userType = "Guest";
		assertFalse(courseService.checkRole(userType));
	}

	@Test
	public void checkUserTypeTestInstrutor() {
		String userType;
		userType = "instructor";
		assertTrue(courseService.checkUserType(userType));

	}

	@Test
	void createCourseService1() throws Exception {

		CreateCourse createCourse = new CreateCourse();
		createCourse.setCourseName("testname");
		createCourse.setCourseId("testid");

		ArrayList<ArrayList<String>> allCoursesArray = new ArrayList<>();
		ArrayList<String> courseName = new ArrayList<>();
		ArrayList<String> courseId = new ArrayList<>();
		courseId.add("couseid1");
		courseName.add("course1");
		allCoursesArray.add(courseId);
		allCoursesArray.add(courseName);

		when(courseRepository.createCourseRepo(createCourse)).thenReturn(true);
		when(courseRepository.getAllCourses()).thenReturn(allCoursesArray);
		assertTrue(courseService.CreateCourseService(createCourse));

		assertEquals("Course created", createCourse.getCourseCreateMessage());
	}

	@Test
	void createCourseService2() throws Exception {
		CreateCourse createCourse = new CreateCourse();
		createCourse.setCourseName("testname2");
		createCourse.setCourseId("testid");

		ArrayList<String> courseName = new ArrayList<>();
		ArrayList<String> courseId = new ArrayList<>();
		ArrayList<ArrayList<String>> allCoursesArray = new ArrayList<>();
		courseId.add("testid");
		courseName.add("testname");
		allCoursesArray.add(courseId);
		allCoursesArray.add(courseName);
		when(courseRepository.createCourseRepo(createCourse)).thenReturn(false);
		when(courseRepository.getAllCourses()).thenReturn(allCoursesArray);
		assertFalse(courseService.CreateCourseService(createCourse));
		assertEquals("Course id exists", createCourse.getCourseCreateMessage());
	}

	@Test
	void createCourseService3() throws Exception {
		CreateCourse createCourse = new CreateCourse();
		createCourse.setCourseId("testid2");
		createCourse.setCourseName("testname");

		ArrayList<String> courseName = new ArrayList<>();
		ArrayList<String> courseId = new ArrayList<>();
		ArrayList<ArrayList<String>> allCoursesArray = new ArrayList<>();
		courseId.add("testid");
		courseName.add("testname");
		allCoursesArray.add(courseId);
		allCoursesArray.add(courseName);
		when(courseRepository.getAllCourses()).thenReturn(allCoursesArray);
		when(courseRepository.createCourseRepo(createCourse)).thenReturn(true);
		assertEquals(courseName, courseRepository.getAllCourses().get(1));
		assertFalse(courseService.CreateCourseService(createCourse));
		assertEquals("Course name exists", createCourse.getCourseCreateMessage());
	}

	@Test
	void deleteCourseService() throws Exception {
		DeleteCourse deleteCourse = new DeleteCourse("test");
		deleteCourse.setSelectedCourseId("testid");

		Dictionary allCoursesList = new Hashtable();
		allCoursesList.put("testid testname", "testid");
		deleteCourse.setAllCoursesList(allCoursesList);

		ArrayList<String> courseName = new ArrayList<>();
		ArrayList<String> courseId = new ArrayList<>();
		ArrayList<ArrayList<String>> allCoursesArray = new ArrayList<>();
		courseId.add("testid");
		courseName.add("testname");
		allCoursesArray.add(courseId);
		allCoursesArray.add(courseName);
		when(courseRepository.getAllCourses()).thenReturn(allCoursesArray);

		when(courseRepository.deleteCourseRepo(deleteCourse)).thenReturn(true);
		assertTrue(courseService.DeleteCourseService(deleteCourse));
		assertEquals("Course deleted", deleteCourse.getCourseDeleteMessage());

		when(courseRepository.deleteCourseRepo(deleteCourse)).thenReturn(false);
		assertFalse(courseService.DeleteCourseService(deleteCourse));
		assertEquals("Course does not exist", deleteCourse.getCourseDeleteMessage());
	}

	@Test
	void coursesWithIdForDropdown() throws SQLException {
		ArrayList<ArrayList<String>> tempCourse;
		ArrayList<String> allCourseIds;
		ArrayList<String> allCourseNames;
		Dictionary allCoursesList = new Hashtable();

		ArrayList<ArrayList<String>> mainList = new ArrayList<>();
		ArrayList<String> courseId = new ArrayList<>();
		ArrayList<String> courseName = new ArrayList<>();
		courseId.add("123");
		courseName.add("course123");
		mainList.add(courseId);
		mainList.add(courseName);
		when(courseRepository.getAllCourses()).thenReturn(mainList);

		for (int i = 0; i < mainList.get(0).size(); i++) {
			allCoursesList.put(mainList.get(0).get(i) + " " + mainList.get(1).get(i), mainList.get(0).get(i));
		}
		assertEquals(allCoursesList, courseService.CoursesWithIdForDropdown());

	}
}
