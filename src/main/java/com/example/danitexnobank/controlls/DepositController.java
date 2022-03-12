package com.example.danitexnobank.controlls;


import com.example.danitexnobank.Service.BankService;
import com.example.danitexnobank.Service.TimerService;
import com.example.danitexnobank.models.*;
import com.example.danitexnobank.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/deposit")
public class DepositController {

    @Autowired
    DepositRepository depositRepository;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ClientRepo clientRepo;
    @Autowired
    PassportRepo passportRepo;
    @Autowired
    BankRepo bankRepo;
    @Autowired
    BankService bankService;
    @Autowired
    TimerService timerService;


    @GetMapping("/add")
    public String addClientGET(@AuthenticationPrincipal User user, Model model) {
        if (user.getClientInfo() != null || user.getPassportInfo() != null) {
            return "redirect:/";
        }
        model.addAttribute("clientInfo", new ClientInfo());
        model.addAttribute("passportInfo", new PassportInfo());
        model.addAttribute("User", user);
        return "clientAdd";
    }

    @PostMapping("/add")
    public String addClient(@AuthenticationPrincipal User user, Model model,
                            @RequestParam String name,
                            @RequestParam String surName,
                            @RequestParam String father,
                            @ModelAttribute("clientInfo") ClientInfo clientInfo,
                            @ModelAttribute("passportInfo") PassportInfo passportInfo,
                            @RequestParam(name = "email") String email
    ) {
        String redex = "[\\d]";
        if (name.equals(redex)) {
            System.out.println(1);
        }
        clientInfo.setFio(name + " " + surName + " " + father);
        user.setEmail(email);
        clientRepo.save(clientInfo);
        passportRepo.save(passportInfo);
        user.setClientInfo(clientInfo);
        user.setPassportInfo(passportInfo);
        userRepo.save(user);
        return "redirect:/";
    }

    @GetMapping("/add2")
    public String addDeposGET(@AuthenticationPrincipal User user, Model model) {
        if (user.getClientInfo() == null || user.getPassportInfo() == null)
            return "redirect:/clientAdd";
        model.addAttribute("deposit", new Deposit());
        return "depositAddStep2";
    }

    @PostMapping("/add2")
    public String addDepos(@AuthenticationPrincipal User user,
                           @ModelAttribute Deposit deposit,

                           Model model) {
        if (deposit.getType().equals(-1) || deposit.getTerm() == -1) {
            model.addAttribute("deposit", new Deposit());
            model.addAttribute("errmess", "error");
            return "depositAddStep2";
        }
        deposit.setCreditUser(user);
        if (deposit.getType().equals("srochniy")){
            deposit.setEndDate(timerService.endDateMethod(deposit.getTerm()));
            deposit.setPercent(deposit.getTerm());
        }
        else {
            deposit.setTerm(-1);
            deposit.setPercent(0.1);
        }
        depositRepository.save(deposit);
        return "redirect:/";
    }

    @GetMapping("{deposit}/info")
    public String depositInfo(@AuthenticationPrincipal User curUser, @PathVariable Deposit deposit, Model model) {
       boolean isEmp = curUser.getRoles().stream().anyMatch(role -> role==role.EMPLOYEE);
        if(isEmp==true&&deposit.isConfirm()==false){
            model.addAttribute("itsEmp",true);
            System.out.println(1);
        }
        User user = deposit.getCreditUser();
        model.addAttribute("inf1", user.getUsername());
        model.addAttribute("deposit",deposit);
        if (curUser.equals(user))
            model.addAttribute("take","take");
        return "depositDetails";
    }

    @PostMapping("{id}/take")
    public String deposit(@PathVariable Deposit deposit, Model model) {
        User user = deposit.getCreditUser();
        model.addAttribute("inf1", user.getUsername());
        model.addAttribute("inf4", deposit.getSum());
        return "depositDetails";
    }


    @PostMapping("{id}/confirm")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public String confirmDeposit(@PathVariable(value = "id") long id,
                                 Model model) {
        Optional<Deposit> optionalDeposit=depositRepository.findById(id);
        Deposit deposit = optionalDeposit.get();
        deposit.setConfirm(true);
        Optional<Bank> optionalBank = bankRepo.findById(1L);
        Bank bank = optionalBank.get();
        bank.setFond(bankService.increaseFond(bank.getFond(), deposit));
        bankRepo.save(bank);
        depositRepository.save(deposit);
        return "redirect:/";
    }
}
