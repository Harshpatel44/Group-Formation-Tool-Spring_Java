package CSCI5308.GroupFormationTool.Course;


import CSCI5308.GroupFormationTool.UserManager.IInstructor;
import CSCI5308.GroupFormationTool.UserManager.UserManagerAbstractFactory;
import CSCI5308.GroupFormationTool.UserManager.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CourseServiceTest {
    public CourseService courseService;
    public CourseRepository courseRepository;
    public UserService userService;

    @BeforeEach
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        courseRepository = mock(CourseRepository.class);
        userService = mock(UserService.class);
        courseService = new CourseService(courseRepository, userService);

    }

    @Test
    public void addTAForCourseTestIfAlready() throws Exception {
        String taId, courseId, result;
        taId = "B00123456";
        courseId = "CSCI1";
        result = "Already user is TA of courseId:" + courseId + ".";
        when(userService.checkIfUserExists(taId)).thenReturn(true);
        when(courseRepository.addTaForCourse(taId, courseId)).thenReturn(result);
        String returned = courseService.addTAForCourse(taId, courseId);
        assertEquals(result, returned);
    }

    @Test
    public void addTAForCourseTest() throws Exception {
        String taId, courseId, result;
        taId = "B00123456";
        courseId = "CSCI2";
        result = "user with Id:" + taId + " is add as a TA for courseId" + courseId + ".";
        when(userService.checkIfUserExists(taId)).thenReturn(true);
        when(courseRepository.addTaForCourse(taId, courseId)).thenReturn(result);
        String returned = courseService.addTAForCourse(taId, courseId);
        assertEquals(result, returned);
    }

    @Test
    public void addTAForCourseTestNotUser() throws Exception {
        String taId, courseId, result;
        taId = "B00103456";
        courseId = "CSCI1";
        result = "No user exist with Id:" + taId + " present in system.";
        when(courseRepository.addTaForCourse(taId, courseId)).thenReturn(result);
        String retruned = courseService.addTAForCourse(taId, courseId);
        assertEquals(result, retruned);
    }

    @Test
    public void CheckRoleTestTAorInstructor() {
        String userRole;
        userRole = "TA";
        assertTrue(courseService.roleAllowInstructorAndTA(userRole));
    }

    @Test
    public void CheckRoleTestStudentOrGuest() {
        String userRole;
        userRole = "Guest";
        assertFalse(courseService.roleAllowInstructorAndTA(userRole));
    }

    @Test
    public void checkUserRoleTestInstructor() {
        String userRole;
        userRole = "instructor";
        assertTrue(courseService.roleAllowInstructor(userRole));
    }

	@Test
	void assignInstructorForCourseTest() throws Exception {
		IInstructor assignInstructor = UserManagerAbstractFactory.instance().getInstructor();
		when(courseRepository.assignInstructorForCourse(assignInstructor)).thenReturn(true);
		assertTrue(courseService.assignInstructorForCourse(assignInstructor));
		assertEquals("Instructor assigned",assignInstructor.getInstructorAssignMessage());
		when(courseRepository.assignInstructorForCourse(assignInstructor)).thenReturn(false);
		assertFalse(courseService.assignInstructorForCourse(assignInstructor));
		assertEquals("User does not exist or already an instructor",assignInstructor.getInstructorAssignMessage());
	}

	@Test
	void createCourseService1() throws Exception {
		ICreateCourse createCourse = CourseAbstractFactory.instance().getCreateCourse();
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
		assertTrue(courseService.createCourse(createCourse));
		assertEquals("Course created", createCourse.getCourseCreateMessage());
	}

    @Test
    void createCourseService2() throws Exception {
        ICreateCourse createCourse = new CreateCourse();
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
        assertFalse(courseService.createCourse(createCourse));
        assertEquals("Course id exists", createCourse.getCourseCreateMessage());
    }

    @Test
    void createCourseService3() throws Exception {
        ICreateCourse createCourse = new CreateCourse();
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
        assertFalse(courseService.createCourse(createCourse));
        assertEquals("Course name exists", createCourse.getCourseCreateMessage());
    }

	@Test
	void deleteCourseService() throws Exception {
		IDeleteCourse deleteCourse = new DeleteCourse();
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
		assertTrue(courseService.deleteCourse(deleteCourse));
		assertEquals("Course deleted", deleteCourse.getCourseDeleteMessage());
		when(courseRepository.deleteCourseRepo(deleteCourse)).thenReturn(false);
		assertFalse(courseService.deleteCourse(deleteCourse));
		assertEquals("Course does not exist", deleteCourse.getCourseDeleteMessage());
	}

	@Test
	void coursesWithIdForDropdown() throws Exception {
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
		assertEquals(allCoursesList, courseService.coursesWithIdForDropdown());
	}


}
