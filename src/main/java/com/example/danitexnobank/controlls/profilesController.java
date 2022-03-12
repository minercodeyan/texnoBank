package com.example.danitexnobank.controlls;

import com.example.danitexnobank.Service.UserService;
import com.example.danitexnobank.models.Deposit;
import com.example.danitexnobank.models.User;
import com.example.danitexnobank.repositories.DepositRepository;
import com.example.danitexnobank.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/profile")
public class profilesController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private DepositRepository depositRepository;

    @GetMapping()
    public String profile(@AuthenticationPrincipal User currUser, Model model) {
       List<Deposit> lst= depositRepository.findAllByCreditUser(currUser);
        for (Deposit deposit : lst) {
            System.out.println(deposit);
        }

        return "profile";
    }


    @GetMapping("/emp")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE')")
    public String employeeProfile(Model model) {
        Iterable<Deposit> deposits = depositRepository.findAll();
        List<Deposit> result = StreamSupport.stream(deposits.spliterator(), false).
                filter(deposit -> deposit.isConfirm()==false)
                        .collect(Collectors.toList());
        model.addAttribute("deposits",result);
        return "emProfile";
    }


}
