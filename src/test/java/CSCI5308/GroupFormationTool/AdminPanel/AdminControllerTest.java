package CSCI5308.GroupFormationTool.AdminPanel;

import CSCI5308.GroupFormationTool.AdminPanel.AdminController;
import CSCI5308.GroupFormationTool.AdminPanel.AdminService;
import org.apache.catalina.filters.CorsFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
    }
}
