package com.example.LoginAndRegister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.LoginAndRegister.service.Pofileservice;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class Profilecontroller {
    @Autowired
    Pofileservice profileservice;

    @PutMapping("/profile")
    public String profieDetails(@RequestParam("id") long id,@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("pass") String pass){
        return profileservice.getPofileDetails(id,name,email,pass);
    }
}
