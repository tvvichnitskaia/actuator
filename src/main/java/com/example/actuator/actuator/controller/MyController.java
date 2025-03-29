package com.example.actuator.actuator.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
public class MyController {

    @GetMapping()
    public String getHello(){
        return "Hello";
    }
}
