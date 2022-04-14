package com.example.danitexnobank.Service;

import com.example.danitexnobank.models.ClientInfo;
import com.example.danitexnobank.models.PassportInfo;
import com.example.danitexnobank.models.Role;
import com.example.danitexnobank.models.User;
import com.example.danitexnobank.repositories.ClientRepo;
import com.example.danitexnobank.repositories.PassportRepo;
import com.example.danitexnobank.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {


    private final ClientRepo clientRepo;
    private final PassportRepo passportRepo;
    private final UserRepo userRepo;
    @Autowired
    public UserService(ClientRepo clientRepo, PassportRepo passportRepo, UserRepo userRepo){
        this.clientRepo = clientRepo;
        this.passportRepo = passportRepo;
        this.userRepo=userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null || !user.getPassword().equals(user.getPassword2())) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));//set с 1 значением
        userRepo.save(user);
        return true;
    }

    public void editUser(String username, User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for(String key :form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }

        }
        user.setUsername(username);
        userRepo.save(user);
    }

    public void addClient(User user, String name, String surName, String father,
                          ClientInfo clientInfo, PassportInfo passportInfo, String email) {
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

    }
}


