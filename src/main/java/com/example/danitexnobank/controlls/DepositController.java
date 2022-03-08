package com.example.danitexnobank.controlls;


import com.example.danitexnobank.models.ClientInfo;
import com.example.danitexnobank.models.Deposit;
import com.example.danitexnobank.models.PassportInfo;
import com.example.danitexnobank.models.User;
import com.example.danitexnobank.repositories.ClientRepo;
import com.example.danitexnobank.repositories.DepositRepository;
import com.example.danitexnobank.repositories.PassportRepo;
import com.example.danitexnobank.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping
    public String credits(Model model) {

        return "credits";
    }


    @GetMapping("/add")
    public String addCreditGET(ClientInfo clientInfo, PassportInfo passportInfo, User user, Model model) {
        model.addAttribute("clientInfo", clientInfo);
        model.addAttribute("passportInfo", passportInfo);
        model.addAttribute("User", user);
        return "clientAdd";
    }

    @PostMapping("/add")
    public String addClient(@AuthenticationPrincipal User user, Model model,
                            @ModelAttribute("clientInfo") ClientInfo clientInfo,
                            @ModelAttribute("passportInfo") PassportInfo passportInfo,
                            @RequestParam(name = "email") String email
    ) {
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
       if(user.getClientInfo()==null||user.getPassportInfo()==null)
           return "redirect:/clientAdd";
        return "depositAddStep2";
    }

    @PostMapping("/add2")
    public String addDepos(@AuthenticationPrincipal User user, Model model,
                            @RequestParam(name = "email") String email) {
        user.setEmail(email);
        userRepo.save(user);
        return "redirect:/";
    }





    @GetMapping("{deposit}/info")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public String creditInfo(@PathVariable Deposit deposit, Model model) {
        User user = deposit.getCreditUser();
        model.addAttribute("inf1", user.getUsername());
        // model.addAttribute("inf2",user.getPassport());
        // model.addAttribute("inf3",user.getIncome());
        model.addAttribute("inf4", deposit.getSum());
        return "depositDetails";
    }

}
