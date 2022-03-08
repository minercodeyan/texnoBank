package com.example.danitexnobank.controlls;


import com.example.danitexnobank.Service.EmailSenderService;
import com.example.danitexnobank.models.User;
import com.example.danitexnobank.repositories.UserRepo;
import com.example.danitexnobank.repositories.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class EmailController {
    @Autowired
    private EmailSenderService service;
    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/mail/{user}")
    public String sendmail(@PathVariable User user,
                           Model model) {




        return "redirect:/user";
    }
}
