package CSCI5308.GroupFormationTool.Course.Model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TaTest {
    @Test
    public void setTaIdTest(){
        TA ta=new TA();
        ta.setTaId("B00123456");
        assertTrue(ta.getTaId()=="B00123456");
    }
}
