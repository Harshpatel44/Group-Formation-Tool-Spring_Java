package CSCI5308.GroupFormationTool.AdminPanel.Controller;

import CSCI5308.GroupFormationTool.AdminPanel.AccessControl.IAdminController;
import CSCI5308.GroupFormationTool.AdminPanel.AdminInjector;
import CSCI5308.GroupFormationTool.AdminPanel.Model.CreateCourse;
import CSCI5308.GroupFormationTool.AdminPanel.Model.DeleteCourse;
import CSCI5308.GroupFormationTool.AdminPanel.Service.AdminService;
import org.apache.catalina.filters.CorsFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import java.util.Collection;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//  Author: Harsh Patel
@SpringBootTest
public class AdminControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(adminController)
                .addFilters(new CorsFilter())
                .build();
    }

    @Test
    void adminPage() throws Exception {
        mockMvc.perform(
                get("/admin")
                        .header("Origin","*")
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
                ;
    }


    @Test
    void createCourse() throws Exception {
        
    }


    @Test
    void deleteCourse() throws Exception{

    }

    @Test
    void assignInstructor() throws Exception{

    }
}
