package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.Course.Course;
import CSCI5308.GroupFormationTool.Course.ICourse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class courseTest {

    @Test
    public void setCourseIdTest(){
        ICourse course=new Course();
        course.setCourseId("CSCI9");
        assertEquals("CSCI9", course.getCourseId());
    }

    @Test
    public void setCourseNameTest(){
        ICourse course=new Course();
        course.setCourseName("Cloud");
        assertEquals("Cloud", course.getCourseName());
    }

    @Test
    public void setRoleTest(){
        ICourse course=new Course();
        course.setRole("Guest");
        assertEquals("Guest", course.getRole());
    }

}
