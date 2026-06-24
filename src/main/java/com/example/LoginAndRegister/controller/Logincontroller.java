package com.example.LoginAndRegister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LoginAndRegister.service.Loginservice;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class Logincontroller {
    @Autowired
    Loginservice loginservice;

    @PostMapping("/login")
    public String login(@RequestParam("name") String name,@RequestParam("pass") String pass){
        return loginservice.getloginservice(name,pass);
    }
}
