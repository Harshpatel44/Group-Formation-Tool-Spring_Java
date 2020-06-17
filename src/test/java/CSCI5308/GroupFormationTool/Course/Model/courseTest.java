package CSCI5308.GroupFormationTool.Course.Model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class courseTest {

    @Test
    public void setCourseIdTest(){
        Course course=new Course();
        course.setCourseId("CSCI9");
        assertTrue(course.getCourseId()=="CSCI9");
    }

    @Test
    public void setCourseNameTest(){
        Course course=new Course();
        course.setCourseName("Cloud");
        assertTrue(course.getCourseName()=="Cloud");
    }

    @Test
    public void setRoleTest(){
        Course course=new Course();
        course.setRole("Guest");
        assertTrue(course.getRole()=="Guest");
    }

}
