package CSCI5308.GroupFormationTool.AdminPanel.Controller;

import CSCI5308.GroupFormationTool.AdminPanel.AccessControl.IAdminController;
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
    private IAdminController iAdminController;

    @BeforeAll
    void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(iAdminController).build();
    }

    @Test
    void adminPage() {
        CreateCourse createCourse = new CreateCourse();
        assertEquals(new ModelAndView(), iAdminController.adminPage(createCourse));
    }

    @Test
    void createCourse() throws Exception {
        CreateCourse createCourse = new CreateCourse();
        mockMvc.perform(post("/createCourse")
                .sessionAttr("createCourse",createCourse))
                .andExpect(status().isOk());
    }
}
