package com.example.LoginAndRegister.service;

import com.example.LoginAndRegister.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.LoginAndRegister.repo.Userrepo;

@Service
public class Pofileservice {

    @Autowired
    Userrepo userrepo;

    public String getPofileDetails(long id,String name, String email, String pass) {
        User user=userrepo.findById(id).orElse(null);
        if(user==null){
            return "Not update";
        }
        user.setName(name);
        user.setEmail(email);
        user.setPass(pass);

        userrepo.save(user);
        return "Update Successfully";
    }

}
