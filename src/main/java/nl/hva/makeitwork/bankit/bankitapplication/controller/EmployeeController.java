package nl.hva.makeitwork.bankit.bankitapplication.controller;

import nl.hva.makeitwork.bankit.bankitapplication.model.repository.EmployeeDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Employee;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Position;
import nl.hva.makeitwork.bankit.bankitapplication.service.EmployeeService;
import nl.hva.makeitwork.bankit.bankitapplication.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping (value = "/intranet")
@Controller
@SessionAttributes("employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    LoginService loginService;

    public EmployeeController() {
        super();
    }

    @GetMapping("")
    public String loginPageHandler(Model model) {
        if (model.getAttribute("employee") != null) {
            return "redirect:/intranet/dashboard";
        } else {
            return "employee_login";
        }
    }

    @PostMapping("login")
    public String loginHandler(
            @RequestParam(name = "user_name") String username,
            @RequestParam(name = "user_password") String password,
            Model model) {
        Employee employee = employeeService.findEmployee(username);
        if ( employee != null && loginService.passwordCheck(password, employee) ) {
            model.addAttribute("employee", employee);
            return "redirect:/intranet/dashboard";
        } else {
            return "redirect:/intranet/";
        }
    }

    @GetMapping("dashboard")
    public String dashboardHandler(Model model) {
        if (model.getAttribute("employee") == null) {
            return "redirect:/intranet/";
        } else {
            return "employee_dashboard";
        }
    }

    //tijdelijk
    @GetMapping("create_employee")
    public String createEmployeeHandler() {
        Employee accountmanager = new Employee();
        accountmanager.setUsername("Piet");
        accountmanager.setSalt();
        accountmanager.setPassword(loginService.hashPassword("wwpiet", accountmanager.getSalt()));
        accountmanager.setPosition(Position.ACCOUNTMANAGER);
        employeeDAO.save(accountmanager);
        return"redirect:/intranet/";
    }
}