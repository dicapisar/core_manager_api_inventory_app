package com.dicapisar.coreManagerAPI.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PingController {
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {return new ResponseEntity<>("Pong", HttpStatus.OK);}
}
