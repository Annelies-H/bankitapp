package nl.hva.makeitwork.bankit.bankitapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("index")
    public String errorGoBackHandler() {
        return "redirect:/";
    }
}