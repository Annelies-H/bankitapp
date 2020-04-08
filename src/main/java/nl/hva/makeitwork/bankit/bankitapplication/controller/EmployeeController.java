package nl.hva.makeitwork.bankit.bankitapplication.controller;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.BusinessAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.PrivateAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import nl.hva.makeitwork.bankit.bankitapplication.model.company.Industry;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.BusinessAccountDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.EmployeeDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.PrivateAccountDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Employee;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Position;
import nl.hva.makeitwork.bankit.bankitapplication.service.BankAccountService;
import nl.hva.makeitwork.bankit.bankitapplication.service.CompanyService;
import nl.hva.makeitwork.bankit.bankitapplication.service.EmployeeService;
import nl.hva.makeitwork.bankit.bankitapplication.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/intranet")
@Controller
@SessionAttributes("employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    BusinessAccountDAO businessAccountDAO;

    @Autowired
    PrivateAccountDAO privateAccountDAO;

    @Autowired
    LoginService loginService;

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    CompanyService companyService;

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

    @GetMapping("logout")
    public String logoutHandler(Model model, WebRequest webRequest, SessionStatus sessionStatus) {
        Employee employee = (Employee) model.getAttribute("employee");
        if (employee != null) {
            sessionStatus.setComplete();
            webRequest.removeAttribute("employee", WebRequest.SCOPE_REQUEST);
        }
        return "redirect:/intranet";
    }

    @GetMapping("/")
    public String redirectHandler() {
        return "redirect:/intranet";
    }

    @PostMapping("login")
    public String loginHandler(@RequestParam(name = "user_name") String username,
            @RequestParam(name = "user_password") String password, Model model) {
        Employee employee = employeeService.findEmployee(username);
        if (employee != null && loginService.passwordCheck(password, employee)) {
            model.addAttribute("employee", employee);
            return "redirect:/intranet/dashboard";
        } else {
            return "redirect:/intranet";
        }
    }

    @GetMapping("dashboard")
    public String dashboardHandler(Model model) {

        Employee employee = (Employee) model.getAttribute("employee");

        if (employee == null) {
            return "redirect:/intranet";
        }

        List<Company> topCompanies = companyService.getTop10CompaniesBalance();
        Iterable<BusinessAccount> businessAccounts = businessAccountDAO.findAll();
        List<PrivateAccount> privateAccounts = bankAccountService.getTop10Private();

        if (employee.getPosition().equals(Position.HEAD_BUSINESS)) {
            model.addAttribute("topCompanies", topCompanies);
            model.addAttribute("industrybalances", employeeService.getAverageBalanceForAllIndustries());
        } else if (employee.getPosition().equals(Position.HEAD_PRIVATE)) {
            model.addAttribute("privateAccounts", privateAccounts);
        } else {
            //model.addAttribute("businessAccounts", businessAccounts);
            model.addAttribute("privateAccounts", privateAccounts);
        }
        return "employee_dashboard";
    }

}