package nl.hva.makeitwork.bankit.bankitapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@Controller
@SessionAttributes("customer")
public class ProductOverviewController {

 public ProductOverviewController(){super();}


    @GetMapping("selected_bankaccount")
    public String bankaccountOverviewHandler (){
    return "under_construction";
    }

    @GetMapping("open_account")
    public String openAccountHandler (){
        return "under_construction";
    }

    @GetMapping("connect_account")
    public String connectAccountHandler (){
        return "under_construction";
    }
}
