

package nl.hva.makeitwork.bankit.bankitapplication.controller;


import nl.hva.makeitwork.bankit.bankitapplication.service.FillDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestDatabaseController {

    @Autowired
    private FillDatabaseService fillDatabaseService;

    public TestDatabaseController() {
        super();
    }

    @GetMapping("fill_database")
    public String filldatabaseHandler() {
        fillDatabaseService.fillDatabase();
        return "menu";
    }


}
