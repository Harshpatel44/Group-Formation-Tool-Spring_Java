package CSCI5308.GroupFormationTool.AdminPanel.Controller;

import CSCI5308.GroupFormationTool.AdminPanel.AccessControl.IAdminController;
import CSCI5308.GroupFormationTool.AdminPanel.Model.CreateCourse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
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

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class AdminControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private IAdminController iAdminController;

    @BeforeAll
    void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(iAdminController).build();
    }

//    @Test
//    void adminPage() {
//    	CreateCourse course =new CreateCourse();
//    	ModelAndView mv = new ModelAndView();
//        mv.addObject(course);
//        mv.setViewName("admin");
//        assertEquals(mv,iAdminController.adminPage(course));
//    }

    @Test
    void createCourse() throws Exception {
        CreateCourse createCourse = new CreateCourse();
        mockMvc.perform(post("/createCourse")
                .sessionAttr("createCourse",createCourse))
                .andExpect(status().isOk());
    }
}
