package CSCI5308.GroupFormationTool.Course.Service;

import CSCI5308.GroupFormationTool.Course.Model.UserId;
import CSCI5308.GroupFormationTool.Course.Repository.CourseRepository;
import CSCI5308.GroupFormationTool.Course.Repository.CourseRepositoryMock;
import CSCI5308.GroupFormationTool.Course.Repository.HomeRepository;
import CSCI5308.GroupFormationTool.Course.Repository.HomeRepositoryMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
//Dhruvesh Patel
@SpringBootTest
public class CourseServiceTest {
   public CourseService courseService;
   public CourseRepository courseRepository;
    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        courseRepository = mock(CourseRepository.class);
        courseService = new CourseService(courseRepository);
    }

    @Test
    public void addTaTestIfAlready() {
        String taId, courseId,result;
        taId = "B00123456";
        courseId = "CSCI1";
        result="Already user is TA of courseId:"+courseId+".";
        when(courseRepository.addTa(taId,courseId)).thenReturn(result);
        String returned = courseService.addTa(taId,courseId);
        assertEquals(result,returned);
    }
    @Test
    public void addTaTest() {
        String taId, courseId,result;
        taId = "B00123456";
        courseId = "CSCI2";
        result="user with Id:"+taId+" is add as a TA for courseId"+courseId+".";
        when(courseRepository.addTa(taId,courseId)).thenReturn(result);
        String returned = courseService.addTa(taId,courseId);
        assertEquals(result,returned);
    }
    @Test
    public void addTaTestNotUser() {
        String taId, courseId,result;
        taId = "B00103456";
        courseId = "CSCI1";
        result = "No user exist with Id:"+taId+" present in system.";
        when(courseRepository.addTa(taId,courseId)).thenReturn(result);
        String retruned = courseService.addTa(taId,courseId);
        assertEquals(result,retruned);
    }

    @Test
    public void CheckRoleTestTAorInstructor(){
        String userType;
        userType="TA";
        assertTrue(courseService.checkRole(userType));
    }
    @Test
    public void CheckRoleTestStudentOrGuest(){
        String userType;
        userType="Guest";
        assertFalse(courseService.checkRole(userType));
    }
    @Test
    public void checkUserTypeTestInstrutor(){
        String userType;
        userType="instructor";
        assertTrue(courseService.checkUserType(userType));
    }
}
