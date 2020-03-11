package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.repository.EmployeeDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDAO employeeDAO;

    public EmployeeService() {
        super();
    }

    public Employee findEmployee(String username) {
        Optional<Employee> employeeOptional = employeeDAO.findEmployeeByUsername(username);
        return employeeOptional.orElse(null);
    }

}