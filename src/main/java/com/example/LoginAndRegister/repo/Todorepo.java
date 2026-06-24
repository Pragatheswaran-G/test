package com.example.LoginAndRegister.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.LoginAndRegister.model.Todo;

@Repository
public interface Todorepo extends JpaRepository<Todo, Long>{
    long countByUsername(String username);
    long countByUsernameAndStatus(String username, String status);
    List<Todo> findByUsername(String username);
}