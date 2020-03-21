package nl.hva.makeitwork.bankit.bankitapplication.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@SessionAttributes("customer")
@RequestMapping (value = "/POS")
public class POSController {

    @GetMapping("request_POS")
    public String requestPOSHandler (Model model ){

        return "under_construction";
    }

}
