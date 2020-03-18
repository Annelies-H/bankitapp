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
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

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
        /*if (model.getAttribute("employee") != null) {
            return "redirect:/intranet/dashboard";
        } else {
            return "employee_login";
        }*/
        return "employee_login";
    }

    @GetMapping("logout")
    public String logoutHandler(@ModelAttribute Employee employee, WebRequest webRequest, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        webRequest.removeAttribute("employee", WebRequest.SCOPE_REQUEST);
        return "redirect:/intranet";
    }

    @GetMapping("/")
    public String redirectHandler(){
        return "redirect:/intranet";
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
            return "redirect:/intranet";
        }
    }

    @GetMapping("dashboard")
    public String dashboardHandler(Model model) {
        if (model.getAttribute("employee") == null) {
            return "redirect:/intranet";
        } else {
            return "employee_dashboard";
        }
    }

    //tijdelijk
    @GetMapping("create_employee")
    public String createEmployeeHandler() {
        Employee accountmanager = new Employee();
        accountmanager.setUsername("Piet");
        accountmanager.setPassword("wwpiet");
        accountmanager.setPosition(Position.ACCOUNTMANAGER);
        employeeDAO.save(accountmanager);
        return"redirect:/intranet";
    }
}