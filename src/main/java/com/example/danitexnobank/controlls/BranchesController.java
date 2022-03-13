package com.example.danitexnobank.controlls;


import com.example.danitexnobank.models.Branches;
import com.example.danitexnobank.repositories.BranchesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class BranchesController {

    @Autowired
    private BranchesRepo branchesRepo;


    @GetMapping("/branches")
    public String all(Model model) {
        Iterable<Branches> branches = branchesRepo.findAll();
        model.addAttribute("branches",branches);
        return "branches";
    }



}
