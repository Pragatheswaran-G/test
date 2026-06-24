package com.example.LoginAndRegister.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.LoginAndRegister.model.Product;
import com.example.LoginAndRegister.service.Productservice;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class Productcontroller {
    @Autowired
    Productservice productservice;

    @PostMapping("/product/add")
    public String productAdd(@RequestParam String name,@RequestParam String details ,@RequestParam double price,@RequestParam int quantity,@RequestParam MultipartFile image){
         return productservice.getProductAdd(name,details,price,quantity,image);
    }
    @GetMapping("/product/show")
    public List<Product> productShow(){
        return productservice.getProductShow();
    }
}
