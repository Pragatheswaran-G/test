package com.example.LoginAndRegister.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LoginAndRegister.model.User;
@Repository
public interface Userrepo extends JpaRepository<User,Long> {
    User findByNameAndPass(String name,String pass);
    User findByEmailAndPass(String name,String pass);
    User findByEmail(String email);
    User findByName(String name);
}
