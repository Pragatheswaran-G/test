package com.example.LoginAndRegister.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LoginAndRegister.model.Product;
import com.example.LoginAndRegister.service.Cartservice;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("*")
public class Cartcontroller {
    @Autowired
    Cartservice cartservice;

    @PostMapping("/add")
    public String cartAdd(@RequestParam long userId ,@RequestParam long productId){
        return cartservice.getCartAdd(userId,productId);
    }
    @GetMapping("/show/{id}")
    public List<Product> cartShow(@PathVariable long id){
        return cartservice.getCartShow(id);
    }
    @DeleteMapping("/delete")
    public String cartDelete(@RequestParam long userId ,@RequestParam long productId){
        return cartservice.getCartDelete(userId,productId);
    }
    @PutMapping("/update")
    public String cartUpdate(@RequestParam long productId,@RequestParam int quantity){
        return cartservice.getCartUpdate(productId,quantity);
    }
}
