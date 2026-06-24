package com.example.LoginAndRegister.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.LoginAndRegister.model.Product;
import com.example.LoginAndRegister.repo.Productrepo;

@Service
public class Productservice {
    @Autowired
    Productrepo productrepo;
    public String getProductAdd(String name, String details, double price, int quantity, MultipartFile image) {
        try {
            Path dir=Paths.get("C:/uploads/images");
            if(!Files.exists(dir)){
                Files.createDirectories(dir);
            }
            String filename = UUID.randomUUID()+ "_"+image.getOriginalFilename().replaceAll("[^a-zA-Z0-9.]","_");
            Path path=dir.resolve(filename);
            Files.copy(image.getInputStream(),path);

            Product product = new Product();
            product.setName(name);
            product.setDetails(details);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setImageUrl(filename);

            productrepo.save(product);
            return "Product Added Successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error :" + e.getMessage();  
        }
    }
    public List<Product> getProductShow() {
        return productrepo.findAll();
    }
    
}
