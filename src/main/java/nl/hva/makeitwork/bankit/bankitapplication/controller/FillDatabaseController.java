

package nl.hva.makeitwork.bankit.bankitapplication.controller;


import nl.hva.makeitwork.bankit.bankitapplication.model.repository.EmployeeDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Employee;
import nl.hva.makeitwork.bankit.bankitapplication.service.FillDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class FillDatabaseController {

    @Autowired
    private FillDatabaseService fillDatabaseService;

    @Autowired
    private EmployeeDAO employeeDAO;

    public FillDatabaseController() {
        super();
    }

    @GetMapping("fill_database")
    public String filldatabaseHandler() {
        if (fillDatabaseService.databaseIsEmpty() ) {
            fillDatabaseService.fillDatabase();
            return "database_fill_ok";
        } else {
            return "database_fill_error";
        }
    }


}
