package com.example.danitexnobank.controlls;


import com.example.danitexnobank.models.Credit;
import com.example.danitexnobank.models.User;
import com.example.danitexnobank.repositories.CreditRepository;
import com.example.danitexnobank.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credits")
public class CreditController {

    @Autowired
    CreditRepository creditRepository;
    @Autowired
    UserRepo userRepo;




    @GetMapping
    public String credits(Model model) {

        return "credits";
    }


    @GetMapping("/add")
    public String addCreditGET(Model model){

        return "creditsAdd";
    }

    @PostMapping("/add")
    public String addCreditPOST(@AuthenticationPrincipal User user, Model model,
                            @RequestParam(name = "type") String type,
                            @RequestParam(name =  "term") String term,
                            @RequestParam(name = "sum") Double sum,
                            @RequestParam(name = "percent") String percent,
                            @RequestParam(name = "income") Double income,
                            @RequestParam(name = "passport") String passport,
                            @RequestParam(name = "email") String email
                            ){
        user.setEmail(email);
        user.setIncome(income);
        user.setPassport(passport);
        userRepo.save(user);
        Credit credit = new Credit(sum,user,Double.parseDouble(percent));
        credit.setTerm(Integer.parseInt(term));
        credit.setType("usual");
        creditRepository.save(credit);
        return "redirect:/";
    }

    @GetMapping("{credit}/info")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public String creditInfo(@PathVariable Credit credit ,Model model){
        User user = credit.getCreditUser();
        model.addAttribute("inf1",user.getUsername());
        model.addAttribute("inf2",user.getPassport());
        model.addAttribute("inf3",user.getIncome());
        model.addAttribute("inf4",credit.getSum());
        return "creditDetails";
    }

}
