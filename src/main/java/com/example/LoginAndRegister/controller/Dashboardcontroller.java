package com.example.LoginAndRegister.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.LoginAndRegister.service.Todoservice;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class Dashboardcontroller  {
    @Autowired
    Todoservice todoservice;

    @PostMapping("/dashboard")
    public Map<String, Long> dashboard(@RequestParam String username){
        Map<String, Long> map = new HashMap<>();
        map.put("total",todoservice.getTotalTask(username));
        map.put("completed",todoservice.getCompletedTask(username));
        map.put("pending", todoservice.getPendingTask(username));
        return map;
    }
}
