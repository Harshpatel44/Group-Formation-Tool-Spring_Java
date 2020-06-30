package CSCI5308.GroupFormationTool.Course.Model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class courseTest {

    @Test
    public void setCourseIdTest(){
        Course course=new Course();
        course.setCourseId("CSCI9");
        assertEquals("CSCI9", course.getCourseId());
    }

    @Test
    public void setCourseNameTest(){
        Course course=new Course();
        course.setCourseName("Cloud");
        assertEquals("Cloud", course.getCourseName());
    }

    @Test
    public void setRoleTest(){
        Course course=new Course();
        course.setRole("Guest");
        assertEquals("Guest", course.getRole());
    }

}
