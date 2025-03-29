package com.example.actuator.actuator.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin ")

public class MyController {

    @Autowired
    private  HealthEndpoint healthEndpoint;

    @GetMapping("/ping")
    @ResponseStatus(HttpStatus.OK)
    public String getHello(){
        return "Hello";
        //return ResponseEntity.ok("{\"status\":\"OK\"}");
    }


    @GetMapping(value = {"", "/"})
    public ResponseEntity<String> check() {
        String health = healthEndpoint.health().getStatus().getCode();
        return ResponseEntity.ok("{\"status\":\"" + health + "\"}");
    }
 }
