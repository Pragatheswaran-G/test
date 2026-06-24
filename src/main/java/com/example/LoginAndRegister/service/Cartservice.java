package com.example.LoginAndRegister.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LoginAndRegister.model.Cart;
import com.example.LoginAndRegister.model.Product;
import com.example.LoginAndRegister.repo.Cartrepo;
import com.example.LoginAndRegister.repo.Productrepo;
@Service
public class Cartservice {
    @Autowired
    Cartrepo cartrepo;

    @Autowired
    Productrepo productrepo;

    public String getCartAdd(long userId, long productId) {
        Cart cart = cartrepo.findByUserIdAndProductId(userId, productId);
        Product product=productrepo.findById(productId).orElse(null);
        if (product==null) {
            return "Product Not Found";
        }
        if(cart != null) {
            if(cart.getQuantity()>=product.getQuantity()){
                return "Stock Limit Reached";
            }
            cart.setQuantity(cart.getQuantity() + 1);
            cartrepo.save(cart);

            return "Quantity Updated";
        }

        Cart c = new Cart();
        c.setUserId(userId);
        c.setProductId(productId);
        c.setQuantity(1);
        cartrepo.save(c);

        return "Added Successfully";
    }
    public List<Product> getCartShow(long id) {
        List<Cart> carts=cartrepo.findByUserId(id);
        List<Product> products=new ArrayList<>();
        for (Cart cart : carts) {
            Product product=productrepo.findById(cart.getProductId()).orElse(null);
            if(product!=null){
                product.setCartNqt(cart.getQuantity());
                products.add(product);
            }
        }
        return products;
    }
    public String getCartDelete(long userId,long productId) {
        Cart cart = cartrepo.findByUserIdAndProductId(userId, productId);
        if(cart==null){
            return "Invalid Details";
        }
        if(cart.getQuantity()>1){
            cart.setQuantity(cart.getQuantity() - 1);
            cartrepo.save(cart);
            return "decr by 1";
        }
        cartrepo.delete(cart);
        return "Remove Successfully";
    }
    public String getCartUpdate(long productId, int quantity) {
        Product product=productrepo.findById(productId).orElse(null);
        if(product==null){
            return "Already Removed";
        }
        if(product.getQuantity()<=1){
            productrepo.delete(product);
            return "Quantity removed";
        }
        
        product.setQuantity(product.getQuantity()-quantity);
        productrepo.save(product);
        return "Payment Successfully";
    }
    
}
