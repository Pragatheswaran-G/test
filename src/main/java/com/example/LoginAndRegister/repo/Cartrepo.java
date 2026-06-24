package com.example.LoginAndRegister.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LoginAndRegister.model.Cart;
@Repository
public interface Cartrepo extends JpaRepository<Cart, Long> {

    Cart findByUserIdAndProductId(long userId, long productId);
    List<Cart> findByUserId(long userId);
}
