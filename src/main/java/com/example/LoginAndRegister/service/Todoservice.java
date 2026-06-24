package com.example.LoginAndRegister.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LoginAndRegister.model.Todo;
import com.example.LoginAndRegister.repo.Todorepo;

@Service
public class Todoservice {
    @Autowired
    Todorepo todorepo;

    public long getTotalTask(String username){
        return todorepo.countByUsername(username);
    } 
    public long getCompletedTask(String username){
        return todorepo.countByUsernameAndStatus(username, "Completed");
    }
    public long getPendingTask(String username){
        return todorepo.countByUsernameAndStatus(username, "Pending");
    }
    public String addTodo(String data,String username){
        Todo todo = new Todo();
        todo.setUsername(username);
        todo.setTask(data);
        todo.setStatus("Pending");
        todorepo.save(todo);
        return "Todo Added Successfully";
    }
    public List<Todo> showTodo(String username){
        return todorepo.findByUsername(username);
    }
    public String deleteTodo(long id){
        if(todorepo.existsById(id)){
            todorepo.deleteById(id);
            return "Todo Deleted Successfully";
        }
        return "Invalid Todo Id";
    }
    public String updateTodo(long id){
        Todo todo = todorepo.findById(id).orElse(null);
        if(todo == null){
            return "Todo Not Found";
        }
        if(todo.getStatus().equals("Pending"))
            todo.setStatus("Completed");
        else{
            todo.setStatus("Pending");
        }
        todorepo.save(todo);
        return "Todo Completed Successfully";
    }
}
