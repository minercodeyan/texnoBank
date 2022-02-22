package com.example.danitexnobank.controlls;

import com.example.danitexnobank.Service.EmailSenderService;
import com.example.danitexnobank.Service.TimerService;
import com.example.danitexnobank.models.User;
import com.example.danitexnobank.repositories.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VidController {

    @Autowired
    private EmailSenderService service;
    @Autowired
    private CreditRepository videoRepository;
    @Autowired
    private TimerService timerService;





    @GetMapping("/all/{id}")
    public String details(@AuthenticationPrincipal User user, @PathVariable(value = "id") long id, Model model) {

        return "creditDetails";
    }


}