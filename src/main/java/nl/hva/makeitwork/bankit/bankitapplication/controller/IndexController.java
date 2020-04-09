package nl.hva.makeitwork.bankit.bankitapplication.controller;

import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("login")
    public String showlogonHandler() {
        return "login";
    }

    @GetMapping("about_us")
    public String aboutusHandler() {
        return "about_us";
    }
}
