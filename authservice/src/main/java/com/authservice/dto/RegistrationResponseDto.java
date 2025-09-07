package com.authservice.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationResponseDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String contact;

    


}

