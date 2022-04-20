package com.example.danitexnobank.controlls;


import com.example.danitexnobank.Service.DepositService;
import com.example.danitexnobank.Service.UserService;
import com.example.danitexnobank.models.ClientInfo;
import com.example.danitexnobank.models.Deposit;
import com.example.danitexnobank.models.PassportInfo;
import com.example.danitexnobank.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/deposit")
public class DepositController {


    private final DepositService depositService;
    private final UserService userService;

    @Autowired
    public DepositController(DepositService depositService, UserService userService) {
        this.depositService = depositService;
        this.userService = userService;
    }


    @GetMapping("/add")
    public String addClientGET(@AuthenticationPrincipal User user, Model model) {
        if (user.getClientInfo() != null || user.getPassportInfo() != null) {
            return "redirect:/";
        }
        model.addAttribute("clientInfo", new ClientInfo());
        model.addAttribute("passportInfo", new PassportInfo());
        return "clientAdd";
    }

    @PostMapping("/add")
    public String addClient(@AuthenticationPrincipal User user,
                            @RequestParam String name,
                            @RequestParam String surName,
                            @RequestParam String father,
                            @ModelAttribute("clientInfo") @Valid ClientInfo clientInfo,BindingResult bindingResult,
                            @ModelAttribute("passportInfo") @Valid PassportInfo passportInfo, BindingResult bindingResult2,
                            @RequestParam String email, Model model
    ) {
        if (bindingResult.hasErrors() == true||bindingResult2.hasErrors() == true) {
            Map<String, String> errMap = ControllerUtils.getErrors(bindingResult);
            Map<String, String> errMap2 = ControllerUtils.getErrors(bindingResult2);
            model.mergeAttributes(errMap);
            model.mergeAttributes(errMap2);
            if(name!=null||surName!=null||father!=null)
                model.addAttribute("nameErr","Введите");
            return "clientAdd";
        }
        userService.addClient(user, name, surName, father, clientInfo, passportInfo, email);
        System.out.println(";p");
        return "redirect:/";
    }

    @GetMapping("/add2")
    public String addDeposGET(@AuthenticationPrincipal User user, Model model) {
        if (user.getClientInfo() == null || user.getPassportInfo() == null)
            return "redirect:/add";
        model.addAttribute("deposit", new Deposit());
        return "depositAddStep2";
    }

    @PostMapping("/add2")
    public String addDepos(@ModelAttribute Deposit deposit, @AuthenticationPrincipal User user,
                           Model model) {
        if (deposit.getType().equals(-1) || deposit.getTerm() == -1) {
            model.addAttribute("deposit", new Deposit());
            model.addAttribute("errmess", "error");
            return "depositAddStep2";
        }
        depositService.addDeposit(deposit,user.getUsername());

        return "redirect:/";
    }

    @GetMapping("{deposit}/info")
    public String depositInfo(@AuthenticationPrincipal User curUser, @PathVariable Deposit deposit, Model model) {
        boolean isEmp = curUser.getRoles().stream().anyMatch(role -> role == role.EMPLOYEE);
        if (isEmp == true && deposit.isConfirm() == false) {
            model.addAttribute("itsEmp", true);
            System.out.println(1);
        }
        User user = deposit.getCreditUser();
        model.addAttribute("inf1", user.getUsername());
        model.addAttribute("deposit", deposit);
        if (curUser.equals(user))
            model.addAttribute("take", "take");
        return "depositDetails";
    }

    @PostMapping("{id}/take")
    public String depositTake(@PathVariable(name = "id") long id, Model model) {
        double newD=depositService.takeDeposit(id);
        System.out.println(newD);
        model.addAttribute("newDeposit",newD);
        return "profile";
    }


    @PostMapping("{id}/confirm")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public String confirmDeposit(@PathVariable(value = "id") long id,
                                 Model model) {
        depositService.confirmDeposit(id);
        return "redirect:/";
    }
}
