package com.example.danitexnobank.controlls;

import com.example.danitexnobank.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class EmailController {


    @PostMapping("/mail/{user}")
    public String sendmail(@PathVariable User user,
                           Model model) {

        return "redirect:/user";
    }
}
