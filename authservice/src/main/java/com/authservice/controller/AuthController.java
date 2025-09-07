package com.authservice.controller;

import com.authservice.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authservice.dto.APIResponse;
import com.authservice.dto.RegistrationRequestDto;
import com.authservice.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthController {

    private final AuthService service;


    @PostMapping("/register")
    public ResponseEntity<APIResponse<?>> registration(@RequestBody @Valid RegistrationRequestDto dto){
        APIResponse<?>response=service.register(dto);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<String>>login(@RequestBody LoginDto dto){
       APIResponse<String>response= service.login(dto);
       return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }
}
