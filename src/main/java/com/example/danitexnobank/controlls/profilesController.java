package com.example.danitexnobank.controlls;

import com.example.danitexnobank.Service.DepositService;
import com.example.danitexnobank.Service.UserService;
import com.example.danitexnobank.models.ClientInfo;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Controller
@RequestMapping("/profile")
public class profilesController {

    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private DepositService depositService;

    @GetMapping()
    public String profile(@AuthenticationPrincipal User currUser, Model model) {
        if(currUser.getClientInfo()==null)
            return "redirect:/deposit/add";
        List<Deposit> lst= depositRepository.findAllByCreditUser(currUser);
        model.addAttribute("deposits",lst);
        model.addAttribute("date" ,new Date().getTime());
        return "profile";
    }

    @GetMapping("/emp")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE')")
    public String employeeProfile(Model model) {
        Iterable<Deposit> result = depositService.findNonConfirmetDeposits();
        model.addAttribute("deposits",result);
        return "emProfile";
    }


}
