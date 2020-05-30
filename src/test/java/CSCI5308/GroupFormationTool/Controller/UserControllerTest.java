package CSCI5308.GroupFormationTool.Controller;

import CSCI5308.GroupFormationTool.AccessControl.IUserController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;
    private MockMvc mockMvc;

    @BeforeAll
    void setUp(){
        UserController controller = new UserController(); // 1
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build(); //

    }

    @Test
    void createUser() {
        assertEquals(false, userController.createUser());
    }


    @Test
    void adminPage() {
        assertEquals(new ModelAndView(), userController.adminPage());
    }

    @Test
    void createCourse() throws Exception {
        mockMvc.perform(post("/createCourse")
                .param("courseId","courseName"))
                .andExpect(status().isOk());
    }
}