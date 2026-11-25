package com.example.practica22.service;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;



@RestController 
@RequestMapping("/api")
public class HolaService {

    @GetMapping("/auth/hola")
    public ResponseEntity<?> hola() {
        return ResponseEntity.ok("holaMundo");
    }
    

}
