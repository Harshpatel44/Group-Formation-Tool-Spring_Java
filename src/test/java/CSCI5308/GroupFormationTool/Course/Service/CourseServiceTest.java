package CSCI5308.GroupFormationTool.Course.Service;

import CSCI5308.GroupFormationTool.Course.Model.UserId;
import CSCI5308.GroupFormationTool.Course.Repository.CourseRepositoryMock;
import CSCI5308.GroupFormationTool.Course.Repository.HomeRepositoryMock;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
//Dhruvesh Patel
@SpringBootTest
public class CourseServiceTest {

    @Mock
    private CourseRepositoryMock courseRepositoryMock;

    @Test
    public void addTaTestIfAlready() {
        courseRepositoryMock = new CourseRepositoryMock();
        String taId, courseId,result = null,expected;
        taId = "B00123456";
        courseId = "CSCI1";
        result = courseRepositoryMock.addTaTest(taId,courseId);
        assertEquals("Already user is TA of courseId:"+courseId+".",result);
    }
    @Test
    public void addTaTest() {
        courseRepositoryMock = new CourseRepositoryMock();
        String taId, courseId,result = null,expected;
        taId = "B00123456";
        courseId = "CSCI2";
        result = courseRepositoryMock.addTaTest(taId,courseId);
        assertEquals("user with Id:"+taId+" is add as a TA for courseId"+courseId+".",result);
    }
    @Test
    public void addTaTestNotUser() {
        courseRepositoryMock = new CourseRepositoryMock();
        String taId, courseId,result = null,expected;
        taId = "B00103456";
        courseId = "CSCI1";
        result = courseRepositoryMock.addTaTest(taId,courseId);
        assertEquals("No user exist with Id:"+taId+" present in system.",result);
    }
}
