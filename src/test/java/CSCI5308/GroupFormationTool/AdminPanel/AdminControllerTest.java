package CSCI5308.GroupFormationTool.AdminPanel;

import CSCI5308.GroupFormationTool.AdminPanel.AccessControl.IAdminController;
import CSCI5308.GroupFormationTool.AdminPanel.Controller.AdminController;
import CSCI5308.GroupFormationTool.AdminPanel.Model.CreateCourse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdminControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private AdminController iAdminController;

    @BeforeAll
    void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(iAdminController).build();
    }

    @Test
    void adminPage() {
        assertEquals(new ModelAndView(), iAdminController.adminPage(new CreateCourse()));
    }

    @Test
    void createCourse() throws Exception {
        mockMvc.perform(post("/createCourse")
                .param("courseId","courseName"))
                .andExpect(status().isOk());
    }
}
