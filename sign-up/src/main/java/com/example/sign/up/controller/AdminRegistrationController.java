package com.example.sign.up.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sign.up.model.AdminMyAppUser;
import com.example.sign.up.model.AdminMyAppUserRepository;

@RestController
public class AdminRegistrationController {

    @Autowired
    private AdminMyAppUserRepository adminMyAppUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value="/req/adminsignup", consumes= "application/json")
    public AdminMyAppUser createUser(@RequestBody AdminMyAppUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return adminMyAppUserRepository.save(user);
    }
    
}
