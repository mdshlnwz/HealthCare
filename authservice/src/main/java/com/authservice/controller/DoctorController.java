package com.authservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {

    @GetMapping("/profile")
    @PreAuthorize("hasRole('DOCTOR')")
    public String doctorProfile(){
        return "Doctor Profile";



    }
}
