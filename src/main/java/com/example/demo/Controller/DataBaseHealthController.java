package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.TestDataHealth;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class DataBaseHealthController {

    private final TestDataHealth testDataHealth;

    public DataBaseHealthController(TestDataHealth testDataHealth) {
        this.testDataHealth = testDataHealth;
    }

    @GetMapping("/health/db")
    public String getDataBaseHealth(){
        return testDataHealth.isDataBaseConnected() ? "BANCO ON" : "BANCO OFF";
    }
}


