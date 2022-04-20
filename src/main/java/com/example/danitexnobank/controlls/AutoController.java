package com.example.danitexnobank.controlls;
import com.example.danitexnobank.Service.UserService;
import com.example.danitexnobank.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;


@Controller
public class AutoController {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor= new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @Autowired
    private UserService userService;


    @GetMapping("/reg")
    public String reg(@ModelAttribute("user") User user){
        return "reg";
    }


    @PostMapping("/reg")
    public String addUser(@ModelAttribute("user") @Valid User user,BindingResult bindingResult,Model mod){
        if(bindingResult.hasErrors()){
            mod.addAttribute("massage1","логин, пароль или\n email неккоректны");
            return "reg";}
        else {
            if (!userService.addUser(user)) {
                if (user.getPassword().equals(user.getPassword2()))
                    mod.addAttribute("massage", "такое имя уже есть");
                else {
                    mod.addAttribute("massage", "пароли не совпадают");
                }
                return "reg";
            }
            if (!user.getPassword().equals(user.getPassword2())) {
                mod.addAttribute("massage", "пароли не совпадают");
                return "reg";
            }
        }
        return "redirect:/login";
    }
}
