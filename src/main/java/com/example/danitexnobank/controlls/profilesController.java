package com.example.danitexnobank.controlls;

import com.example.danitexnobank.Service.UserService;
import com.example.danitexnobank.models.Deposit;
import com.example.danitexnobank.repositories.DepositRepository;
import com.example.danitexnobank.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class profilesController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private DepositRepository depositRepository;

    @GetMapping
    public String profile(Model model) {
        return "profile";
    }


    @GetMapping("/emp")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE')")
    public String employeeProfile(Model model) {
        Iterable<Deposit> credits = depositRepository.findAll();
        model.addAttribute("credit",credits);
        return "emProfile";
    }


}
