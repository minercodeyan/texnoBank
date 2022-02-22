package com.example.danitexnobank.controlls;

import com.example.danitexnobank.Service.UserService;
import com.example.danitexnobank.models.Credit;
import com.example.danitexnobank.models.Role;
import com.example.danitexnobank.models.User;
import com.example.danitexnobank.repositories.CreditRepository;
import com.example.danitexnobank.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/profile")
public class profilesController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private CreditRepository creditRepository;

    @GetMapping
    public String profile(Model model) {
        return "profile";
    }


    @GetMapping("/emp")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE')")
    public String employeeProfile(Model model) {
        Iterable<Credit> credits = creditRepository.findAll();
        model.addAttribute("credit",credits);
        return "emProfile";
    }


}
