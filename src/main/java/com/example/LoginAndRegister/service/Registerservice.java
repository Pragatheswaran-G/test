package com.example.LoginAndRegister.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LoginAndRegister.model.User;
import com.example.LoginAndRegister.repo.Userrepo;
@Service
public class Registerservice {
    @Autowired
    Userrepo userrepo;
    public String getregisterservice(String name,String email,String pass){
        if(userrepo.findByEmail(email)!=null){
            return "Email Already Exsist";
        }
        if(userrepo.findByName(name)!=null){
            return "Username Already Exsist";
        }
        User user=new User(name,email,pass);
        userrepo.save(user);
        return "Register Successfully";
    }
}
