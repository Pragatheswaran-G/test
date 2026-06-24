package com.example.LoginAndRegister.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LoginAndRegister.model.User;
import com.example.LoginAndRegister.repo.Userrepo;
@Service
public class Loginservice {
    @Autowired
    Userrepo userrepo;

    public String getloginservice(String name, String pass) {

        User user = userrepo.findByNameAndPass(name, pass);
        if(user == null){
            user = userrepo.findByEmailAndPass(name, pass);
        }
        if(user != null){
            String rt=user.getName()+" "+user.getId()+" "+user.getEmail()+" "+user.getRole();
            return rt;
        }
        return "Invalid Username or Password";
    }
    
}
