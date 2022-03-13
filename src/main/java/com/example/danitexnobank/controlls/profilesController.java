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
        List<Deposit> lst= depositRepository.findAllByCreditUser(currUser);
        ClientInfo clientInfo = currUser.getClientInfo();
        model.addAttribute("deposits",lst);
        model.addAttribute("username",currUser.getUsername());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("busDate",formatter.format(clientInfo.getBusDate()));
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
