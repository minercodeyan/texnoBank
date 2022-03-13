package com.example.danitexnobank.controlls;

import com.example.danitexnobank.Service.EmailSenderService;
import com.example.danitexnobank.repositories.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная");
        return "home";
    }
    @GetMapping("/faq")
    public String Faq(Model model) {
        model.addAttribute("title", "Faq");
        return "faq";
    }

}