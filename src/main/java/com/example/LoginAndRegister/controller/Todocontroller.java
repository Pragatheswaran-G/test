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

import com.example.LoginAndRegister.model.Todo;
import com.example.LoginAndRegister.service.Todoservice;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class Todocontroller {
    @Autowired
    Todoservice todoservice;

    @PostMapping("/todo/add")
    public String addTodoController(@RequestParam String data,@RequestParam String username){
        return todoservice.addTodo(data,username);
    }
    
    @GetMapping("/todo/show")
    public List<Todo> showTodoController(@RequestParam String username){
        return todoservice.showTodo(username);
    }
    @DeleteMapping("/todo/delete/{id}")
    public String deleteTodoController(@PathVariable long id){
        return todoservice.deleteTodo(id);
    }
    @PutMapping("/todo/update/{id}")
    public String updateTodo(@PathVariable long id){
        return todoservice.updateTodo(id);
    }
}
