package nl.hva.makeitwork.bankit.bankitapplication.controller;

import nl.hva.makeitwork.bankit.bankitapplication.model.repository.BusinessAccountDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.EmployeeDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.PrivateAccountDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Employee;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Position;
import nl.hva.makeitwork.bankit.bankitapplication.service.EmployeeService;
import nl.hva.makeitwork.bankit.bankitapplication.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;

    @MockBean
    EmployeeDAO employeeDAO;

    @MockBean
    BusinessAccountDAO businessAccountDAO;

    @MockBean
    PrivateAccountDAO privateAccountDAO;

    @MockBean
    LoginService loginService;

    @MockBean
    Model model;

    @Test
    public void loginHandlerAlreadyLoggedIn() throws Exception{
        Employee employee = new Employee();
        employee.setUsername("Piet");
        employee.setPosition(Position.HEAD_BUSINESS);

        when(employeeService.findEmployee(employee.getUsername())).thenReturn(employee);

        mockMvc.perform(get("/intranet/dashboard")
                .contentType("application/json")
                .sessionAttr("employee", employee))
                .andExpect(status().isOk())
                .andExpect(view().name("employee_dashboard"));
    }

    @Test
    public void loginHandlerNotLoggedIn() throws Exception {
        mockMvc.perform(get("/intranet/dashboard")
                .contentType("application/json"))
                .andExpect(model().attributeDoesNotExist("employee"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/intranet"));

    }

    @Test
    public void dashboardHandlerTestHeadBusiness() throws Exception {
        Employee employee = new Employee();
        employee.setUsername("Piet");
        employee.setPosition(Position.HEAD_BUSINESS);

        when(employeeService.findEmployee(employee.getUsername())).thenReturn(employee);

        mockMvc.perform(get("/intranet/dashboard")
                .contentType("application/json")
                .sessionAttr("employee", employee))
                .andExpect(status().isOk())
                .andExpect(view().name("employee_dashboard"));
    }

    @Test
    public void dashboardHandlerTestHeadPrivate() throws Exception {
        Employee employee = new Employee();
        employee.setUsername("Kees");
        employee.setPosition(Position.HEAD_PRIVATE);

        when(employeeService.findEmployee(employee.getUsername())).thenReturn(employee);

        mockMvc.perform(get("/intranet/dashboard")
                .contentType("application/json")
                .sessionAttr("employee", employee))
                .andExpect(status().isOk())
                .andExpect(view().name("employee_dashboard"));
    }

    @Test
    public void dashboardHandlerTestAccountManager() throws Exception {
        Employee employee = new Employee();
        employee.setUsername("Jan");
        employee.setPosition(Position.ACCOUNTMANAGER);

        when(employeeService.findEmployee(employee.getUsername())).thenReturn(employee);

        mockMvc.perform(get("/intranet/dashboard")
                .contentType("application/json")
                .sessionAttr("employee", employee))
                .andExpect(status().isOk())
                .andExpect(view().name("employee_dashboard"));
    }

}
