package com.example.danitexnobank.controlls;

import com.example.danitexnobank.Service.UserService;
import com.example.danitexnobank.models.Role;
import com.example.danitexnobank.models.User;
import com.example.danitexnobank.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;


    @GetMapping
    public String userList(Model model){
        Iterable<User> users= userRepo.findAll();
        model.addAttribute("users", users);
        return "personalArea";
    }


    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "editUser";
    }

    @PostMapping
    public String userEdit(@RequestParam String username,
                           @RequestParam Map<String, String> form,
                           @RequestParam("userId") User user, Model model) {

        System.out.println(user);
        User userfromDb = userRepo.findByUsername(username);

        if (userfromDb != null&&username.equals(user.getUsername())==false) {
            model.addAttribute("err", "такое имя уже есть");
            model.addAttribute("user", user);
            model.addAttribute("roles", Role.values());
            return "editUser";
        }
        userService.editUser(username,user,form);
        return "redirect:/user";
    }



}