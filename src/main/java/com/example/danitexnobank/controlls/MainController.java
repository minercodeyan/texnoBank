package com.example.danitexnobank.controlls;
import com.example.danitexnobank.Service.EmailSenderService;
import com.example.danitexnobank.repositories.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;


@Controller
public class MainController {
    @Autowired
    private EmailSenderService service;
    @Autowired
    private DepositRepository depositRepository;

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