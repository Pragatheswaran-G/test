package com.example.LoginAndRegister.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LoginAndRegister.service.Registerservice;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class Regitercontroller {
    @Autowired
    Registerservice registerservice;
    
    @PostMapping("/register")
    public String register(@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("pass") String pass){
        return registerservice.getregisterservice(name,email,pass);
    }
}
